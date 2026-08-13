package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.entity.MiningLaserEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class MiningLaser extends PowerItem implements GTItemWithModes, CustomTooltipProvider {

    public MiningLaser(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, voltageTier, maxEnergy);
        setHandheld();
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return false;
    }

    public LaserMode getMode(ItemStack stack) {
        return LaserMode.values()[stack.getStationNbt().getInt("mode")];
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        LaserMode mode = getMode(stack);

        if (removeEnergy(stack, mode.powerUse) < mode.powerUse) {
            return stack;
        }

        world.spawnEntity(new MiningLaserEntity(world, user, mode.range, mode.explosive));

        return stack;
    }

    @Override
    public void cycleMode(PlayerEntity player, ItemStack stack) {
        LaserMode mode = getMode(stack);
        stack.getStationNbt().putInt("mode", (mode.ordinal() + 1) % (LaserMode.values().length));
        player.sendMessage("Mode: " + getMode(stack).name());
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Mode: " + getMode(stack).name(),
        };
    }

    public enum LaserMode {
        MINING(8, 125, false),
        SHOTGUN(6, 1000, false),
        EXPLOSIVE(12, 500, true),
        ;

        public final int range;
        public final int powerUse;
        public final boolean explosive;

        LaserMode(int range, int powerUse, boolean explosive) {
            this.range = range;
            this.powerUse = powerUse;
            this.explosive = explosive;
        }
    }
}
