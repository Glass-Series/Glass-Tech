package net.glasslauncher.mods.glasstech.recipe.machine;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.glasslauncher.mods.glasstech.events.init.InitListener;
import net.glasslauncher.mods.glasstech.recipe.HashUtil;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegistryTemplate<T extends BasicMachineRecipe> {
    public final HashMap<Identifier, T> registry = new HashMap<>();
    public final Long2ObjectOpenHashMap<T> cache = new Long2ObjectOpenHashMap<>();

    public boolean register(Identifier identifier, T recipe) {
        if (registry.containsKey(identifier)) {
            InitListener.LOGGER.warn("Recipe {} already exists in {}!", identifier, getClass().getName());
            return false;
        }

        registry.put(identifier, recipe);
        return true;
    }

    public T get(Identifier identifier) {
        return registry.get(identifier);
    }

    public T remove(Identifier identifier) {
        cache.clear();
        return registry.remove(identifier);
    }

    public T get(ItemStack[] input) {
        // Calculate a hash based on the array contents
        long arrayHash = HashUtil.hashInputs(input);

        // Check if the cache contains this input
        if (!cache.containsKey(arrayHash)) {
            cache.put(arrayHash, fetch(input));
        }

        return cache.get(arrayHash);
    }

    private T fetch(ItemStack[] input) {
        
        for (Map.Entry<Identifier, T> recipe : registry.entrySet()) {
            if (recipe.getValue().matches(input)) {
                return recipe.getValue();
            }
        }

        return null;
    }
}
