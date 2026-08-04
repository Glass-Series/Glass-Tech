package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.DamageSource;
import net.glasslauncher.mods.glasstech.GTArmorDamageHandler;
import net.glasslauncher.mods.glasstech.GTCustomDamageHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class RubberBoots extends TemplateArmorItem implements GTArmorDamageHandler, ArmorTextureProvider, GTCustomDamageHandler {
    private final Identifier identifier;

    public RubberBoots(Identifier identifier, int j, int k, int slot) {
        super(identifier, j, k, slot);
        this.identifier = identifier;
        maxProtection = 0;
        setMaxDamage(1024);
    }

    @Override
    public boolean shouldDamage(LivingEntity entity, ItemStack armor, int damage, DamageSource source) {
        boolean willDamage = source != DamageSource.FALLING;
        if (!willDamage) {
            armor.damage(damage * 2, entity);
        }
        return willDamage;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public Identifier getTexture(ArmorItem armor) {
        return identifier;
    }

    @Override
    public void onTakeDamage(Entity entity, ItemStack stack, int damage) {

    }

    @Override
    public boolean canAbsorbDamage(Entity entity, ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxArmorDamage(Entity entity, ItemStack stack) {
        return 0;
    }

    @Override
    public int getCurrentArmorDamage(Entity entity, ItemStack stack) {
        return 0;
    }
}
