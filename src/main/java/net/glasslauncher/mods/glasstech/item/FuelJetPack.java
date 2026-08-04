package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.alwaysmoreitems.api.SubItemProvider;
import net.glasslauncher.mods.glasstech.GTItemOverlay;
import net.glasslauncher.mods.glasstech.GTJetPackTick;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.awt.*;
import java.util.List;

public class FuelJetPack extends TemplateArmorItem implements GTJetPackTick, GTItemOverlay, ArmorTextureProvider {
    private final Identifier identifier;
    // VoltageTier is just here to make up numbers
    private final VoltageTier voltageTier;
    private final int maxEnergy;

    public FuelJetPack(Identifier identifier, int slot, VoltageTier voltageTier) {
        super(identifier, 0, 0, slot);
        this.identifier = identifier;
        this.voltageTier = voltageTier;
        this.maxEnergy = voltageTier.maxVoltage * 320;
        maxProtection = 0;
        setMaxDamage(0);
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
    public Identifier getTexture(ArmorItem armor) {
        return identifier;
    }

    @Override
    public int getMaxHeight() {
        return 256;
    }

    @Override
    public float getVelocity() {
        return 0.15f;
    }

    @Override
    public float getMaxVelocity() {
        return 0.5f;
    }

    @SubItemProvider
    public List<ItemStack> getSubItems() {
        ItemStack stack = new ItemStack(GlassTechItems.jetpack);
        stack.getStationNbt().putInt("energy", getEnergyCapacity(stack));

        return List.of(
                new ItemStack(GlassTechItems.jetpack),
                stack
        );
    }

    @Override
    public int getColor(int colorOffset) {
        return new Color(255 - (colorOffset / 2), 255 - (colorOffset / 2), 0).getRGB();
    }

    @Override
    public boolean addTooltip() {
        return false;
    }
}
