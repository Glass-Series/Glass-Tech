package net.glasslauncher.mods.glasstech.events;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;

import java.util.List;

public interface GTMachineListener<T extends MachineBlockEntityTemplate<T>> {
    default List<GTMachineListener<?>> getDependencies() {
        return null;
    }

    default void listenerOnTick(T blockEntity) {}
    default void listenerPreTick(T blockEntity) {}
    default void listenerPostTick(T blockEntity) {}
    default void listenerPostCraft(T blockEntity) {}

    default boolean listenerCanCraft(T blockEntity) {
        return true;
    }

    default boolean listenerInterruptMachine(T blockEntity) {
        return false;
    }
}
