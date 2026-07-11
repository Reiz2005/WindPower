package dev.reiz.windpower;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;

/**
 * Paper Mill — an electric machine that processes bamboo into paper.
 * <p>
 * Extends Slimefun4's {@link AContainer} which handles all GUI logic,
 * energy consumption, recipe processing and energy network integration.
 * <p>
 * Energy: 32 J per Slimefun tick (~64 J/s real).
 * Processing: ~10 seconds per operation.
 * Recipe: 2 bamboo → 1 paper.
 */
@ParametersAreNonnullByDefault
public class PaperMill extends AContainer implements RecipeDisplayItem {

    public PaperMill(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        // 2 bamboo → 1 paper, ~10 seconds processing time
        registerRecipe(10,
            new ItemStack[] {new ItemStack(Material.BAMBOO, 2)},
            new ItemStack[] {new ItemStack(Material.PAPER, 1)}
        );
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.PAPER);
    }

    @Override
    public String getMachineIdentifier() {
        return "WP_PAPER_MILL";
    }

    @Override
    public int getCapacity() {
        return 64;
    }

    @Override
    public int getEnergyConsumption() {
        return 32;
    }
}
