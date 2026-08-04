package net.glasslauncher.mods.glasstech.item;

import lombok.Getter;
import lombok.Setter;
import net.glasslauncher.mods.alwaysmoreitems.api.SubItemProvider;
import net.glasslauncher.mods.glasstech.GTCustomAttackDamage;
import net.glasslauncher.mods.glasstech.GTEnergyBar;
import net.glasslauncher.mods.glasstech.VoltageTier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class NanoSaberItem extends TemplateSwordItem implements GTEnergyStorageItem, GTEnergyBar, GTCustomAttackDamage {
    @Setter
    protected int maxEnergy;
    @Setter @Getter
    protected VoltageTier voltageTier;

    public int onTexture1;
    public int onTexture2;

    public NanoSaberItem(Identifier identifier, VoltageTier voltageTier, int maxEnergy) {
        super(identifier, ToolMaterial.DIAMOND);
        this.voltageTier = voltageTier;
        this.maxEnergy = maxEnergy;
        setMaxDamage(0);
    }

    @Override
    public int getTextureId(ItemStack itemStack) {
        if (itemStack.getStationNbt().getBoolean("active")) {
            return random.nextInt(2) == 0 ? onTexture1 : onTexture2;
        }
        return super.getTextureId(itemStack);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        if (getEnergyStored(stack) == 0) {
            return stack;
        }
        stack.getStationNbt().putBoolean("active", !stack.getStationNbt().getBoolean("active"));
        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getStationNbt().getBoolean("active")) {
            removeEnergy(stack, 16);
        }
        if (getEnergyStored(stack) == 0) {
            stack.getStationNbt().putBoolean("active", false);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
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

    @Override
    public int glasstech$getAttackDamage(ItemStack itemStack, Entity attacked) {
        if (itemStack.getStationNbt().getBoolean("active")) {
            return 16;
        }
        return 1;
    }
}
