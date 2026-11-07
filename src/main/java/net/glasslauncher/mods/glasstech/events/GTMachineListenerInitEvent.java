package net.glasslauncher.mods.glasstech.events;

import net.glasslauncher.mods.glasstech.blocks.machine.MachineBlockEntityTemplate;
import net.mine_diver.unsafeevents.Event;

// The type parameter here is only here so folk don't have to do a million casts
public class GTMachineListenerInitEvent<T extends MachineBlockEntityTemplate<T>> extends Event {
    public final GTMachineListenerList<T> listenerList;
    public final Class<T> ownerClass;

    public GTMachineListenerInitEvent(GTMachineListenerList<T> listenerList) {
        this.listenerList = listenerList;
        ownerClass = listenerList.ownerClass;
    }

    public void add(GTMachineListener<T> listener) {
        listenerList.add(listener);
    }
}
