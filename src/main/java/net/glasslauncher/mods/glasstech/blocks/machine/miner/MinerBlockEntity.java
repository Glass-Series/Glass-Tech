package net.glasslauncher.mods.glasstech.blocks.machine.miner;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.danygames2014.nyalib.capability.CapabilityHelper;
import net.danygames2014.nyalib.capability.block.itemhandler.ItemHandlerBlockCapability;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.glasslauncher.mods.glasstech.blocks.machine.ProgressMachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotType;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.util.BlockWalker;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class MinerBlockEntity extends ProgressMachineBlockEntityTemplate {
    public static final TagKey<Block> ORES_TAG = TagKey.of(BlockRegistry.KEY, Identifier.of("c:ores"));

    public static BlockWalker.WalkValidator MINING_FILTER = ((world, x, y, z) -> world.getBlockState(x, y, z).isIn(ORES_TAG) && canMine(world, x, y, z));

    protected ArrayList<BlockPos> queue;
    protected List<ItemStack> heldItems;

    protected boolean retracted = false;

    static {
    }

    public MinerBlockEntity() {
        super(VoltageTier.LV, 200, VoltageTier.MV.maxVoltage, 300);
        addInput();
        addSlot(SlotType.FUEL);
    }

    @Override
    public String getName() {
        return "Miner";
    }

    @Override
    public void craftRecipe() {
        if (retracted) {
            return;
        }
        BlockPos end = getEndOfDrill();

        // Try to ditch held items
        yeetItems(false);
        if (heldItems != null) {
            return;
        }

        // Try to process the queue
        if (queue != null && !queue.isEmpty()) {
            BlockPos digPos = queue.remove(queue.size() - 1);
            while (!canMine(world, digPos.x, digPos.y, digPos.z)) {
                if (queue.isEmpty()) {
                    digPos = null;
                    break;
                }
                digPos = queue.remove(0);
            }
            if (digPos != null) {
                BlockState state = world.getBlockState(digPos);
                heldItems = getStacksFromBlock(digPos.x, digPos.y, digPos.z);

                WorldHelper.breakBlockWithParticles(world, digPos.x, digPos.y, digPos.z, state.getBlock().id);

                yeetItems(false);
            }
            return; // Don't try to rescan or expand the drill if we're already processing a queue.
        }

        expandDrill(end);

        if (queue == null || queue.isEmpty()) {
            findBlocks(end.x, end.y, end.z);
        }

        end = getEndOfDrill();

        if ((queue == null || queue.isEmpty()) && (end == null || !canMine(world, end.x, end.y, end.z))) {
            retract(false);
        }
    }

    public void retract(boolean forceEject) {
        retracted = true;
        BlockPos end = getEndOfDrill();
        for (int removeY = y - 1; removeY >= end.y + 1; removeY--) {
            WorldHelper.breakBlockWithParticles(world, x, removeY, z, GlassTechBlocks.miningPipeBlock.id);
        }
        int pipeCount = y - (end.y + 1);
        if (pipeCount < 1) {
            return;
        }
        if (heldItems == null) {
            heldItems = new ArrayList<>();
        }
        for (int pipeStack = (int) Math.ceil(pipeCount / 64f); pipeStack > 0; pipeStack--) {
            heldItems.add(new ItemStack(GlassTechBlocks.miningPipeBlock, Math.min(pipeCount, 64)));
            pipeCount -= 64;
        }
        yeetItems(forceEject);
    }

    @Override
    public boolean canProcess() {
        if (retracted) { // This is only checked once a tick, so we're good to handle this here.
            yeetItems(false);
            if (world.getPowerLevel(x, y, z) > 0) {
                retracted = false;
            }
        }
        if (heldItems != null) {
            return true;
        }
        if (retracted) {
            return false;
        }
        ItemStack pipeStack = getInput(0);
        if (pipeStack == null || !(pipeStack.getItem() instanceof BlockItem blockItem) || blockItem.getBlock() != GlassTechBlocks.miningPipeBlock) {
            return false;
        }
        BlockPos end = getEndOfDrill();
        return end != null && (canMine(world, end.x, end.y, end.z) || world.getBlockState(end).isAir());
    }

    public static boolean canMine(World world, int x, int y, int z) {
        BlockState state = world.getBlockState(x, y, z);

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

    public void yeetItems(boolean forceEject) {
        if (heldItems == null || heldItems.isEmpty()) {
            return;
        }

        List<ItemStack> remainders = new ArrayList<>();
        for (ItemStack stack : heldItems) {
            ItemStack remainder = yeetItem(stack, forceEject);
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

    public ItemStack yeetItem(ItemStack stack, boolean forceEject) {
        ItemHandlerBlockCapability capability = CapabilityHelper.getCapability(world, x, y + 1, z, ItemHandlerBlockCapability.class);
        if (capability != null) {
            stack = capability.insertItem(stack, Direction.DOWN);
        }
        else if (world.getBlockState(x, y + 1, z).isAir() || forceEject) {
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

    public void expandDrill(BlockPos target) {
        ItemStack pipeStack = getInput(0);
        if (pipeStack == null || !(pipeStack.getItem() instanceof BlockItem blockItem) || blockItem.getBlock() != GlassTechBlocks.miningPipeBlock) {
            return;
        }
        BlockState state = world.getBlockState(target);

        if (!state.isAir()) {
            if (!canMine(world, target.x, target.y, target.z)) {
                return;
            }

            heldItems = getStacksFromBlock(target.x, target.y, target.z);

            WorldHelper.breakBlockWithParticles(world, target.x, target.y, target.z, state.getBlock().id);
        }

        if ((state.isAir() || state.getMaterial().isReplaceable()) && world.canPlace(GlassTechBlocks.miningPipeBlock.id, target.x, target.y, target.z, false, Direction.DOWN.getId())) {
            pipeStack.count--;
            if (pipeStack.count < 1) {
                setInput(0, null);
            }
            world.setBlock(target.x, target.y, target.z, GlassTechBlocks.miningPipeBlock.id);
        }
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
        for (Vec3i side : BlockWalker.DIAGONAL_SEARCH_OFFSETS) {
            BlockPos pos = new BlockPos(x + side.x, y + side.y, z + side.z);
            if (!queue.contains(pos) && MINING_FILTER.test(world, pos.x, pos.y, pos.z)) {
                queue = BlockWalker.walk(world, pos, BlockWalker.DIAGONAL_SEARCH_OFFSETS, MINING_FILTER);;
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("retracted", retracted);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        retracted = nbt.getBoolean("retracted");
    }
}
