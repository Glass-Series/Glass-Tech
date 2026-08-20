package net.glasslauncher.mods.glasstech.blocks.machine;

import java.util.HashMap;
import java.util.Iterator;

public class SlotType {
    private static final HashMap<String, SlotType> REGISTRY = new HashMap<>();
    public static SlotType of(String name) {
        return REGISTRY.get(name);
    }
    public static Iterator<SlotType> iterator() {
        return REGISTRY.values().iterator();
    }

    public static final SlotType FUEL = new SlotType("fuel");

    public final String name;

    public SlotType(String name) {
        if (REGISTRY.containsKey(name)) {
            throw new IllegalArgumentException("Slot type " + name + " already exists");
        }
        this.name = name;
        REGISTRY.put(name, this);
    }
}
