package net.glasslauncher.mods.glasstech;

import net.modificationstation.stationapi.api.util.Util;

public interface GlassTechPlayer {
    default boolean glasstech$isHoldingAbilityKey() {return Util.assertImpl();}
    default void glasstech$setHoldingAbilityKey(boolean value) {Util.assertImpl();}

    default boolean glasstech$didJump() {return Util.assertImpl();}

    default boolean glasstech$isHovering() {return Util.assertImpl();}
    default void glasstech$setHovering(boolean hovering) {Util.assertImpl();};
}
