package net.glasslauncher.mods.glasstech.blocks.batbox.esu;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockEntityTemplate;

public class ESUBlockEntity extends PowerStorageBlockEntityTemplate {

    public ESUBlockEntity() {
        super(VoltageTier.MV, 600000);
    }

    @Override
    public String getName() {
        return "ESU";
    }
}
