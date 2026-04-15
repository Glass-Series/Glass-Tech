package net.glasslauncher.mods.glasstech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateDoorBlock;
import net.modificationstation.stationapi.api.template.item.TemplateDoorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class GTDoorBlock extends TemplateDoorBlock {
    public GTDoorBlock(Identifier identifier) {
        super(identifier, Material.METAL);
        setSoundGroup(Block.METAL_SOUND_GROUP);
    }

    public static class GTDoorItem extends TemplateDoorItem {
        public GTDoorItem(Identifier identifier, Material arg, Block doorBlock) {
            super(identifier, arg, doorBlock);
        }
    }
}
