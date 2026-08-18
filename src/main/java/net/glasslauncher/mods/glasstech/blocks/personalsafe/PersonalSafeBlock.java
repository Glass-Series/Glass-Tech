package net.glasslauncher.mods.glasstech.blocks.personalsafe;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class PersonalSafeBlock extends TemplateBlockWithEntity implements Wrenchable {
    public PersonalSafeBlock(Identifier identifier) {
        super(identifier, Material.METAL);
        setUnbreakable();
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return super.getPlacementState(context).with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (!(blockEntity instanceof PersonalSafeBlockEntity safeBlockEntity) || !safeBlockEntity.canPlayerUse(player)) {
            return false;
        }
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("personal_safe"),
                safeBlockEntity,
                new GenericContainerScreenHandler(player.inventory, safeBlockEntity)
        );
        return true;
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new PersonalSafeBlockEntity();
    }

    @Override
    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
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
            if (!(blockEntity instanceof PersonalSafeBlockEntity safeBlockEntity)) {
                return false;
            }
            if (!safeBlockEntity.owner.equals(player.name) && !safeBlockEntity.owner.isEmpty()) {
                player.sendMessage(Formatting.RED + "This safe isn't owned by you!");
                return false;
            }
            for (int i = 0; i < safeBlockEntity.size(); i++) {
                ItemStack itemStack = safeBlockEntity.getStack(i);
                if (itemStack == null) {
                    continue;
                }
                dropStack(world, x, y, z, itemStack);
            }
            WorldHelper.breakBlockWithParticles(world, x, y, z, id);
            dropStacks(world, x, y, z, 0);
            return true;
        }
        return false;
    }

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
