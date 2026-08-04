package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.GTCustomDamageHandler;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class NanoArmor extends PowerArmor implements GTCustomDamageHandler {


    public NanoArmor(Identifier identifier, int slot, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, slot, voltageTier, maxEnergy);
        setMaxDamage(0);
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return false;
    }

    @Override
    public void onTakeDamage(Entity entity, ItemStack stack, int damage) {
        removeEnergy(stack, damage * 32);
    }

    @Override
    public boolean canAbsorbDamage(Entity player, ItemStack stack) {
        return getEnergyStored(stack) > 0;
    }

    @Override
    public int getMaxArmorDamage(Entity entity, ItemStack stack) {
        return getEnergyCapacity(stack);
    }

    @Override
    public int getCurrentArmorDamage(Entity entity, ItemStack stack) {
        return getEnergyCapacity(stack) - getEnergyStored(stack);
    }

    @Override
    public Identifier getTexture(ArmorItem armor) {
        return Identifier.of(GlassTech.NAMESPACE, "nano_suit");
    }
}
