package net.glasslauncher.mods.glasstech.item;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.alwaysmoreitems.api.SubItemProvider;
import net.glasslauncher.mods.glasstech.GTEnergyBar;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class ElectricPickaxe extends TemplatePickaxeItem implements GTEnergyStorageItem, GTEnergyBar {
    @Setter
    protected int maxEnergy;
    @Setter @Getter
    protected VoltageTier voltageTier;

    public ElectricPickaxe(Identifier identifier, ToolMaterial toolMaterial, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, toolMaterial);
        this.voltageTier = voltageTier;
        this.maxEnergy = maxEnergy;
        setMaxDamage(0);
    }

    @Override
    public float getMiningSpeedMultiplier(PlayerEntity player, ItemStack itemStack, BlockView blockView, BlockPos blockPos, BlockState state) {
        if (getEnergyStored(itemStack) == 0) {
            return 0;
        }
        return Math.max(super.getMiningSpeedMultiplier(player, itemStack, blockView, blockPos, state), IRON_SHOVEL.getMiningSpeedMultiplier(player, itemStack, blockView, blockPos, state));
    }

    @Override
    public boolean isSuitableFor(PlayerEntity player, ItemStack itemStack, BlockView blockView, BlockPos blockPos, BlockState state) {
        if (getEnergyStored(itemStack) == 0) {
            return false;
        }
        return super.isSuitableFor(player, itemStack, blockView, blockPos, state) || IRON_SHOVEL.isSuitableFor(player, itemStack, blockView, blockPos, state);
    }

    @Override
    public boolean postMine(ItemStack stack, int blockId, int x, int y, int z, LivingEntity miner) {
        removeEnergy(stack, 64);
        return true;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        return stack.getStationNbt().getInt("energy");
    }

    @Override
    public int getEnergyCapacity(ItemStack stack) {
        return maxEnergy;
    }

    @Override
    public int setEnergy(ItemStack stack, int value) {
        int takenEnergy = Math.min(value, maxEnergy);
        stack.getStationNbt().putInt("energy", takenEnergy);
        return value - takenEnergy;
    }

    @Override
    public boolean canReceiveEnergy(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxEnergyInput(ItemStack stack) {
        return voltageTier.maxVoltage;
    }

    @Override
    public boolean canExtractEnergy(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxEnergyOutput(ItemStack stack) {
        return canExtractEnergy(stack) ? voltageTier.maxVoltage : 0;
    }

    @SubItemProvider
    public List<ItemStack> getSubItems() {
        ItemStack charged = new ItemStack(this);
        charged.getStationNbt().putInt("energy", getEnergyCapacity(charged));
        return List.of(
                new ItemStack(this),
                charged
        );
    }
}
