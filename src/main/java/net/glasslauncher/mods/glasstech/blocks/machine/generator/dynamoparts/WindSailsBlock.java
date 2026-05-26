package net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts;

import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoComponentBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

@DynamoComponentBlock
public class WindSailsBlock extends TemplateBlockWithEntity {
    public WindSailsBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setSoundGroup(WOOD_SOUND_GROUP);
        setTranslationKey(identifier);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return super.getPlacementState(context).with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new WindSailsBlockEntity();
    }

    @Override
    public int getRenderLayer() {
        return -1;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        ItemStack item = player.getHand();
        if (item == null || item.getItem() != Item.DYE) {
            return false;
        }

        ((WindSailsBlockEntity) world.getBlockEntity(x, y, z)).color = SheepEntity.COLORS[15 - item.getDamage()]; // NOOOOTCH
        item.count--;
        if (item.count <= 0) {
            player.inventory.main[player.inventory.selectedSlot] = null;
        }
        return true;
    }
}
