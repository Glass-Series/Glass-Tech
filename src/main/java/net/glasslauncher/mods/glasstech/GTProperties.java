package net.glasslauncher.mods.glasstech;

import net.glasslauncher.mods.glasstech.blocks.FoamColor;
import net.modificationstation.stationapi.api.state.property.EnumProperty;
import net.modificationstation.stationapi.api.state.property.IntProperty;

public class GTProperties {
    public static final EnumProperty<FoamColor> FOAM_COLOR = EnumProperty.of("foam_color", FoamColor.class);

    public static final IntProperty LEAVES_DISTANCE = IntProperty.of("distance", 0, 8);
    public static final IntProperty SCAFFOLD_DISTANCE = IntProperty.of("distance", 0, 16);
}
