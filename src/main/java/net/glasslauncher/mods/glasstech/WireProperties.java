package net.glasslauncher.mods.glasstech;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WireProperties {
    public static final Cache<Identifier, WireProperties> CACHE = Caffeine.newBuilder().build();

    public final Identifier identifier;
    public final float size;
    public final boolean insulated;
    public final GTWireMaterial wireMaterial;

    @Nullable
    public static WireProperties of(Identifier identifier) {
        return CACHE.getIfPresent(identifier);
    }

    @NotNull
    public static WireProperties createIfAbsent(Identifier identifier, float size, boolean insulated, GTWireMaterial wireMaterial) {
        return CACHE.get(identifier, i -> new WireProperties(i, size, insulated, wireMaterial));
    }

    private WireProperties(Identifier identifier, float size, boolean insulated, GTWireMaterial wireMaterial) {
        this.identifier = identifier;
        this.size = size;
        this.insulated = insulated;
        this.wireMaterial = wireMaterial;
    }
}
