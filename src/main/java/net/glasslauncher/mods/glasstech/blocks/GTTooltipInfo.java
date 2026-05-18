package net.glasslauncher.mods.glasstech.blocks;

import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public interface GTTooltipInfo {
    int getMaxInputVoltage(@Nullable Direction direction);
    int getMaxInputAmps();
}
