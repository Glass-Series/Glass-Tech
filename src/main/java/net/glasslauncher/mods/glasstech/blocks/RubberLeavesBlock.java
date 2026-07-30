package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.util.Identifier;

public class RubberLeavesBlock extends LeavesBlockTemplate {
    public RubberLeavesBlock(Identifier identifier) {
        super(identifier);
    }

    @Override
    public int getColor(int meta) {
        return 4688199;
    }

    @Override
    public int getColorMultiplier(BlockView blockView, int x, int y, int z) {
        return 4688199;
    }
}
