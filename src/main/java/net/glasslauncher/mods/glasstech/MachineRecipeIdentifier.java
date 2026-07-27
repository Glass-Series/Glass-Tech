package net.glasslauncher.mods.glasstech;

import net.modificationstation.stationapi.api.util.Identifier;

public enum MachineRecipeIdentifier {
    MACERATOR("macerator"),
    COMPRESSOR("compressor"),
    CANNER("canner"),
    ELECTROLYZER("electrolyzer"),
    EXTRACTOR("extractor"),
    GEOTHERMAL("geothermal"),
    ;

    public final Identifier identifier;

    MachineRecipeIdentifier(String id) {
        identifier = GlassTech.NAMESPACE.id(id);
    }
}
