package net.glasslauncher.mods.glasstech.blocks;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;

public interface GTWrenchable extends Wrenchable {

    @Override
    default boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE) {
            BlockState state = world.getBlockState(x, y, z);
            if (state.contains(Properties.FACING)) {
                int ordinal = state.get(Properties.FACING).ordinal() + 1;
                world.setBlockState(x, y, z, state.with(Properties.FACING, Direction.byId(ordinal > Direction.values().length ? 0 : ordinal)));
                return true;
            }
            if (state.contains(Properties.HORIZONTAL_FACING)) {
                world.setBlockState(x, y, z, state.with(Properties.HORIZONTAL_FACING, state.get(Properties.HORIZONTAL_FACING).rotateClockwise(Direction.Axis.Y)));
                return true;
            }
            return false;
        }
        if (wrenchMode == WrenchMode.MODE_WRENCH) {
            BlockEntity blockEntity = world.getBlockEntity(x, y, z);
            if (!(blockEntity instanceof Inventory inventory)) {
                return false;
            }
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack itemStack = inventory.getStack(i);
                if (itemStack == null) {
                    continue;
                }
                dropStack(world, x, y, z, itemStack);
            }
            WorldHelper.breakBlockWithParticles(world, x, y, z, world.getBlockId(x, y, z));
            dropStacksWrench(world, x, y, z, 0);
            return true;
        }
        return false;
    }

    void dropStack(World world, int x, int y, int z, ItemStack itemStack);

    void dropStacksWrench(World world, int x, int y, int z, int i);

    // TODO: Uncomment when dany L moment subsides
//    @Override
//    public boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
//        if (wrenchMode == WrenchMode.MODE_ROTATE) {
//            BlockState state = world.getBlockState(x, y, z);
//            if (state.contains(Properties.FACING)) {
//                int ordinal = state.get(Properties.FACING).ordinal() - 1;
//                world.setBlockState(x, y, z, state.with(Properties.FACING, Direction.byId(ordinal < 0 ? Direction.values().length - 1 : ordinal)));
//                return true;
//            }
//            if (state.contains(Properties.HORIZONTAL_FACING)) {
//                world.setBlockState(x, y, z, state.with(Properties.HORIZONTAL_FACING, state.get(Properties.HORIZONTAL_FACING).rotateCounterclockwise(Direction.Axis.Y)));
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }
}
