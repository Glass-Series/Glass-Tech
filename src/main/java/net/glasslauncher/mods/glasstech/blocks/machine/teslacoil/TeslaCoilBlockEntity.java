package net.glasslauncher.mods.glasstech.blocks.machine.teslacoil;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.ProgressMachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.teleporter.TeleporterBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.List;

public class TeslaCoilBlockEntity extends ProgressMachineBlockEntityTemplate {

    public TeslaCoilBlockEntity() {
        super(VoltageTier.MV, VoltageTier.MV.maxVoltage * 20, VoltageTier.MV.maxVoltage, VoltageTier.MV.maxVoltage * 2);
        inventory =  new ItemStack[0];
    }

    @Override
    public String getName() {
        return "Tesla Coil";
    }

    @Override
    public void craftRecipe() {
        //noinspection unchecked
        List<Entity> entityList = world.getEntities(null, Box.create(x - 3, y, z - 3, x + 3, y + 3, z + 3));
        entityList.forEach(e -> e.damage(null, 10));
    }

    @Override
    public boolean canProcess() {
        return true;
    }
}
