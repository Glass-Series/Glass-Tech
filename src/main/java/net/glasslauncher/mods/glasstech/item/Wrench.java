package net.glasslauncher.mods.glasstech.item;

import net.danygames2014.uniwrench.item.WrenchBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class Wrench extends WrenchBase {
    public Wrench(Identifier identifier) {
        super(identifier);
        setMaxDamage(128);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        boolean didSomething = super.useOnBlock(stack, user, world, x, y, z, side);
        if (didSomething) {
            stack.damage(1, user);
        }
        return didSomething;
    }
}
