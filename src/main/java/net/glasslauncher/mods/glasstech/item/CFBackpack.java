package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.GTArmorPlayerTick;
import net.glasslauncher.mods.glasstech.GTCustomDamageHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class CFBackpack extends TemplateArmorItem implements ArmorTextureProvider, GTCustomDamageHandler, GTArmorPlayerTick, CustomTooltipProvider {
    private final Identifier identifier;

    public CFBackpack(Identifier identifier, int j, int k, int slot) {
        super(identifier, j, k, slot);
        this.identifier = identifier;
        maxProtection = 0;
        setMaxDamage(1024);
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

    @Override
    public void tick(PlayerEntity player, int armorSlot) {
        ItemStack heldItem = player.getHand();
        if (heldItem == null || !(heldItem.getItem() instanceof CFSprayer) || heldItem.getDamage() == 0) {
            return;
        }

        ItemStack armor = player.inventory.armor[armorSlot];

        int restoredCharges = Math.min(heldItem.getDamage(), armor.getMaxDamage() - armor.getDamage());
        heldItem.setDamage(heldItem.getDamage() - restoredCharges);
        armor.damage(restoredCharges, null);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                Formatting.GRAY + "Can be filled with pellets in the canner"
        };
    }
}
