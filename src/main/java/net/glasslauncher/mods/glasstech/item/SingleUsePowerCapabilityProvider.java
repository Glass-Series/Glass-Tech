package net.glasslauncher.mods.glasstech.item;

import net.danygames2014.nyalib.capability.item.ItemCapabilityProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class SingleUsePowerCapabilityProvider extends ItemCapabilityProvider<SingleUsePowerCapability> {
    @Override
    public @Nullable SingleUsePowerCapability getCapability(ItemStack stack) {
        if (stack.getItem() instanceof SingleUsePowerItem singleusePowerItem) {
            return new SingleUsePowerCapability(singleusePowerItem.getSingleUsePowerProvided());
        }
        if (stack.getItem() == Item.REDSTONE) {
            return new SingleUsePowerCapability(500);
        }
        return null;
    }
}
