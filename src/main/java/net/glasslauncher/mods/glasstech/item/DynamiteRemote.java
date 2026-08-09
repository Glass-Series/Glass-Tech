package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.blocks.GTDynamiteBlock;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.util.NbtHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class DynamiteRemote extends TemplateItem implements CustomTooltipProvider {
    public static int MAX_TARGETS = 5;

    public static final String TARGETS = "targets";

    public DynamiteRemote(Identifier identifier) {
        super(identifier);
    }

    public static HashSet<BlockPos> getTargets(ItemStack stack) {
        HashSet<BlockPos> targets = new HashSet<>();
        NbtList targetsList = stack.getStationNbt().getList(TARGETS);
        for (int i = 0; i < targetsList.size(); i++) {
            if (targetsList.get(i) instanceof NbtCompound tag) {
                BlockPos pos = NbtHelper.readBlockPos(tag);
                targets.add(pos);
            }
        }
        return targets;
    }

    public static void setTargets(ItemStack stack, Collection<BlockPos> targets) {
        NbtList targetsList = new NbtList();
        for (BlockPos pos : targets) {
            NbtCompound tag = new NbtCompound();
            NbtHelper.writeBlockPos(tag, pos);
            targetsList.add(tag);
        }
        stack.getStationNbt().put(TARGETS, targetsList);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        BlockPos target = new BlockPos(x, y, z);
        Set<BlockPos> targets = getTargets(stack);

        if (!world.getBlockState(target).isOf(GlassTechBlocks.dynamiteBlock)) {
            return false;
        }

        if (user.isSneaking() && targets.contains(target)) {
            targets.remove(target);
            setTargets(stack, targets);
            user.sendMessage("Removed target: (x:" + target.getX() + ", y:" + target.getY() + ", z:" + target.getZ() + ")");
        } else {
            if (targets.size() >= MAX_TARGETS) {
                user.sendMessage(Formatting.RED + "Cannot add more than " + MAX_TARGETS + " targets!");
                return true;
            }
            targets.add(target);
            setTargets(stack, targets);
            user.sendMessage("Added target: (x:" + target.getX() + ", y:" + target.getY() + ", z:" + target.getZ() + ")");
        }


        return true;
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        List<BlockPos> actualTargets = new ArrayList<>();
        for (BlockPos pos : getTargets(stack)) {
            if (world.getBlockState(pos).isOf(GlassTechBlocks.dynamiteBlock)) {
                world.setBlockState(pos, States.AIR.get());
                actualTargets.add(pos);
            }
        }

        for (BlockPos pos : actualTargets) {
            GTDynamiteBlock.explode(world, pos.getX(), pos.getY(), pos.getZ());
        }
        setTargets(stack, List.of());
        return stack;
    }


    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        List<String> lines = new ArrayList<>();
        lines.add(originalTooltip);
        Set<BlockPos> targets = getTargets(stack);
        lines.add(targets.size() + " Targets");
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            for (BlockPos target : targets) {
                lines.add(Formatting.GRAY + " Target: (x:" + target.getX() + ", y:" + target.getY() + ", z:" + target.getZ() + ")");
            }
        } else {
            lines.add(Formatting.GRAY + "Hold shift to see individual targets");
        }
        return lines.toArray(String[]::new);
    }
}
