package net.glasslauncher.mods.glasstech.blocks.batbox.mesu;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockEntityTemplate;
import net.minecraft.screen.ScreenHandler;

public class MESUBlockEntity extends PowerStorageBlockEntityTemplate {

    public MESUBlockEntity() {
        super(VoltageTier.HV, 10000000);
    }

    @Override
    public String getName() {
        return "MESU";
    }

    @Override
    public ScreenHandler getInventoryFilterHandler() {
        return new MESUScreenHandler(null, this);
    }
}
