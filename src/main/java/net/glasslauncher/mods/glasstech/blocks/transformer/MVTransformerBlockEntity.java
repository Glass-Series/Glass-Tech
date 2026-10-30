package net.glasslauncher.mods.glasstech.blocks.transformer;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.screen.ScreenHandler;

public class MVTransformerBlockEntity extends TransformerBlockEntity {
    @Override
    protected VoltageTier getTier() {
        return VoltageTier.MV;
    }

    @Override
    public ScreenHandler getInventoryFilterHandler() {
        return null;
    }
}
