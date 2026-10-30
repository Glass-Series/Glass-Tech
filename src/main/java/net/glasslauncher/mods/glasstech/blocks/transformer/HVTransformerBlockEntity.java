package net.glasslauncher.mods.glasstech.blocks.transformer;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.screen.ScreenHandler;

public class HVTransformerBlockEntity extends TransformerBlockEntity {
    @Override
    protected VoltageTier getTier() {
        return VoltageTier.HV;
    }

    @Override
    public ScreenHandler getInventoryFilterHandler() {
        return null;
    }
}
