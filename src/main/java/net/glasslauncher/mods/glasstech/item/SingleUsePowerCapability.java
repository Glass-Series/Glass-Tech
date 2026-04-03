package net.glasslauncher.mods.glasstech.item;

import lombok.Getter;
import net.danygames2014.nyalib.capability.item.ItemCapability;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.modificationstation.stationapi.api.util.Identifier;

public class SingleUsePowerCapability extends ItemCapability {
    public static final Identifier IDENTIFIER = GlassTech.NAMESPACE.id("single_use_power");

    @Getter
    private final int powerProvided;

    public SingleUsePowerCapability(int powerProvided) {
        this.powerProvided = powerProvided;
    }
}
