package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldRegion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.util.Identifier;

import static net.glasslauncher.mods.glasstech.GTProperties.FOAM_COLOR;

public class GTHardenedFoamBlock extends GTTemplateBlock {

    public GTHardenedFoamBlock(Identifier identifier) {
        super(identifier, Material.STONE, STONE_SOUND_GROUP);
        setHardness(1);
        setResistance(5);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FOAM_COLOR);
    }

    @Override
    public int getColorMultiplier(BlockView blockView, int x, int y, int z) {
        World world = null;
        if (blockView instanceof WorldRegion worldRegion) {
            world = worldRegion.world;
        }
        else if (blockView instanceof World world_) {
            world = world_;
        }

        if (world != null) {
            BlockState state = world.getBlockState(x, y, z);
            if (state.get(FOAM_COLOR) == null) {
                return 0;
            }
            return state.get(FOAM_COLOR).color;
        }

        return super.getColorMultiplier(blockView, x, y, z);
    }

    @Override
    public int getColor(int meta) {
        return FoamColor.DEFAULT.color;
    }
}
