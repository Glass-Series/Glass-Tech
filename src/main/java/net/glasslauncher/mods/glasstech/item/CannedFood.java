package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class CannedFood extends TemplateFoodItem {
    public CannedFood(Identifier identifier) {
        super(identifier, 2, false);
        setMaxCount(16);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        int stackSize = stack.count;
        stack = super.use(stack, world, user);

        if (stack != null && stackSize == stack.count) {
            return stack;
        }

        ItemStack tin = new ItemStack(GlassTechItems.emptyCell);
        if (!user.inventory.addStack(tin)) {
            user.dropItem(tin);
        }
        return stack;
    }
}
