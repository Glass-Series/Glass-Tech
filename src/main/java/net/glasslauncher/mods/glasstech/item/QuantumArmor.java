package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.*;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class QuantumArmor extends NanoArmor implements GTArmorDamageHandler, GTArmorPlayerTick {
    public QuantumArmor(Identifier identifier, int slot, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, slot, voltageTier, maxEnergy);
        setMaxDamage(0);
    }

    @Override
    public Identifier getTexture(ArmorItem armor) {
        return Identifier.of(GlassTech.NAMESPACE, "quantum_suit");
    }

    @Override
    public boolean shouldDamage(LivingEntity entity, ItemStack armor, int damage, DamageSource source) {
        if (getEnergyStored(armor) < 1) {
            return true;
        }
        if (entity instanceof PlayerEntity player) {
            boolean notQuantum = false;
            for (ItemStack armorPiece : player.inventory.armor) {
                notQuantum = !(armorPiece.getItem() instanceof QuantumArmor quantumArmor) || quantumArmor.getEnergyStored(armorPiece) < 1;
                if (notQuantum) {
                    break;
                }
            }
            if (!notQuantum) {
                boolean willDamage = source.nature != DamageSource.Nature.ENVIRONMENT;
                if (!willDamage) {
                    for (ItemStack armorPiece : player.inventory.armor) {
                        removeEnergy(armorPiece, damage * 4);
                    }
                }
                return false;
            }
        }
        boolean wontDamage = false;
        if (armor.getItem() == GlassTechItems.quantumBoots) {
            wontDamage |= source == DamageSource.FALLING;
        }
        if (armor.getItem() == GlassTechItems.quantumChestplate) {
            wontDamage |= source == DamageSource.FIRE;
        }
        if (armor.getItem() == GlassTechItems.quantumHelmet) {
            wontDamage |= source == DamageSource.DROWNING;
        }
        if (wontDamage) {
            removeEnergy(armor, damage * 16);
        }
        return !wontDamage;
    }

    @Override
    public void tick(PlayerEntity player, int armorSlot) {
        ItemStack armor = player.inventory.armor[armorSlot];

        switch(armorSlot) {
            case HEAD -> {
                if (player.air < 150) {
                    removeEnergy(armor, Math.max(0, 300 - player.air));
                    player.air = 300;
                }
            }
            case CHEST -> {
                if (player.fireTicks > 0 && getEnergyStored(armor) > 0) {
                    removeEnergy(armor, player.fireTicks / 2);
                    player.fireTicks = 0;
                }
            }
            case LEGS -> {
                if ((player.onGround || player.isSubmergedInWater()) && (Math.abs(player.velocityX) + Math.abs(player.velocityZ) > 0.05d) && player.glasstech$isHoldingAbilityKey() && getEnergyStored(armor) > 0) {
                    removeEnergy(armor, 1);
                    if (player.isSubmergedInWater()) {
                        if (player.jumping) {
                            player.velocityY += 0.1;
                            if (player.velocityY > 0.2) {
                                player.velocityY = 0.2;
                            }
                        }
                        else if (player.isSneaking()) {
                            player.velocityY -= 0.1;
                            if (player.velocityY < -0.2) {
                                player.velocityY = -0.2;
                            }
                        }
                        player.moveNonSolid(0, 1, 0.05f); // Actually converts world X/Z to rotated X/Z and adds it to velocity
                        // I did this mapping. I'm a monster.
                    }
                    else {
                        player.moveNonSolid(0, 1, 0.2f);
                    }
                }
            }
            case BOOTS -> {
                if (player.glasstech$didJump() && !player.isSubmergedInWater() && player.glasstech$isHoldingAbilityKey()) {
                    player.velocityZ *= 3.5;
                    player.velocityX *= 3.5;
                    player.velocityY += 0.5;

                    if (player.velocityX > 6) {
                        player.velocityX = 6;
                    }
                    if (player.velocityZ > 6) {
                        player.velocityZ = 6;
                    }

                    removeEnergy(armor, 320);
                }
            }
        }
    }
}
