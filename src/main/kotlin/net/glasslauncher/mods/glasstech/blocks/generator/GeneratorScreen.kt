package net.glasslauncher.mods.glasstech.blocks.generator

import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerInventory
import net.modificationstation.stationapi.api.util.Formatting
import org.lwjgl.opengl.GL11
import java.awt.Rectangle

class GeneratorScreen(val playerInventory: PlayerInventory, val generatorBlockEntity: GeneratorBlockEntity) : HandledScreen(
    GeneratorScreenHandler(playerInventory, generatorBlockEntity)
) {

    companion object {
        val euTooltipRect = Rectangle(80, 10, 64, 18)
    }

    init {
        glassguis_setName("Generator")
    }

    override fun drawForeground() {
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        super.render(mouseX, mouseY, delta)

        glassguis_tooltip(this, listOf("${if (generatorBlockEntity.energy < generatorBlockEntity.energyCapacity / 5) Formatting.RED else Formatting.AQUA}${generatorBlockEntity.energy}${Formatting.FORMATTING_CODE_PREFIX}r/${Formatting.AQUA}${generatorBlockEntity.energyCapacity} EU"), euTooltipRect, mouseX, mouseY)
    }

    override fun drawBackground(tickDelta: Float) {
        glassguis_renderBackground(this)

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_BLEND)
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_background.png", 80, 10)
        glassguis_drawImagePercentage(this, "/assets/glasstech/stationapi/textures/gui/energy_bar.png", 82, 12, (generatorBlockEntity.energy.toFloat() / generatorBlockEntity.energyCapacity))
        glassguis_drawImage(this, "/assets/glasstech/stationapi/textures/gui/energy_overlay.png", 80, 10)

        glassguis_drawSlots(this)
        GL11.glDisable(GL11.GL_BLEND)

    }
}