package net.glasslauncher.mods.glasstech.item;

import net.glasslauncher.mods.glasstech.blocks.GTFrequencyBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Formatting;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.impl.item.StationNBTSetter;
import org.jetbrains.annotations.NotNull;

public class FrequencyTransmitter extends TemplateItem implements CustomTooltipProvider {
    public FrequencyTransmitter(Identifier identifier) {
        super(identifier);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        if (user.isSneaking()) {
            StationNBTSetter.cast(stack).setStationNbt(new NbtCompound());
        }
        return stack;
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        NbtCompound nbt = stack.getStationNbt();
        BlockState state = world.getBlockState(x, y, z);

        if (!nbt.contains("frequency_x")) {
            if (state.getBlock() instanceof GTFrequencyBlock frequencyBlock) {
                Vec3i frequency = frequencyBlock.getFrequency(world, x, y, z);
                if (frequency != null) {
                    nbt.putInt("frequency_x", frequency.x);
                    nbt.putInt("frequency_y", frequency.y);
                    nbt.putInt("frequency_z", frequency.z);
                    return true;
                }
            }
            return false;
        }

        if (state.getBlock() instanceof GTFrequencyBlock frequencyBlock) {
            return frequencyBlock.setFrequency(world, x, y, z, nbt.getInt("frequency_x"),  nbt.getInt("frequency_y"), nbt.getInt("frequency_z"));
        }
        return false;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        if (stack.getStationNbt().contains("frequency_x")) {
            return new String[] {
                    originalTooltip,
                    Formatting.GRAY + "Frequency set"
            };
        }
        return new String[] {originalTooltip};
    }
}
