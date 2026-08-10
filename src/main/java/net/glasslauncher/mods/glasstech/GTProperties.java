package net.glasslauncher.mods.glasstech;

import net.glasslauncher.mods.glasstech.blocks.FoamColor;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.state.property.EnumProperty;
import net.modificationstation.stationapi.api.state.property.IntProperty;

public class GTProperties {
    public static final EnumProperty<FoamColor> FOAM_COLOR = EnumProperty.of("foam_color", FoamColor.class);

    public static final IntProperty LEAVES_DISTANCE = IntProperty.of("distance", 0, 8);
    public static final IntProperty SCAFFOLD_DISTANCE = IntProperty.of("distance", 0, 16);

    public static final BooleanProperty HAS_AIR = BooleanProperty.of("has_air");

    public static final IntProperty RESIN = IntProperty.of("resin", 0, 3);

    public static final IntProperty FOAM = IntProperty.of("foam", 0, 2);
}
