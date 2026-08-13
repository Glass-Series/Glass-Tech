package net.glasslauncher.mods.glasstech.entity.renderer;

import net.glasslauncher.mods.glasstech.entity.MiningLaserEntity;

public class MiningLaserEntityRenderer extends GenericCrossEntityRenderer<MiningLaserEntity> {
    @Override
    public void bindTexture(MiningLaserEntity entity) {
        bindTexture("/assets/glasstech/stationapi/textures/entity/mining_laser.png");
    }
}
