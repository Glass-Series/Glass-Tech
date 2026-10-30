package net.glasslauncher.mods.glasstech.blocks.batbox;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockEntityTemplate;
import net.minecraft.screen.ScreenHandler;

public class BatBoxBlockEntity extends PowerStorageBlockEntityTemplate {

    public BatBoxBlockEntity() {
        super(VoltageTier.LV, 40000);
    }

    @Override
    public String getName() {
        return "Battery Box";
    }

    @Override
    public ScreenHandler getInventoryFilterHandler() {
        return new BatBoxScreenHandler(null, this);
    }
}
