package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.entity.StickyDynamiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class StickyDynamiteItem extends TemplateItem {
    public StickyDynamiteItem(Identifier identifier) {
        super(identifier);
        this.maxCount = 16;
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        stack.count--;
        world.playSound(user, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) {
            world.spawnEntity(new StickyDynamiteEntity(world, user));
        }

        return stack;
    }
}