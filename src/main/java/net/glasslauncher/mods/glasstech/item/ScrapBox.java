package net.glasslauncher.mods.glasstech.item;

import lombok.Getter;
import lombok.SneakyThrows;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ScrapBox extends TemplateItem {
    public static final List<LootDrop> DROPS = new ArrayList<>();
    @Getter
    private static float CACHED_TOTAL_WEIGHT;

    public static void addDrop(ItemStack drop, float weight) {
        DROPS.add(new LootDrop(drop, weight));
    }

    public static void addDrop(Block drop, float weight) {
        DROPS.add(new LootDrop(new ItemStack(drop), weight));
    }

    public static void addDrop(Item drop, float weight) {
        DROPS.add(new LootDrop(new ItemStack(drop), weight));
    }
    
    public static void initDefaultDrops() {
        addDrop(Block.DIRT, 5f);
        addDrop(Item.STICK, 4f);
        addDrop(Block.GRASS, 3f);
        addDrop(Block.GRAVEL, 3f);
        addDrop(Block.NETHERRACK, 2f);
        addDrop(Item.APPLE, 1.5f);
        addDrop(Item.BREAD, 1.5f);
        addDrop(GlassTechItems.cannedFood, 1.5f);
        addDrop(Item.WOODEN_SWORD, 1f);
        addDrop(Item.WOODEN_SHOVEL, 1f);
        addDrop(Item.WOODEN_PICKAXE, 1f);
        addDrop(Block.SOUL_SAND, 1f);
        addDrop(Item.SIGN, 1f);
        addDrop(Item.LEATHER, 1f);
        addDrop(Item.FEATHER, 1f);
        addDrop(Item.COOKED_PORKCHOP, 0.9f);
        addDrop(Block.PUMPKIN, 0.9f);
        addDrop(Item.MINECART, 0.9f);
        addDrop(Item.REDSTONE, 0.9f);
        addDrop(GlassTechItems.rubber, 0.8f);
        addDrop(Item.GLOWSTONE_DUST, 0.8f);
        addDrop(GlassTechItems.coalDust, 0.8f);
        addDrop(GlassTechItems.copperDust, 0.8f);
        addDrop(GlassTechItems.tinDust, 0.8f);
        addDrop(GlassTechBlocks.copperOreBlock, 0.7f);
        addDrop(GlassTechBlocks.tinOreBlock, 0.7f);
        addDrop(GlassTechItems.plantBall, 0.7f);
        addDrop(GlassTechItems.suBattery, 0.7f);
        addDrop(GlassTechItems.ironDust, 0.7f);
        addDrop(GlassTechItems.goldDust, 0.7f);
        addDrop(Item.SLIMEBALL, 0.6f);
        addDrop(Block.IRON_ORE, 0.5f);
        addDrop(Item.GOLDEN_HELMET, 0.5f);
        addDrop(Block.GOLD_ORE, 0.5f);
        addDrop(Item.CAKE, 0.5f);
        addDrop(Item.DIAMOND, 0.1f);
    }

    public ScrapBox(Identifier identifier) {
        super(identifier);
    }

    @SneakyThrows
    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        if (world.isRemote) {
            return stack;
        }

        if (CACHED_TOTAL_WEIGHT == 0) {
            CACHED_TOTAL_WEIGHT = DROPS.stream().map(e -> e.weight).reduce(Float::sum).orElseThrow();
        }

        double randomDouble = random.nextDouble() * CACHED_TOTAL_WEIGHT;
        int i = 0;
        for (; i < DROPS.size() - 1; ++i) {
            randomDouble -= DROPS.get(i).weight;
            if (randomDouble <= 0.0) {
                break;
            }
        }

        // This is a workaround cause bald stapi man decided to not deep copy nbt on item copy
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        NbtCompound nbtToCopy = new NbtCompound();
        DROPS.get(i).stack.writeNbt(nbtToCopy);
        nbtToCopy.write(dataOutputStream);
        dataOutputStream.flush();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        NbtCompound compound = new NbtCompound();
        compound.read(new DataInputStream(inputStream));

        ItemStack itemStack = new ItemStack(compound);

        float var6 = 0.7F;
        double randomX = world.random.nextFloat() * var6 * 0.5;
        double randomY = world.random.nextFloat() * var6 * 0.5;
        double randomZ = world.random.nextFloat() * var6 * 0.5;
        ItemEntity itemEntity = new ItemEntity(world, user.x + randomX, user.y + randomY, user.z + randomZ, itemStack);
        world.spawnEntity(itemEntity);

        stack.count--;
        return stack;
    }

    public record LootDrop(ItemStack stack, float weight) {}
}
