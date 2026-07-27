package net.glasslauncher.mods.glasstech.blocks;

import net.modificationstation.stationapi.api.util.StringIdentifiable;

// I hate making new color objects like this but I gotta have easy reading of the colors
public enum FoamColor implements StringIdentifiable {
    WHITE(0xFFFFFF, 0, 15),
    ORANGE(0xFFA500, 1, 14),
    MAGENTA(0xFF00FF, 2, 13),
    LIGHT_BLUE(0xADD8E6, 3, 12),
    YELLOW(0xFFFF00, 4, 11),
    LIME(0x32CD32, 5, 10),
    PINK(0xFF69B4, 6, 9),
    GRAY(0x555555, 7, 8),
    LIGHT_GRAY(0xAAAAAA, 8, 7),
    CYAN(0x00AAAA, 9, 6),
    PURPLE(0x800080, 10, 5),
    BLUE(0x32329c, 11, 4),
    BROWN(0x8B4513, 12, 3),
    GREEN(0x00A300, 13, 2),
    RED(0xa62626, 14, 1),
    BLACK(0x2e2e2e, 15, 0),
    DEFAULT(0x858585, 0, 0)
    ;

    public final int color;
    public final int woolMeta;
    public final int dyeMeta;

    FoamColor(int color, int woolMeta, int dyeMeta) {
        this.color = color;
        this.woolMeta = woolMeta;
        this.dyeMeta = dyeMeta;
    }

    @Override
    public String asString() {
        return name().toLowerCase();
    }

    public static final FoamColor[] DYE_TO_FOAM_COLOR = {
            FoamColor.BLACK,
            FoamColor.RED,
            FoamColor.GREEN,
            FoamColor.BROWN,
            FoamColor.BLUE,
            FoamColor.PURPLE,
            FoamColor.CYAN,
            FoamColor.LIGHT_GRAY,
            FoamColor.GRAY,
            FoamColor.PINK,
            FoamColor.LIME,
            FoamColor.YELLOW,
            FoamColor.LIGHT_BLUE,
            FoamColor.MAGENTA,
            FoamColor.ORANGE,
            FoamColor.WHITE
    };

}
