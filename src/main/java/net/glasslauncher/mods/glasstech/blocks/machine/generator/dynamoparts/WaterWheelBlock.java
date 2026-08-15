package net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts;

import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoComponentBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.template.item.TemplateBlockItem;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.NotNull;

@HasCustomBlockItemFactory(WaterWheelBlock.WaterWheelBlockItem.class)
@DynamoComponentBlock
public class WaterWheelBlock extends TemplateBlockWithEntity implements CustomTooltipProvider {
    public WaterWheelBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setSoundGroup(WOOD_SOUND_GROUP);
        setTranslationKey(identifier);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        setHardness(2);
        setResistance(5);
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
    public BlockEntity createBlockEntity() {
        return new WaterWheelBlockEntity();
    }

    @Override
    public int getRenderLayer() {
        return -1;
    }


    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                Formatting.GRAY + "Can be stacked on the same dynamo",
                Formatting.GRAY + "but has diminishing returns."
        };
    }

    public static class WaterWheelBlockItem extends TemplateBlockItem {
        public WaterWheelBlockItem(int i) {
            super(i);
            setMaxCount(4);
        }
    }
}
