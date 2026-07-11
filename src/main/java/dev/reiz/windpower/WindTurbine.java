package dev.reiz.windpower;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

/**
 * Wind Turbine - a passive energy generator powered by wind.
 *
 * Power output scales with placement height and sky exposure.
 * Advanced tiers and above can ignore height requirements entirely.
 * Place it high up with an unobstructed view of the sky for maximum efficiency.
 */
public class WindTurbine extends SlimefunItem implements EnergyNetProvider {

    private final int energyOutput;
    private final int minHeight;
    private final int capacity;
    private final boolean ignoreHeight;

    /**
     * @param itemGroup    The Slimefun item group
     * @param item         The SlimefunItemStack for this turbine
     * @param recipeType   The recipe type (usually ENHANCED_CRAFTING_TABLE)
     * @param recipe       The crafting recipe (3x3 grid)
     * @param energyOutput Energy generated per Slimefun tick (displayed as J/s)
     * @param minHeight    Minimum Y level to generate power (ignored if ignoreHeight is true)
     * @param capacity     Internal energy buffer capacity (J)
     * @param ignoreHeight If true, generate power at any Y level
     */
    public WindTurbine(ItemGroup itemGroup, SlimefunItemStack item,
                       RecipeType recipeType, ItemStack[] recipe,
                       int energyOutput, int minHeight, int capacity,
                       boolean ignoreHeight) {
        super(itemGroup, item, recipeType, recipe);
        this.energyOutput = energyOutput;
        this.minHeight = minHeight;
        this.capacity = capacity;
        this.ignoreHeight = ignoreHeight;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Called every Slimefun tick (default: every 10 server ticks / 0.5s).
     * Returns the energy generated in J per tick.
     *
     * Generation rules:
     * 1. If ignoreHeight is false, must be placed at or above minHeight
     * 2. Full output if the block has direct sky exposure (lightFromSky == 15)
     * 3. Half output if partially exposed (lightFromSky >= 7)
     * 4. No output if fully enclosed
     */
    @Override
    public int getGeneratedOutput(Location l, Config data) {
        Block b = l.getBlock();

        // Height check (skipped for advanced tiers)
        if (!ignoreHeight && b.getY() < minHeight) {
            return 0;
        }

        // Check sky exposure
        int lightFromSky = b.getLightFromSky();

        if (lightFromSky >= 15) {
            // Full output - clear sky above
            return energyOutput;
        } else if (lightFromSky >= 7) {
            // Reduced output - partially blocked
            return energyOutput / 2;
        } else {
            // No output - enclosed or underground
            return 0;
        }
    }

    /**
     * Returns whether this generator can store excess energy.
     */
    @Override
    public boolean isChargeable() {
        return capacity > 0;
    }
}
