package net.glasslauncher.mods.glasstech.blocks.machine.teslacoil;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockTemplate;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class TeslaCoilBlock extends MachineBlockTemplate {

    public TeslaCoilBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public MachineBlockEntityTemplate createBlockEntity() {
        return new TeslaCoilBlockEntity();
    }
}
