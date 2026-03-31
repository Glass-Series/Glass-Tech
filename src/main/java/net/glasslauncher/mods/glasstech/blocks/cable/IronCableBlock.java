package net.glasslauncher.mods.glasstech.blocks.cable;

import net.glasslauncher.mods.glasstech.WireMaterial;
import net.glasslauncher.mods.glasstech.WireProperties;
import net.glasslauncher.mods.glasstech.blocks.TemplateCableBlock;
import net.glasslauncher.mods.glasstech.events.init.InitListener;
import net.modificationstation.stationapi.api.util.Identifier;

public class IronCableBlock extends TemplateCableBlock {
    public IronCableBlock(Identifier identifier) {
        super(identifier, WireProperties.createIfAbsent(InitListener.NAMESPACE.id("iron"), PIXEL_SIZE * 2, false, WireMaterial.IRON));
    }
}