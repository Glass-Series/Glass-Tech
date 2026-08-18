package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ElectricJetPack extends PowerArmor implements GTJetPackTick, CustomTooltipProvider {

    private final Identifier identifier;

    public ElectricJetPack(Identifier identifier, int slot, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, slot, voltageTier, maxEnergy);
        this.identifier = identifier;
        maxProtection = 0;
        setMaxDamage(0);
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return false;
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
        return 0.12f;
    }

    @Override
    public float getMaxVelocity() {
        return 0.3f;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                Formatting.GRAY + "Worse than the normal jetpack,",
                Formatting.GRAY + "but only requires power"
        };
    }
}
