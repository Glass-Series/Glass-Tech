package net.glasslauncher.mods.glasstech.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.color.block.BlockColorProvider;
import net.modificationstation.stationapi.api.state.property.EnumProperty;
import net.modificationstation.stationapi.api.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import static net.glasslauncher.mods.glasstech.GlassTech.NAMESPACE;

@Environment(EnvType.CLIENT)
public class GTHardenedFoamBlockColorProvider implements BlockColorProvider {
    public static final EnumProperty<FoamColor> FOAM_COLOR_PROPERTY = EnumProperty.of("foam_color", FoamColor.class);

    @Override
    public int getColor(BlockState state, @Nullable BlockView world, @Nullable BlockPos pos, int tintIndex) {
        if (state.get(FOAM_COLOR_PROPERTY) == null) {
            return 0;
        }
        return state.get(FOAM_COLOR_PROPERTY).color;
    }

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
        BLUE(0x0000A3, 11, 4),
        BROWN(0x8B4513, 12, 3),
        GREEN(0x00A300, 13, 2),
        RED(0xA30000, 14, 1),
        BLACK(0x000000, 15, 0),
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
    }

    public static final FoamColor[] WOOL_TO_FOAM_COLOR = {
            FoamColor.WHITE,
            FoamColor.ORANGE,
            FoamColor.MAGENTA,
            FoamColor.LIGHT_BLUE,
            FoamColor.YELLOW,
            FoamColor.LIME,
            FoamColor.PINK,
            FoamColor.GRAY,
            FoamColor.LIGHT_GRAY,
            FoamColor.CYAN,
            FoamColor.PURPLE,
            FoamColor.BLUE,
            FoamColor.BROWN,
            FoamColor.GREEN,
            FoamColor.RED,
            FoamColor.BLACK
    };

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
