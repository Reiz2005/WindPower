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
 * Paper Mill — an electric machine that processes plant materials into paper.
 * <p>
 * Extends Slimefun4's {@link AContainer} which handles all GUI logic,
 * energy consumption, recipe processing and energy network integration.
 * <p>
 * Energy: 32 J per Slimefun tick (~64 J/s real).
 * <p>
 * Recipes:
 * <ul>
 *   <li>2x bamboo → 1x paper (~10s)</li>
 *   <li>1x sugar cane → 1x paper (~5s)</li>
 *   <li>16x leaves (any type) → 5x paper (~15s)</li>
 * </ul>
 */
@ParametersAreNonnullByDefault
public class PaperMill extends AContainer implements RecipeDisplayItem {

    public PaperMill(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // Slimefun requires setProcessingSpeed() before register(), otherwise the item is disabled
        setProcessingSpeed(1);
    }

    @Override
    protected void registerDefaultRecipes() {
        // 2x bamboo → 1x paper, ~10 seconds
        registerRecipe(10,
            new ItemStack[] {new ItemStack(Material.BAMBOO, 2)},
            new ItemStack[] {new ItemStack(Material.PAPER, 1)}
        );

        // 1x sugar cane → 1x paper, ~5 seconds
        registerRecipe(5,
            new ItemStack[] {new ItemStack(Material.SUGAR_CANE, 1)},
            new ItemStack[] {new ItemStack(Material.PAPER, 1)}
        );

        // 16x leaves → 5x paper, ~15 seconds (all vanilla leaf types)
        ItemStack paper = new ItemStack(Material.PAPER, 5);
        Material[] leaves = {
            Material.OAK_LEAVES, Material.SPRUCE_LEAVES, Material.BIRCH_LEAVES,
            Material.JUNGLE_LEAVES, Material.ACACIA_LEAVES, Material.DARK_OAK_LEAVES,
            Material.MANGROVE_LEAVES, Material.CHERRY_LEAVES,
            Material.AZALEA_LEAVES, Material.FLOWERING_AZALEA_LEAVES,
        };
        for (Material leaf : leaves) {
            registerRecipe(15,
                new ItemStack[] {new ItemStack(leaf, 16)},
                new ItemStack[] {paper}
            );
        }
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
