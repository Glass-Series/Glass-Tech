package net.glasslauncher.mods.glasstech.client

import com.google.common.collect.ImmutableList
import net.modificationstation.stationapi.api.block.BlockState
import net.modificationstation.stationapi.api.client.render.model.BakedModel
import net.modificationstation.stationapi.api.client.render.model.BakedQuad
import net.modificationstation.stationapi.api.client.render.model.json.ModelOverrideList
import net.modificationstation.stationapi.api.client.render.model.json.ModelTransformation
import net.modificationstation.stationapi.api.client.texture.Sprite
import net.modificationstation.stationapi.api.util.math.Direction
import java.util.*

class PipeModel : BakedModel {
    override fun getQuads(state: BlockState?, face: Direction?, random: Random?): ImmutableList<BakedQuad> {
        TODO("Not yet implemented")
    }

    override fun useAmbientOcclusion(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasDepth() = true

    override fun isSideLit(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBuiltin(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSprite(): Sprite {
        TODO("Not yet implemented")
    }

    override fun getTransformation(): ModelTransformation {
        TODO("Not yet implemented")
    }

    override fun getOverrides(): ModelOverrideList {
        TODO("Not yet implemented")
    }
}