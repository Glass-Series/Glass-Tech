package net.glasslauncher.mods.glasstech.mixin.nyalib;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.danygames2014.nyalib.energy.EnergyConsumer;
import net.danygames2014.nyalib.network.Network;
import net.danygames2014.nyalib.network.NetworkPath;
import net.danygames2014.nyalib.network.NetworkType;
import net.danygames2014.nyalib.network.energy.EnergyNetwork;
import net.glasslauncher.mods.glasstech.blocks.LossyEnergyConductor;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnergyNetwork.class)
public class EnergyNetworkMixin extends Network {
    @Final
    @Shadow
    private Object2ObjectOpenHashMap<Vec3i, EnergyNetwork.EnergyFlowEntry> energyFlow;

    public EnergyNetworkMixin(World world, NetworkType type) {
        super(world, type);
    }

    @WrapOperation(method = "traverseEnergy", at = @At(value = "INVOKE", target = "Lnet/danygames2014/nyalib/energy/EnergyConsumer;receiveEnergy(Lnet/modificationstation/stationapi/api/util/math/Direction;II)I"))
    private int lossyEnergy(EnergyConsumer instance, Direction direction, int voltage, int energy, Operation<Integer> original, @Local(argsOnly = true) NetworkPath path) {
        float lostEnergy = 0;
        for (Vec3i node : path.path) {
            EnergyNetwork.EnergyFlowEntry entry = energyFlow.get(node);
            if (entry != null && entry.conductor instanceof LossyEnergyConductor lossyEnergyConductor) {
                lostEnergy += lossyEnergyConductor.getLossPerBlock();
            }
        }
        int newEnergy = energy - (int) Math.ceil(lostEnergy);
        if (newEnergy <= 0) { // Oops we ate it all :)
            return energy;
        }
        int providedEnergy = original.call(instance, direction, voltage, newEnergy);
        return Math.min(providedEnergy + (int) Math.ceil(lostEnergy), energy);
    }
}
