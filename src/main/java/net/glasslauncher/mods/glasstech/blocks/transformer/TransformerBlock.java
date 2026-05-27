package net.glasslauncher.mods.glasstech.blocks.transformer;

import lombok.SneakyThrows;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.PowerStorageBlockTemplate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class TransformerBlock extends PowerStorageBlockTemplate {

    private final Class<? extends BlockEntity> blockEntityClass;

    public TransformerBlock(Identifier identifier, Material material, Class<? extends BlockEntity> blockEntityClass) {
        super(identifier, material);
        this.blockEntityClass = blockEntityClass;
        setTranslationKey(identifier);
    }

    @Override
    @SneakyThrows // If we get either exception from newInstance, something is extremely wrong, or a modder was stupid. Either case, not my problem.
    protected BlockEntity createBlockEntity() {
        //noinspection deprecation
        return blockEntityClass.newInstance();
    }
}
