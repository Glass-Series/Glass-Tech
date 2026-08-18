package net.glasslauncher.mods.glasstech.events;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.biome.Biome;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;

import static net.glasslauncher.mods.glasstech.worldgen.Features.*;

public class GTChunkDecoration {

    @EventListener
    public static void decorateChunk(WorldGenEvent.ChunkDecoration event) {
        if (event.world.dimension.id == 0) {
            genTrees(event);
            genOres(event);
        }
    }

    public static void genTrees(WorldGenEvent.ChunkDecoration event) {
        int rarity = 0; // If it'll try at all
        int genChances = 0; // How many trees might generate
        if (event.biome.equals(Biome.FOREST)) {
            rarity = 100;
            genChances = 2 + event.random.nextInt(4);
        }
        else if (event.biome.equals(Biome.RAINFOREST)) {
            rarity = 70;
            genChances = 5 + event.random.nextInt(4);
        }
        else if (event.biome.equals(Biome.SWAMPLAND)) {
            rarity = 60;
            genChances = 7 + event.random.nextInt(6);
        }

        if (rarity != 0 && event.random.nextInt(rarity) == 0) {
            int x;
            int z;
            for (int i = 0; i < genChances; i++) {
                x = event.x + event.random.nextInt(16);
                z = event.z + event.random.nextInt(16);
                RUBBER_TREE.generate(event.world, event.random, x, event.world.getTopY(x, z), z);
            }
        }
    }

    public static void genOres(WorldGenEvent.ChunkDecoration event) {
        int x;
        int y;
        int z;
        int attempts = 15;
        for (int i = 0; i < attempts; i++) {
            x = event.x + event.random.nextInt(16);
            y = 58 + event.random.nextInt(30);
            z = event.z + event.random.nextInt(16);
            TIN_ORE.generate(event.world, event.random, x, y, z);
        }

        attempts = 12;
        for (int i = 0; i < attempts; i++) {
            x = event.x + event.random.nextInt(16);
            y = 20 + event.random.nextInt(60);
            z = event.z + event.random.nextInt(16);
            COPPER_ORE.generate(event.world, event.random, x, y, z);
        }

        attempts = 8;
        for (int i = 0; i < attempts; i++) {
            x = event.x + event.random.nextInt(16);
            y = event.random.nextInt(40);
            z = event.z + event.random.nextInt(16);
            URANIUM_ORE.generate(event.world, event.random, x, y, z);
        }
    }
}
