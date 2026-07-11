package dev.reiz.windpower;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

/**
 * Research tree for WindPower addon.
 *
 * Players must spend XP levels to unlock each tier, creating a natural
 * progression from basic materials to quantum wind turbines.
 *
 * Tree layout:
 *
 *   [基础材料] (5 LV)
 *       |
 *   [先进材料] (10 LV)      [造纸技术] (8 LV)
 *       |                         |
 *   [基础风力发电] (15 LV)       (造纸机)
 *       |  ->  [强化风力发电] (20 LV)
 *                                      |
 *   [量子能源技术] (40 LV)  <-----------┘
 *       |
 *   [高级风力发电] (30 LV)
 *       |
 *   [量子风力发电] (50 LV)
 *       |
 *   [量子MK I] (65 LV)
 *       |
 *   [量子MK II] (85 LV)
 *       |
 *   [时空共振技术] (100 LV)
 *       |
 *   [量子MK III] (120 LV)
 */
public class WindPowerResearches {

    // Research numeric IDs — using 8500+ range to avoid conflicts
    private static final int ID_BASIC_MATERIALS    = 8500;
    private static final int ID_ADVANCED_MATERIALS  = 8501;
    private static final int ID_BASIC_TURBINE      = 8502;
    private static final int ID_REINFORCED_TURBINE  = 8503;
    private static final int ID_ADVANCED_TURBINE    = 8504;
    private static final int ID_QUANTUM_TECH        = 8505;
    private static final int ID_QUANTUM_TURBINE     = 8506;
    private static final int ID_QUANTUM_MK1         = 8507;
    private static final int ID_QUANTUM_MK2         = 8508;
    private static final int ID_SPACETIME_TECH      = 8509;
    private static final int ID_QUANTUM_MK3         = 8510;
    private static final int ID_PAPER_MILL          = 8511;

    public static void setup(WindPower plugin) {

        // ---- Tier 1: Basic Materials ----
        Research basicMaterials = new Research(
            new NamespacedKey(plugin, "basic_materials"),
            ID_BASIC_MATERIALS,
            "风力材料基础",
            5
        );
        addItems(basicMaterials,
            WindPowerItems.ID_CARBON_FIBER,
            WindPowerItems.ID_CARBON_FIBER_SHEET,
            WindPowerItems.ID_TITANIUM_INGOT,
            WindPowerItems.ID_MAGNETIC_ALLOY
        );
        basicMaterials.register();

        // ---- Paper Mill (low-cost standalone machine) ----
        Research paperMill = new Research(
            new NamespacedKey(plugin, "paper_mill"),
            ID_PAPER_MILL,
            "造纸技术",
            8
        );
        addItems(paperMill, WindPowerItems.ID_PAPER_MILL);
        paperMill.register();

        // ---- Tier 2: Advanced Materials ----
        Research advancedMaterials = new Research(
            new NamespacedKey(plugin, "advanced_materials"),
            ID_ADVANCED_MATERIALS,
            "先进合成材料",
            10
        );
        addItems(advancedMaterials,
            WindPowerItems.ID_WIND_CRYSTAL,
            WindPowerItems.ID_REINFORCED_BLADE,
            WindPowerItems.ID_AEROGEL
        );
        advancedMaterials.register();

        // ---- Tier 3: Basic Wind Turbine ----
        Research basicTurbine = new Research(
            new NamespacedKey(plugin, "basic_wind_turbine"),
            ID_BASIC_TURBINE,
            "基础风力发电",
            15
        );
        addItems(basicTurbine, WindPowerItems.ID_TURBINE_BASIC);
        basicTurbine.register();

        // ---- Tier 4: Reinforced Wind Turbine ----
        Research reinforcedTurbine = new Research(
            new NamespacedKey(plugin, "reinforced_wind_turbine"),
            ID_REINFORCED_TURBINE,
            "强化风力发电",
            20
        );
        addItems(reinforcedTurbine, WindPowerItems.ID_TURBINE_REINFORCED);
        reinforcedTurbine.register();

        // ---- Tier 5: Quantum Energy Technology (materials) ----
        Research quantumTech = new Research(
            new NamespacedKey(plugin, "quantum_energy_tech"),
            ID_QUANTUM_TECH,
            "量子能源技术",
            40
        );
        addItems(quantumTech,
            WindPowerItems.ID_QUANTUM_CORE,
            WindPowerItems.ID_STORM_MATRIX
        );
        quantumTech.register();

        // ---- Tier 6: Advanced Wind Turbine ----
        Research advancedTurbine = new Research(
            new NamespacedKey(plugin, "advanced_wind_turbine"),
            ID_ADVANCED_TURBINE,
            "高级风力发电",
            30
        );
        addItems(advancedTurbine, WindPowerItems.ID_TURBINE_ADVANCED);
        advancedTurbine.register();

        // ---- Tier 7: Quantum Wind Turbine ----
        Research quantumTurbine = new Research(
            new NamespacedKey(plugin, "quantum_wind_turbine"),
            ID_QUANTUM_TURBINE,
            "量子风力发电",
            50
        );
        addItems(quantumTurbine, WindPowerItems.ID_TURBINE_QUANTUM);
        quantumTurbine.register();

        // ---- Tier 8: Quantum MK I ----
        Research quantumMk1 = new Research(
            new NamespacedKey(plugin, "quantum_mk1"),
            ID_QUANTUM_MK1,
            "量子MK I 风力发电",
            65
        );
        addItems(quantumMk1, WindPowerItems.ID_TURBINE_QUANTUM_MK1);
        quantumMk1.register();

        // ---- Tier 9: Quantum MK II ----
        Research quantumMk2 = new Research(
            new NamespacedKey(plugin, "quantum_mk2"),
            ID_QUANTUM_MK2,
            "量子MK II 风力发电",
            85
        );
        addItems(quantumMk2, WindPowerItems.ID_TURBINE_QUANTUM_MK2);
        quantumMk2.register();

        // ---- Tier 10: Spacetime Resonance Technology (ultimate material) ----
        Research spacetimeTech = new Research(
            new NamespacedKey(plugin, "spacetime_resonance_tech"),
            ID_SPACETIME_TECH,
            "时空共振技术",
            100
        );
        addItems(spacetimeTech, WindPowerItems.ID_SPACETIME_CORE);
        spacetimeTech.register();

        // ---- Tier 11: Quantum MK III ----
        Research quantumMk3 = new Research(
            new NamespacedKey(plugin, "quantum_mk3"),
            ID_QUANTUM_MK3,
            "量子MK III 风力发电",
            120
        );
        addItems(quantumMk3, WindPowerItems.ID_TURBINE_QUANTUM_MK3);
        quantumMk3.register();
    }

    /**
     * Helper: add items to a research by their Slimefun IDs.
     * Silently skips IDs that are not yet registered (defensive).
     */
    private static void addItems(Research research, String... ids) {
        for (String id : ids) {
            SlimefunItem item = SlimefunItem.getById(id);
            if (item != null) {
                research.addItems(item);
            }
        }
    }
}
