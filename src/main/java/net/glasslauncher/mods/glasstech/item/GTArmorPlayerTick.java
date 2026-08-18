package net.glasslauncher.mods.glasstech.item;

import net.minecraft.entity.player.PlayerEntity;

public interface GTArmorPlayerTick {
    int HEAD = 3;
    int CHEST = 2;
    int LEGS = 1;
    int BOOTS = 0;



    void tick(PlayerEntity player, int armorSlot);
}
