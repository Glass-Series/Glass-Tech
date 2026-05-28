package net.glasslauncher.mods.glasstech.blocks.ironfurnace;

import net.danygames2014.nyalib.block.DropInventoryOnBreak;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.Random;

public class IronFurnaceBlock extends TemplateBlockWithEntity implements DropInventoryOnBreak {
    public IronFurnaceBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setTranslationKey(identifier);
        setHardness(2);
        setSoundGroup(METAL_SOUND_GROUP);
        setDefaultState(getDefaultState()
                .with(Properties.LIT, false)
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(Properties.LIT);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState()
                .with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite())
                .with(Properties.LIT, false);
    }

    @Override
    public BlockEntity createBlockEntity() {
        return new IronFurnaceBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        IronFurnaceBlockEntity ironFurnaceBlockEntity = (IronFurnaceBlockEntity) world.getBlockEntity(x, y, z);
        GuiHelper.openGUI(
                player,
                GlassTech.NAMESPACE.id("iron_furnace"),
                ironFurnaceBlockEntity,
                new IronFurnaceScreenHandler(player.inventory, ironFurnaceBlockEntity)
        );
        return true;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        BlockState state = world.getBlockState(x, y, z);
        if (state.get(Properties.LIT)) {
            int meta = state.get(Properties.HORIZONTAL_FACING).getOpposite().getId();
            float particleX = (float)x + 0.5F;
            float particleY = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float particleZ = (float)z + 0.5F;
            float offsetForFront = 0.52F;
            float randomSpot = random.nextFloat() * 0.6F - 0.3F;
            if (meta == 4) {
                world.addParticle("smoke", particleX - offsetForFront, particleY, particleZ + randomSpot, 0.0F, 0.0F, 0.0F);
                world.addParticle("flame", particleX - offsetForFront, particleY, particleZ + randomSpot, 0.0F, 0.0F, 0.0F);
            } else if (meta == 5) {
                world.addParticle("smoke", particleX + offsetForFront, particleY, particleZ + randomSpot, 0.0F, 0.0F, 0.0F);
                world.addParticle("flame", particleX + offsetForFront, particleY, particleZ + randomSpot, 0.0F, 0.0F, 0.0F);
            } else if (meta == 2) {
                world.addParticle("smoke", particleX + randomSpot, particleY, particleZ - offsetForFront, 0.0F, 0.0F, 0.0F);
                world.addParticle("flame", particleX + randomSpot, particleY, particleZ - offsetForFront, 0.0F, 0.0F, 0.0F);
            } else if (meta == 3) {
                world.addParticle("smoke", particleX + randomSpot, particleY, particleZ + offsetForFront, 0.0F, 0.0F, 0.0F);
                world.addParticle("flame", particleX + randomSpot, particleY, particleZ + offsetForFront, 0.0F, 0.0F, 0.0F);
            }

        }
    }
}
