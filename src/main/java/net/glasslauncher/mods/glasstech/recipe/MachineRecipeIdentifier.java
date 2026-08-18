package net.glasslauncher.mods.glasstech.recipe;

import net.glasslauncher.mods.glasstech.GlassTech;
import net.modificationstation.stationapi.api.util.Identifier;

public enum MachineRecipeIdentifier {
    MACERATOR("macerator"),
    COMPRESSOR("compressor"),
    CANNER("canner"),
    ELECTROLYZER("electrolyzer"),
    EXTRACTOR("extractor"),
    THERMAL("thermal"),
    ;

    public final Identifier identifier;

    MachineRecipeIdentifier(String id) {
        identifier = GlassTech.NAMESPACE.id(id);
    }
}
