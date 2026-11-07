package net.glasslauncher.mods.glasstech.events;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.modificationstation.stationapi.api.StationAPI;

import java.util.*;

public class GTMachineListenerList<T extends MachineBlockEntityTemplate<T>> {
    public final Class<T> ownerClass;
    private final Set<GTMachineListener<T>> listeners = new HashSet<>();
    private final List<GTMachineListener<T>> resolved = new ArrayList<>();

    private GTMachineListenerList(Class<T> ownerClass) {
        this.ownerClass = ownerClass;
        StationAPI.EVENT_BUS.post(new GTMachineListenerInitEvent<>(this));
        sort(listeners);
    }

    public void onTick(T blockEntity) {
        listeners.forEach(listener -> listener.listenerOnTick(blockEntity));
    }

    public void preTick(T blockEntity) {
        listeners.forEach(listener -> listener.listenerPreTick(blockEntity));
    }

    public void postTick(T blockEntity) {
        listeners.forEach(listener -> listener.listenerPostTick(blockEntity));
    }

    public void postCraft(T blockEntity) {
        listeners.forEach(listener -> listener.listenerPostCraft(blockEntity));
    }

    public boolean canCraft(T blockEntity) {
        return listeners.stream().allMatch(listener -> listener.listenerCanCraft(blockEntity));
    }

    public boolean interruptMachine(T blockEntity) {
        return listeners.stream().anyMatch(listener -> listener.listenerInterruptMachine(blockEntity));
    }

    public boolean add(GTMachineListener<T> listener) {
        return listeners.add(listener);
    }

    public boolean remove(GTMachineListener<T> listener) {
        return listeners.remove(listener);
    }

    private void sort(Set<GTMachineListener<T>> stillToSort) {
        listeners.forEach(listener -> {
            if ((listener.getDependencies() == null || listener.getDependencies().isEmpty())) {
                resolved.add(listener);
            }
            else if (listener.getDependencies().stream().allMatch(e -> stillToSort.stream().anyMatch(e2 -> e2 == e))) {
                resolved.add(listener);
            }
        });

        //noinspection SlowAbstractSetRemoveAll idc cause these lists are sorted once for any given machine class (ideally)
        if (!stillToSort.removeAll(resolved) && !stillToSort.isEmpty()) {
            throw new RuntimeException("Cyclic dependency!");
        }

        if (!stillToSort.isEmpty()) {
            sort(stillToSort);
        }
    }

    private static final HashMap<Class<?>, GTMachineListenerList<?>> CACHE = new HashMap<>();
    public static <T extends MachineBlockEntityTemplate<T>> GTMachineListenerList<T> getOrInitListenerList(Class<T> ownerClass) {
        //noinspection unchecked Oooh spooky cast
        return (GTMachineListenerList<T>) CACHE.computeIfAbsent(ownerClass, o -> new GTMachineListenerList<>(ownerClass));
    }
}
