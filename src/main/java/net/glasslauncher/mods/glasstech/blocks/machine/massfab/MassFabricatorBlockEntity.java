package net.glasslauncher.mods.glasstech.blocks.machine.massfab;

import lombok.Getter;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.item.GTMassAccelerant;
import net.glasslauncher.mods.glasstech.recipe.machine.output.RecipeOutputType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class MassFabricatorBlockEntity extends MachineBlockEntityTemplate {
    @Getter
    protected int progress;
    @Getter
    protected int maxProgress = 1000000;
    @Getter
    protected int accelerant;

    public MassFabricatorBlockEntity() {
        super(VoltageTier.HV, VoltageTier.HV.maxVoltage, VoltageTier.HV.maxVoltage * 2);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Mass Fabricator";
    }

    @Override
    public void processTick() {
        // Check if we can process the current input
        if (canProcess() && energy > 0) {
            ItemStack accelerantStack = getInput(0);
            if (accelerant <= 0 && accelerantStack != null && accelerantStack.getItem() instanceof GTMassAccelerant massAccelerant) {
                accelerant = massAccelerant.getAccelerantValue();
                accelerantStack.count--;
                if (accelerantStack.count <= 0) {
                    setInput(0, null);
                }
            }
            // We have power, add the base progress and then add the accelerant
            lit = true;
            int baseProgress = removeEnergy(energyConsumption);
            progress += baseProgress;
            int accelerantBoost = Math.min(baseProgress, accelerant);
            accelerant -= accelerantBoost;
            progress += accelerantBoost * 5;
        }
        else {
            lit = false;
        }

        if (progress < 0) {
            // If progress is less than zero, clamp it to zero
            progress = 0;
        }
        else if (progress >= getMaxProgress()) {
            // If the progress has reached maximum, craft the recipe
            progress = 0;
            pushOutput(RecipeOutputType.PRIMARY, new ItemStack(GlassTechItems.uuMatter), false);
        }
    }

    @Override
    public boolean canProcess() {
        return true;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        progress = nbt.getInt("progress");
        accelerant = nbt.getInt("accelerant");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("progress", progress);
        nbt.putInt("accelerant", accelerant);
    }
}
