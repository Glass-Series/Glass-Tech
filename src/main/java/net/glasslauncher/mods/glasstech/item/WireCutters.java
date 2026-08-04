package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.WorldUtil;
import net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplateShearsItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class WireCutters extends TemplateShearsItem {
    public WireCutters(Identifier identifier) {
        super(identifier);
        setMaxDamage(1536);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        BlockState state = world.getBlockState(x, y, z);
        if (!(state.getBlock() instanceof TemplateCableBlock templateCableBlock)) {
            return super.useOnBlock(stack, user, world, x, y, z, side);
        }

        stack.damage(1, user);
        WorldUtil.breakBlockWithParticles(world, x, y, z, templateCableBlock.id);
        templateCableBlock.dropStacks(world, x, y, z, templateCableBlock.id);

        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, int blockId, int x, int y, int z, LivingEntity miner) {
        stack.damage(1, miner);
        return false;
    }
}
