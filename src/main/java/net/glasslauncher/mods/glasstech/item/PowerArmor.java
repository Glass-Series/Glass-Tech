package net.glasslauncher.mods.glasstech.item;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.alwaysmoreitems.api.SubItemProvider;
import net.glasslauncher.mods.glasstech.GTEnergyBar;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public abstract class PowerArmor extends TemplateArmorItem implements GTEnergyStorageItem, GTEnergyBar, ArmorTextureProvider {
    @Setter
    protected int maxEnergy;
    @Setter @Getter
    protected VoltageTier voltageTier;

    public PowerArmor(Identifier identifier, int slot, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, 0, 0, slot);
        this.voltageTier = voltageTier;
        this.maxEnergy = maxEnergy;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        return stack.getStationNbt().getInt("energy");
    }

    @Override
    public int getEnergyCapacity(ItemStack stack) {
        return maxEnergy;
    }

    @Override
    public int setEnergy(ItemStack stack, int value) {
        int takenEnergy = Math.min(value, maxEnergy);
        stack.getStationNbt().putInt("energy", takenEnergy);
        return value - takenEnergy;
    }

    @Override
    public boolean canReceiveEnergy(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxEnergyInput(ItemStack stack) {
        return voltageTier.maxVoltage;
    }

    @Override
    public int getMaxEnergyOutput(ItemStack stack) {
        return canExtractEnergy(stack) ? voltageTier.maxVoltage : 0;
    }

    @SubItemProvider
    public List<ItemStack> getSubItems() {
        ItemStack charged = new ItemStack(this);
        charged.getStationNbt().putInt("energy", getEnergyCapacity(charged));
        return List.of(
                new ItemStack(this),
                charged
        );
    }
}
