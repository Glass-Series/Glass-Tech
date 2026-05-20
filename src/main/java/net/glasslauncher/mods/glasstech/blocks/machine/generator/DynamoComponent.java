package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import net.modificationstation.stationapi.api.util.math.Direction;

public interface DynamoComponent {
    boolean isGenerating();
    int getOutput();
    boolean isConnected(Direction dynamoDirection);
}
