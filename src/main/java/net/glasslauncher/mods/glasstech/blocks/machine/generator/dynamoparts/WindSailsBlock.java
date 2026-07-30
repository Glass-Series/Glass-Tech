package net.glasslauncher.mods.glasstech.blocks.machine.generator.dynamoparts;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.EnvironmentInterfaces;
import net.glasslauncher.mods.glasstech.GTProperties;
import net.glasslauncher.mods.glasstech.GlassTechItemInWorldRenderer;
import net.glasslauncher.mods.glasstech.blocks.machine.generator.DynamoComponentBlock;
import net.glasslauncher.mods.glasstech.blocks.renderer.WindSailsBlockEntityRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.client.model.item.ItemWithRenderer;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.template.item.TemplateBlockItem;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@HasCustomBlockItemFactory(WindSailsBlock.WindSailsBlockItem.class)
@DynamoComponentBlock
public class WindSailsBlock extends TemplateBlockWithEntity implements CustomTooltipProvider {
    public WindSailsBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setSoundGroup(WOOD_SOUND_GROUP);
        setTranslationKey(identifier);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(GTProperties.HAS_AIR, Boolean.FALSE));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return super.getPlacementState(context).with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(GTProperties.HAS_AIR);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public BlockEntity createBlockEntity() {
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

        float[] notchMoment = SheepEntity.COLORS[15 - item.getDamage()]; // NOOOOTCH
        WindSailsBlockEntity entity = ((WindSailsBlockEntity) world.getBlockEntity(x, y, z));
        entity.red = notchMoment[0];
        entity.green = notchMoment[1];
        entity.blue = notchMoment[2];

        item.count--;
        if (item.count <= 0) {
            player.inventory.main[player.inventory.selectedSlot] = null;
        }
        return true;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                Formatting.RED + "Requires an empty 15x15 area to work!"
        };
    }

    @EnvironmentInterfaces({@EnvironmentInterface(value = EnvType.CLIENT, itf = ItemWithRenderer.class), @EnvironmentInterface(value = EnvType.CLIENT, itf = GlassTechItemInWorldRenderer.class)})
    @SuppressWarnings("deprecation")
    public static class WindSailsBlockItem extends TemplateBlockItem implements ItemWithRenderer, GlassTechItemInWorldRenderer {
        public WindSailsBlockItem(int i) {
            super(i);
            setMaxCount(2);
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void renderItemOnGui(ItemRenderer itemRenderer, TextRenderer textRenderer, TextureManager textureManager, int itemId, int damage, int textureIndex, int x, int y) {
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void renderItemOnGui(ItemRenderer itemRenderer, TextRenderer textRenderer, TextureManager textureManager, ItemStack stack, int x, int y) {
            WindSailsBlockEntityRenderer renderer = (WindSailsBlockEntityRenderer) BlockEntityRenderDispatcher.INSTANCE.getRenderer(WindSailsBlockEntity.class);
            renderer.setBrightness(1);
            renderer.render(null, x + 7, y + 7, 5, 0);
//            renderItemOnGui(itemRenderer, textRenderer, textureManager, stack.itemId, stack.getDamage(), stack.getTextureId(), x, y);
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void renderItemOnGround(float brightness) {
            GL11.glPushMatrix();
            GL11.glScaled(0.2, 0.2, 0.2);
            WindSailsBlockEntityRenderer renderer = (WindSailsBlockEntityRenderer) BlockEntityRenderDispatcher.INSTANCE.getRenderer(WindSailsBlockEntity.class);
            renderer.setBrightness(brightness);
            renderer.render(null, 0, 5, 0, 0);
            GL11.glPopMatrix();
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void renderItemInHand(float brightness) {
            GL11.glPushMatrix();
            GL11.glScaled(0.2, 0.2, 0.2);
            WindSailsBlockEntityRenderer renderer = (WindSailsBlockEntityRenderer) BlockEntityRenderDispatcher.INSTANCE.getRenderer(WindSailsBlockEntity.class);
            renderer.setBrightness(brightness);
            renderer.render(null, 0, 5, 0, 0);
            GL11.glPopMatrix();
        }
    }
}
