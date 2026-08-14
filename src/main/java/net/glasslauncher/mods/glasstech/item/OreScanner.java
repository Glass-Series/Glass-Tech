package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.Identifier;

public abstract class OreScanner extends PowerItem {
    public OreScanner(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, voltageTier, maxEnergy);
        setMaxCount(1);
    }

    abstract public int getOreValue(BlockState state);

    abstract public int getEnergyUsed(ItemStack stack);

    abstract public void sendFeedback(PlayerEntity user, int totalValue);

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        int energyToUse = getEnergyUsed(stack);
        if (removeEnergy(stack, energyToUse) < energyToUse) {
            return stack;
        }

        int totalValue = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < world.getTopY(); y++) {
                    BlockState state = world.getBlockState((int) (user.x - 8 + x), y, (int) (user.z - 8 + z));
                    totalValue += getOreValue(state);
                }
            }
        }
        sendFeedback(user, totalValue);
        return stack;
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return false;
    }

    public static class Advanced extends OreScanner {
        public Advanced(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
            super(identifier, voltageTier, maxEnergy);
        }

        @Override
        public int getOreValue(BlockState state) {
            return OreScannerRegistry.INSTANCE.getOrDefault(state.getBlock(), 0);
        }

        @Override
        public int getEnergyUsed(ItemStack stack) {
            return 128;
        }

        @Override
        public void sendFeedback(PlayerEntity user, int totalValue) {
            user.sendMessage("Ore value in 16x16 area: " + totalValue);
        }
    }

    public static class Basic extends OreScanner {
        public Basic(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
            super(identifier, voltageTier, maxEnergy);
        }

        @Override
        public int getOreValue(BlockState state) {
            return OreScannerRegistry.INSTANCE.getOrDefault(state.getBlock(), 0) == 0 ? 0 : 1;
        }

        @Override
        public int getEnergyUsed(ItemStack stack) {
            return 16;
        }

        @Override
        public void sendFeedback(PlayerEntity user, int totalValue) {
            user.sendMessage("Ores in 16x16 area: " + totalValue);
        }
    }
}
