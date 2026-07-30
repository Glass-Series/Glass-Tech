package net.glasslauncher.mods.glasstech.blocks.machine.recycler;

import net.glasslauncher.mods.glasstech.VoltageTier;
import net.glasslauncher.mods.glasstech.blocks.machine.RecipeBlockEntityTemplate;
import net.glasslauncher.mods.glasstech.blocks.machine.SlotLayout;
import net.glasslauncher.mods.glasstech.events.init.GlassTechItems;
import net.glasslauncher.mods.glasstech.recipe.machine.BasicMachineRecipe;
import net.glasslauncher.mods.glasstech.recipe.machine.input.RecipeInput;
import net.glasslauncher.mods.glasstech.recipe.machine.output.ChanceRecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RecyclerBlockEntity extends RecipeBlockEntityTemplate<BasicMachineRecipe> {
    public static final BasicMachineRecipe RECIPE = new BasicMachineRecipe(new RecipeInput() {
        @Override
        public boolean matches(ItemStack other) {
            return other != null;
        }

        @Override
        public int getRequiredAmount() {
            return 1;
        }

        @Override
        public List<ItemStack> getRepresentingStacks() {
            return List.of(new ItemStack(Block.COBBLESTONE));
        }
    }, new ChanceRecipeOutput(new ItemStack(GlassTechItems.scrap), 10));

    public RecyclerBlockEntity() {
        super(VoltageTier.LV, 200, 2, 300);
        SlotLayout.createBasic(this);
    }

    @Override
    public String getName() {
        return "Recycler";
    }

    @Override
    public BasicMachineRecipe fetchRecipe(ItemStack[] input) {
        return input[0] != null ? RECIPE : null;
    }
}
