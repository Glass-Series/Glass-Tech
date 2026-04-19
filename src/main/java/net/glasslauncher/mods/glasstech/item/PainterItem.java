package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.blocks.FoamColor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import static net.glasslauncher.mods.glasstech.GlassTech.LOGGER;

public class PainterItem extends TemplateItem {

    public PainterItem(Identifier identifier) {
        super(identifier);
        setMaxDamage(129);
        setMaxCount(1);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity player, World world, int x, int y, int z, int side) {
        if (world.isRemote) {
            return true;
        }

        BlockState state = world.getBlockState(x, y, z);
        NbtCompound nbt = stack.getStationNbt();
        String color = nbt.getString("color");

        if (color == null || color.isEmpty()) {
            return false;
        }

        FoamColor foamColor;
        try {
            foamColor = FoamColor.valueOf(color);
        } catch (Exception e) {
            LOGGER.error("Painter has invalid color?", e);
            foamColor = null;
        }

        if (foamColor == null) {
            return false;
        }

        if (state.contains(FoamColor.FOAM_COLOR_PROPERTY)) {
            state = state.with(FoamColor.FOAM_COLOR_PROPERTY, foamColor);
            world.setBlockStateWithNotify(x, y, z, state);
            world.playSound(player, "random.click", 0.5f, 1);
            stack.damage(1, player);
            if (stack.getDamage() == stack.getMaxDamage() - 1) {
                nbt.putString("color", "");
            }
            return true;
        }
        else if (state.getBlock() == Block.WOOL) {
            world.setBlockMeta(x, y, z, foamColor.woolMeta);
            world.playSound(player, "random.click", 0.5f, 1);
            stack.damage(1, player);
            if (stack.getDamage() == stack.getMaxDamage() - 1) {
                nbt.entries.remove("color");
            }
            return true;
        }

        return false;
    }
}
