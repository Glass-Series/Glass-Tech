package net.glasslauncher.mods.glasstech.blocks.transformer;

import net.glasslauncher.mods.glasstech.VoltageTier;

public class MVTransformerBlockEntity extends TransformerBlockEntity {
    @Override
    protected VoltageTier getTier() {
        return VoltageTier.MV;
    }
}
