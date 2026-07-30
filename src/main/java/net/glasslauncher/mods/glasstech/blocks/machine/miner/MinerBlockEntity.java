package net.glasslauncher.mods.glasstech.blocks.machine.miner;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.danygames2014.nyalib.capability.CapabilityHelper;
import net.danygames2014.nyalib.capability.block.itemhandler.ItemHandlerBlockCapability;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.ProgressMachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class MinerBlockEntity extends ProgressMachineBlockEntityTemplate {
    public static final TagKey<Block> ORES_TAG = TagKey.of(BlockRegistry.KEY, Identifier.of("c:ores"));
    private static final ObjectArrayList<Vec3i> SEARCH_OFFSETS = new ObjectArrayList<>();

    private ArrayList<BlockPos> queue;

    private List<ItemStack> heldItems;

    static {
        // Top and Bottom
        SEARCH_OFFSETS.add(new Vec3i(0, 1, 0));
        SEARCH_OFFSETS.add(new Vec3i(0, -1, 0));

        // Sides
        SEARCH_OFFSETS.add(new Vec3i(1, 0, 0));
        SEARCH_OFFSETS.add(new Vec3i(-1, 0, 0));
        SEARCH_OFFSETS.add(new Vec3i(0, 0, 1));
        SEARCH_OFFSETS.add(new Vec3i(0, 0, -1));

        // Diagonals
        SEARCH_OFFSETS.add(new Vec3i(1, 0, 1));
        SEARCH_OFFSETS.add(new Vec3i(1, 0, -1));
        SEARCH_OFFSETS.add(new Vec3i(-1, 0, 1));
        SEARCH_OFFSETS.add(new Vec3i(-1, 0, -1));
    }

    public MinerBlockEntity() {
        super(VoltageTier.LV, 200, VoltageTier.MV.maxVoltage, 300);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Miner";
    }

    @Override
    public void craftRecipe() {
        BlockPos end = getEndOfDrill();

        // Try to ditch held items
        yeetItems();
        if (heldItems != null) {
            return;
        }

        // Try to process the queue
        if (queue != null && !queue.isEmpty()) {
            BlockPos digPos = queue.remove(queue.size() - 1);
            while (!canMine(digPos)) {
                if (queue.isEmpty()) {
                    digPos = null;
                    break;
                }
                digPos = queue.remove(0);
            }
            if (digPos != null) {
                BlockState state = world.getBlockState(digPos);
                heldItems = getStacksFromBlock(digPos.x, digPos.y, digPos.z);

                world.worldEvent(null, 2001, digPos.x, digPos.y, digPos.z, state.getBlock().id + (world.getBlockMeta(digPos.x, digPos.y, digPos.z) << 28));
                world.setBlock(digPos.x, digPos.y, digPos.z, 0);

                yeetItems();
            }
            return; // Don't try to rescan or expand the drill if we're already processing a queue.
        }

        expandDrill(end);

        if (queue == null || queue.isEmpty()) {
            findBlocks(end.x, end.y, end.z);
        }
    }

    @Override
    public boolean canProcess() {
        BlockPos end = getEndOfDrill();
        return end != null && (canMine(end) || world.getBlockState(end).isAir());
    }

    public boolean canMine(BlockPos end) {
        if (end == null) {
            return false;
        }
        BlockState state = world.getBlockState(end);

        if (state.getBlock().getHardness() == -1) {
            return false;
        }
        if (state.getBlock().material.isHandHarvestable()) {
            return true;
        }
        if (!Item.IRON_PICKAXE.isSuitableFor(state.getBlock())) {
            return false;
        }
        return true;
    }

    public void yeetItems() {
        if (heldItems == null) {
            return;
        }

        List<ItemStack> remainders = new ArrayList<>();
        for (ItemStack stack : heldItems) {
            ItemStack remainder = yeetItem(stack);
            if (remainder != null) {
                remainders.add(remainder);
            }
        }
        if (remainders.isEmpty()) {
            heldItems = null;
        }
        else {
            heldItems = remainders;
        }
    }

    public ItemStack yeetItem(ItemStack stack) {
        ItemHandlerBlockCapability capability = CapabilityHelper.getCapability(world, x, y + 1, z, ItemHandlerBlockCapability.class);
        if (capability != null) {
            stack = capability.insertItem(stack, Direction.DOWN);
        }
        else if (world.getBlockState(x, y + 1, z).isAir()) {
            ItemEntity item = new ItemEntity(world, x + .5, y + 1.5, z + .5, stack);
            item.pickupDelay = 10;
            float baseVelocity = 0.05F;
            item.velocityX = (float) world.random.nextGaussian() * baseVelocity;
            item.velocityY = (float) world.random.nextGaussian() * baseVelocity + 1.0F;
            item.velocityZ = (float) world.random.nextGaussian() * baseVelocity;
            world.spawnEntity(item);
            stack = null;
        }
        return stack;
    }

    public boolean expandDrill(BlockPos target) {
        BlockState state = world.getBlockState(target);

        if (!state.isAir()) {
            if (!canMine(target)) {
                return false;
            }

            heldItems = getStacksFromBlock(target.x, target.y, target.z);

            world.worldEvent(null, 2001, target.x, target.y, target.z, state.getBlock().id + (world.getBlockMeta(target.x, target.y, target.z) << 28));
            world.setBlock(target.x, target.y, target.z, 0);
        }

        if ((state.isAir() || state.getMaterial().isReplaceable()) && world.canPlace(GlassTechBlocks.miningPipeBlock.id, target.x, target.y, target.z, false, Direction.DOWN.getId())) {
            world.setBlock(target.x, target.y, target.z, GlassTechBlocks.miningPipeBlock.id);
            return true;
        }
        return false;
    }

    public BlockPos getEndOfDrill() {
        for (int searchY = y - 1; searchY > world.getBottomY(); searchY--) {
            BlockState state = world.getBlockState(x, searchY, z);
            if (state.getBlock() == GlassTechBlocks.miningPipeBlock) {
                continue;
            }
            return new BlockPos(x, searchY, z);
        }
        return null; // No valid end of drill found, which means we are above the void.
    }

    public List<ItemStack> getStacksFromBlock(int x, int y, int z) {
        BlockState state = world.getBlockState(x, y, z);
        Block block = state.getBlock();

        if (state.isAir() || block == null) {
            return null;
        }

        int blockMeta = world.getBlockMeta(x, y, z);
        List<ItemStack> stacks = state.getBlock().getDropList(world, x, y, z, state, blockMeta);

        if (stacks == null) {
            stacks = new ObjectArrayList<>();
            int itemId = block.getDroppedItemId(blockMeta, world.random);
            int meta = block.getDroppedItemMeta(blockMeta);
            int count = block.getDroppedItemCount(world.random);

            if (itemId != 0 && count > 0) {
                stacks.add(new ItemStack(itemId, count, meta));
            }
        }

        return stacks;
    }

    public void findBlocks(int x, int y, int z) {
        queue = new ArrayList<>();
        for (Vec3i side : SEARCH_OFFSETS) {
            BlockPos pos = new BlockPos(x + side.x, y + side.y, z + side.z);
            if (world.getBlockState(pos.x, pos.y, pos.z).isIn(ORES_TAG) && !queue.contains(pos) && canMine(pos)) {
                queue = walk(pos);
            }
        }
    }

    public ArrayList<BlockPos> walk(BlockPos start) {
        // ArrayList for list of blocks yet to explore
        ArrayList<BlockPos> open = new ArrayList<>();
        // ArrayList for list of blocks that have been found
        ArrayList<BlockPos> closed = new ArrayList<>();

        // Add the starting position to explore
        open.add(start);

        // Go until open isnt empty
        while (!open.isEmpty()) {
            // Get the position to explore
            BlockPos pos = open.get(0);
            // Look at all of its sides
            for (Vec3i dir : SEARCH_OFFSETS) {
                // Get the side and see if there is a block on it. Then check if it doesnt already exist
                BlockPos side = new BlockPos(pos.x + dir.x, pos.y + dir.y, pos.z + dir.z);
                if (!closed.contains(side)) {
                    if (world.getBlockState(side.x, side.y, side.z).isIn(ORES_TAG) && canMine(side)) {
                        open.add(side);
                    }
                }
            }

            // Add the position to closed and remove it from open
            closed.add(pos);
            open.remove(pos);
        }

        return closed;
    }
}
