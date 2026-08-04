package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class PowerStorageArmor extends PowerArmor implements GTTickingArmor {
    private final Identifier identifier;

    public PowerStorageArmor(Identifier identifier, int slot, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, slot, voltageTier, maxEnergy);
        this.identifier = identifier;
        maxProtection = 0;
        setMaxDamage(0);
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return true;
    }

    @Override
    public Identifier getTexture(ArmorItem armor) {
        return identifier;
    }

    @Override
    public void armorTick(ItemStack stack, World world, Entity entity, int slot) {
        if (!(entity instanceof PlayerEntity player) || equipmentSlot != 3 - slot || getEnergyStored(stack) < 1) {
            return;
        }

        for (ItemStack itemStack : player.inventory.armor) {
            if (itemStack == null || itemStack.getItem() instanceof PowerStorageArmor) {
                continue;
            }
            if (itemStack.getItem() instanceof GTEnergyStorageItem gtEnergyStorageItem) {
                int chargeAmount = removeEnergy(stack, voltageTier.maxVoltage);
                chargeAmount = gtEnergyStorageItem.addEnergy(stack, chargeAmount);
                addEnergy(stack, chargeAmount);
            }
        }

        for (int i = 0; i < 9; i++) {
            ItemStack itemStack = player.inventory.main[i];

            if (itemStack == null || itemStack.getItem() instanceof PowerStorageArmor) {
                continue;
            }
            if (itemStack.getItem() instanceof GTEnergyStorageItem gtEnergyStorageItem) {
                int chargeAmount = removeEnergy(stack, voltageTier.maxVoltage);
                chargeAmount -= gtEnergyStorageItem.addEnergy(itemStack, chargeAmount);
                addEnergy(stack, chargeAmount);
            }
        }
    }
}
