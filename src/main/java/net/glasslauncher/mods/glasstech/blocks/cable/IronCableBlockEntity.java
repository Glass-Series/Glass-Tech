package net.glasslauncher.mods.glasstech.blocks.cable;

import net.modificationstation.stationapi.api.state.property.BooleanProperty;;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.teamterminus.machineessentials.energy.electric.template.ElectricWireBlockEntity;

import java.util.Map;

public class IronCableBlockEntity extends ElectricWireBlockEntity {
    // Fucking beta directions
    public static final Map<BooleanProperty, Direction> DIR_PROPS = Map.of(
            Properties.NORTH, Direction.NORTH.rotateYClockwise(),
            Properties.SOUTH, Direction.SOUTH.rotateYClockwise(),
            Properties.EAST, Direction.EAST.rotateYClockwise(),
            Properties.WEST, Direction.WEST.rotateYClockwise(),
            Properties.UP, Direction.UP,
            Properties.DOWN, Direction.DOWN
    );

    public void onOvercurrent(long amps) {

    }

    public void onOvervoltage(long voltage) {

    }
}