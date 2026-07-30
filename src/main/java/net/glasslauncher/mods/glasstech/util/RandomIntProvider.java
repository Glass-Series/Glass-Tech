package net.glasslauncher.mods.glasstech.util;

import java.util.Random;

@FunctionalInterface
public interface RandomIntProvider {
    int provide(Random random);
}
