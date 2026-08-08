package net.glasslauncher.mods.glasstech.blocks.personalsafe;

import net.danygames2014.uniwrench.api.Wrenchable;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Formatting;

import java.util.Objects;

public class PersonalSafeBlockEntity extends ChestBlockEntity implements Wrenchable {
    protected String owner = "";

    @Override
    public String getName() {
        return "Personal Safe";
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        owner = nbt.getString("owner");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("owner", owner);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (!super.canPlayerUse(player)) {
            return false;
        }
        if (owner.isEmpty()) {
            owner = player.name;
            if (owner == null) { // somehow
                owner = "";
            }
        }
        if (!Objects.equals(player.name, owner)) {
            player.sendMessage(Formatting.RED + "This safe isn't owned by you!");
            return false;
        }
        return true;
    }
}
