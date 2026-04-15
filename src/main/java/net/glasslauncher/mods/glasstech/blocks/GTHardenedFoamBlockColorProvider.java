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

import java.awt.*;

@Environment(EnvType.CLIENT)
public class GTHardenedFoamBlockColorProvider implements BlockColorProvider {
    public static final EnumProperty<FoamColor> FOAM_COLOR_PROPERTY = EnumProperty.of("foam_color", FoamColor.class);

    @Override
    public int getColor(BlockState state, @Nullable BlockView world, @Nullable BlockPos pos, int tintIndex) {
        return state.get(FOAM_COLOR_PROPERTY).color;
    }

    // I hate making new color objects like this but I gotta have easy reading of the colors
    public enum FoamColor implements StringIdentifiable {
        WHITE(0xFFFFFF, 0),
        LIGHT_GRAY(0xAAAAAA, 1),
        GRAY(0x555555, 2),
        BLACK(0x000000, 3),
        RED(0xA30000, 4),
        ORANGE(0xFFA500, 5),
        BROWN(0x8B4513, 6),
        GREEN(0x00A300, 7),
        BLUE(0x0000A3, 8),
        PURPLE(0x800080, 9),
        CYAN(0x00AAAA, 10),
        PINK(0xFF69B4, 11),
        LIME(0x32CD32, 12),
        YELLOW(0xFFFF00, 13),
        LIGHT_BLUE(0xADD8E6, 14),
        MAGENTA(0xFF00FF, 15),
        ;

        public final int color;
        public final int meta;

        FoamColor(int color, int meta) {
            this.color = color;
            this.meta = meta;
        }

        @Override
        public String asString() {
            return name();
        }
    }


    public static final FoamColor[] WOOL_TO_FOAM_COLOR = {
            FoamColor.WHITE,
            FoamColor.LIGHT_GRAY,
            FoamColor.GRAY,
            FoamColor.BLACK,
            FoamColor.RED,
            FoamColor.ORANGE,
            FoamColor.BROWN,
            FoamColor.GREEN,
            FoamColor.BLUE,
            FoamColor.PURPLE,
            FoamColor.CYAN,
            FoamColor.PINK,
            FoamColor.LIME,
            FoamColor.YELLOW,
            FoamColor.LIGHT_BLUE,
            FoamColor.MAGENTA
    };

}
