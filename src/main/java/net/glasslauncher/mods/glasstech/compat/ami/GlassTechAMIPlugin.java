package net.glasslauncher.mods.glasstech.compat.ami;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.alwaysmoreitems.api.*;
import net.glasslauncher.mods.glasstech.GlassTech;
import net.glasslauncher.mods.glasstech.blocks.machine.MachineScreenTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.canner.CannerScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.compressor.CompressorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.compressor.CompressorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer.ElectrolyzerBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.electrolyzer.ElectrolyzerScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.extractor.ExtractorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.extractor.ExtractorScreen;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorBlockEntity;
import net.glasslauncher.mods.glasstech.blocks.machine.macerator.MaceratorScreen;
import net.glasslauncher.mods.glasstech.events.init.GlassTechBlocks;
import net.glasslauncher.mods.glasstech.events.init.recipes.CannerRecipes;
import net.glasslauncher.mods.glasstech.events.init.recipes.CompressorRecipes;
import net.glasslauncher.mods.glasstech.events.init.recipes.ExtractorRecipes;
import net.glasslauncher.mods.glasstech.events.init.recipes.MaceratorRecipes;
import net.glasslauncher.mods.glasstech.recipe.machine.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Identifier;

public class GlassTechAMIPlugin implements ModPluginProvider {
    public static final Identifier ID = GlassTech.NAMESPACE.id("machines");

    @Override
    public String getName() {
        return "Glass Tech Machines";
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public void onAMIHelpersAvailable(AMIHelpers amiHelpers) {

    }

    @Override
    public void onItemRegistryAvailable(ItemRegistry itemRegistry) {

    }

    @Override
    public void register(ModRegistry registry) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            // These aren't synced yet anyway
            registerMachines(registry);
        }
    }

    @Environment(EnvType.CLIENT)
    private void registerMachines(ModRegistry registry) {
        registerMachine(registry, MaceratorRecipes.MaceratorRecipe.class, "Macerator", MaceratorRecipeRegistry.INSTANCE, new MaceratorScreen(null, new MaceratorBlockEntity()));
        registerMachine(registry, CompressorRecipes.CompressorRecipe.class, "Compressor", CompressorRecipeRegistry.INSTANCE, new CompressorScreen(null, new CompressorBlockEntity()));
        registerMachine(registry, CannerRecipes.CannerRecipe.class, "Canner", CannerRecipeRegistry.INSTANCE, new CannerScreen(null, new CannerBlockEntity()));
        registerMachine(registry, ElectrolyzerMachineRecipe.class, "Electrolyzer", ElectrolyzerRecipeRegistry.INSTANCE, new ElectrolyzerScreen(null, new ElectrolyzerBlockEntity()));
        registerMachine(registry, ExtractorRecipes.ExtractorRecipe.class, "Extractor", ExtractorRecipeRegistry.INSTANCE, new ExtractorScreen(null, new ExtractorBlockEntity()));
    }

    @Environment(EnvType.CLIENT)
    private <T extends MachineScreenTemplate<V>, V extends RecipeBlockEntityTemplate<?>, C extends BasicMachineRecipe> void registerMachine(ModRegistry registry, Class<C> cls, String name, RecipeRegistryTemplate<C> recipeRegistry, T screenTemplate) {
        BasicMachineCategory<T, V> basicMachineCategory = new BasicMachineCategory<>("glasstech_" + name, name, screenTemplate);
        registry.addRecipeCategories(
                basicMachineCategory
        );
        registry.addRecipeHandlers(
                new AMIMachineRecipeHandler<>("glasstech_" + name, cls)
        );

        registry.addRecipes(recipeRegistry.registry.values().stream().toList());

    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry) {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound recipe) {
        return null;
    }

    @Override
    public void updateBlacklist(AMIHelpers amiHelpers) {
        amiHelpers.getItemBlacklist().addItemToBlacklist(new ItemStack(GlassTechBlocks.reinforcedDoorBlock));
    }
}
