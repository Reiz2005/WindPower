package dev.reiz.windpower;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

/**
 * Handles registration of all WindPower items: materials and wind turbines.
 *
 * Material tiers:
 *   Tier 1 (basic):    carbon fiber, carbon fiber sheet, titanium, magnetic alloy
 *   Tier 2 (advanced): wind crystal, reinforced blade, aerogel
 *   Tier 3 (quantum):  quantum core, storm matrix
 *   Tier 4 (ultimate): spacetime resonance core
 *
 * Turbine tiers (all use custom player-head textures):
 *   Basic (20 J/s) -> Reinforced (35 J/s) -> Advanced (45 J/s, ignore height)
 *   -> Quantum (150 J/s) -> Quantum MK I (180 J/s) -> Quantum MK II (256 J/s)
 *   -> Quantum MK III (512 J/s)
 */
public class WindPowerItems {

    // ===== Material IDs =====
    public static final String ID_CARBON_FIBER = "WP_CARBON_FIBER";
    public static final String ID_CARBON_FIBER_SHEET = "WP_CARBON_FIBER_SHEET";
    public static final String ID_TITANIUM_INGOT = "WP_TITANIUM_INGOT";
    public static final String ID_MAGNETIC_ALLOY = "WP_MAGNETIC_ALLOY";
    public static final String ID_WIND_CRYSTAL = "WP_WIND_CRYSTAL";
    public static final String ID_REINFORCED_BLADE = "WP_REINFORCED_BLADE";
    public static final String ID_AEROGEL = "WP_AEROGEL";
    public static final String ID_QUANTUM_CORE = "WP_QUANTUM_CORE";
    public static final String ID_STORM_MATRIX = "WP_STORM_MATRIX";
    public static final String ID_SPACETIME_CORE = "WP_SPACETIME_RESONANCE_CORE";

    // ===== Turbine IDs =====
    public static final String ID_TURBINE_BASIC = "WP_WIND_TURBINE_BASIC";
    public static final String ID_TURBINE_REINFORCED = "WP_WIND_TURBINE_REINFORCED";
    public static final String ID_TURBINE_ADVANCED = "WP_WIND_TURBINE_ADVANCED";
    public static final String ID_TURBINE_QUANTUM = "WP_WIND_TURBINE_QUANTUM";
    public static final String ID_TURBINE_QUANTUM_MK1 = "WP_WIND_TURBINE_QUANTUM_MK1";
    public static final String ID_TURBINE_QUANTUM_MK2 = "WP_WIND_TURBINE_QUANTUM_MK2";
    public static final String ID_TURBINE_QUANTUM_MK3 = "WP_WIND_TURBINE_QUANTUM_MK3";

    // ===== Custom Head Textures (from minecraft-heads.com / mc-heads.com) =====
    // Carbonado / Black Diamond (dark crystalline texture for carbon fiber)
    private static final String HEAD_CARBON_FIBER =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQ3YWZiNzBhZjVmNzQ3MWEzMWNjN2MxNDVhMDA4NzZhYjkzMzRmNmE3NTNkOWI0YmE0NzMxYmM2YzFmZTZiIn19fQ==";
    // Machine Part (yellow industrial component)
    private static final String HEAD_MACHINE_PART =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWIzYWMzZmZiOTVlY2IwYWM1N2NhYjQ5MzBhM2FiNzQ2MjExYjU5ODg2MWEzNTJkZjNlM2M2ZTljNjk5OGUifX19";
    // Cargo Node (Slimefun-style tech component)
    private static final String HEAD_CARGO_NODE =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY2MzgxMDMxOWRjMWRkY2NmMzA0ZGMzNzU5NzE5MDk0ZmJhZDA5ZGZmNjcxNDI4YmM5NGYwOTNmZmNhN2Y3NyJ9fX0=";
    // Machine Part (alternate industrial block)
    private static final String HEAD_MACHINE_PART_2 =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTg4YzZmNjE5NDQ5M2QyNjI2OWYzODM5ODZjNjRmZDMzMmI5ZjliNWUwZDhhZDQ1ZmVkNjQxZjE3MWUyZWMwNyJ9fX0=";
    // Battery (energy storage device)
    private static final String HEAD_BATTERY =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWYzODAwMDgxYWQ5OWQyOTkyZjQ4MzZmYzMyMmRiYmY4ZjAwY2Y1MzNiNGZiNzMxYWIxZWMwNmE0NDVmNjBkYiJ9fX0=";
    // Computer (high-tech circuit board)
    private static final String HEAD_COMPUTER =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTMxOGEzMDBmZGExNzMzY2FmMTZjZTc4YmM2ZGM4MmU3ZjBlMWJlYzdlOWUzY2IyNDE3OWUyODU4MGQzYTJmOCJ9fX0=";
    // Camera (precision optical device)
    private static final String HEAD_CAMERA =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTMyMjcwNmJlOTY4MDZmMjI4ZGNjYzM4MGVjYWZhMmU3OWVhZGZlZTU1OGNkOGFkOGFlOTljYWQxZmJiNjc1In19fQ==";
    // Wind Monster (wind-themed energy entity, for ultimate tier)
    private static final String HEAD_WIND_ENTITY =
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk3ZTczYjUxOGI0NjEyZDJkMjY3ZWMzNDc4N2JhODYyYmIwMzExMThlM2U1MDUzMTUzOTAxYzU2OTQ0MWQwMyJ9fX0=";

    public static void setup(WindPower plugin, ItemGroup group) {
        registerMaterials(plugin, group);
        registerWindTurbines(plugin, group);
    }

    // ================================================================
    //  Materials
    // ================================================================
    private static void registerMaterials(WindPower plugin, ItemGroup group) {

        // --- Carbon Fiber ---
        SlimefunItemStack carbonFiber = new SlimefunItemStack(
            ID_CARBON_FIBER, HEAD_CARBON_FIBER,
            "&f碳纤维",
            "",
            "&7一种轻质高强度的纤维材料。",
            "&7由煤炭经特殊工艺提炼而成。",
            "",
            "&e用于制作: &7碳纤维板、强化叶片"
        );
        new SlimefunItem(group, carbonFiber, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.COAL), new ItemStack(Material.COAL), null,
            null, null, null,
            null, null, null
        }).register(plugin);

        // --- Carbon Fiber Sheet ---
        SlimefunItemStack carbonFiberSheet = new SlimefunItemStack(
            ID_CARBON_FIBER_SHEET, Material.PAPER,
            "&f碳纤维板",
            "",
            "&7由碳纤维压制而成的结构板材。",
            "&7轻便而坚固，是制造叶片的基础材料。"
        );
        new SlimefunItem(group, carbonFiberSheet, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            carbonFiber, carbonFiber, null,
            carbonFiber, carbonFiber, null,
            null, null, null
        }).register(plugin);

        // --- Titanium Ingot ---
        SlimefunItemStack titaniumIngot = new SlimefunItemStack(
            ID_TITANIUM_INGOT, Material.IRON_INGOT,
            "&f钛锭",
            "",
            "&7一种高强度耐腐蚀的合金材料。",
            "&7比铁更轻、更坚固。",
            "",
            "&e用于制作: &7强化叶片、高级发电机"
        );
        new SlimefunItem(group, titaniumIngot, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.IRON_INGOT),
            null, null, null,
            null, null, null
        }).register(plugin);

        // --- Magnetic Alloy Ingot ---
        SlimefunItemStack magneticAlloy = new SlimefunItemStack(
            ID_MAGNETIC_ALLOY, Material.NETHERITE_SCRAP,
            "&f磁性合金锭",
            "",
            "&7含有强磁性成分的合金材料。",
            "&7用于制造发电机的电磁定子组件。"
        );
        new SlimefunItem(group, magneticAlloy, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.IRON_INGOT), null, null,
            new ItemStack(Material.REDSTONE), null, null,
            new ItemStack(Material.COPPER_INGOT), null, null
        }).register(plugin);

        // --- Wind Crystal ---
        SlimefunItemStack windCrystal = new SlimefunItemStack(
            ID_WIND_CRYSTAL, Material.AMETHYST_SHARD,
            "&b风之水晶",
            "",
            "&7蕴含风之力的神秘水晶。",
            "&7能显著增强风力发电机的效率。",
            "",
            "&c稀有材料"
        );
        new SlimefunItem(group, windCrystal, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            null, new ItemStack(Material.DIAMOND), null,
            carbonFiber, new ItemStack(Material.LAPIS_LAZULI), carbonFiber,
            null, new ItemStack(Material.AMETHYST_SHARD), null
        }).register(plugin);

        // --- Reinforced Blade ---
        SlimefunItemStack reinforcedBlade = new SlimefunItemStack(
            ID_REINFORCED_BLADE, Material.FEATHER,
            "&f强化叶片",
            "",
            "&7由碳纤维板与钛合金制成的涡轮叶片。",
            "&7空气动力学优化的设计能捕捉更多风能。"
        );
        new SlimefunItem(group, reinforcedBlade, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            carbonFiberSheet, null, carbonFiberSheet,
            null, titaniumIngot, null,
            carbonFiberSheet, null, carbonFiberSheet
        }).register(plugin);

        // --- Aerogel ---
        SlimefunItemStack aerogel = new SlimefunItemStack(
            ID_AEROGEL, Material.WHITE_STAINED_GLASS,
            "&f气凝胶",
            "",
            "&7世界上最轻的固体材料。",
            "&7具有极佳的隔热与减阻性能。",
            "",
            "&e用于制作: &7高级以上风力发电机"
        );
        new SlimefunItem(group, aerogel, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SAND), new ItemStack(Material.SLIME_BALL),
            new ItemStack(Material.SAND), carbonFiber, new ItemStack(Material.SAND),
            new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SAND), new ItemStack(Material.SLIME_BALL)
        }).register(plugin);

        // --- Quantum Core (Tier 3 material) ---
        SlimefunItemStack quantumCore = new SlimefunItemStack(
            ID_QUANTUM_CORE, Material.END_CRYSTAL,
            "&d量子核心",
            "",
            "&7由下界之星与风之水晶共振形成的能量核心。",
            "&7蕴含量子纠缠级别的风能增幅效果。",
            "",
            "&c极品材料 &7- &e用于量子以上发电机"
        );
        new SlimefunItem(group, quantumCore, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            windCrystal, new ItemStack(Material.ENDER_EYE), windCrystal,
            new ItemStack(Material.ENDER_EYE), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.ENDER_EYE),
            windCrystal, new ItemStack(Material.ENDER_EYE), windCrystal
        }).register(plugin);

        // --- Storm Matrix (Tier 3 material, higher than quantum core) ---
        SlimefunItemStack stormMatrix = new SlimefunItemStack(
            ID_STORM_MATRIX, Material.SCULK_CATALYST,
            "&5风暴矩阵",
            "",
            "&7由多个量子核心与下界合金构成的能量矩阵。",
            "&7能将风能转化为毁灭性的风暴之力。",
            "",
            "&c传说级材料 &7- &e用于MK以上发电机"
        );
        new SlimefunItem(group, stormMatrix, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            quantumCore, new ItemStack(Material.NETHERITE_INGOT), quantumCore,
            new ItemStack(Material.NETHERITE_INGOT), new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.NETHERITE_INGOT),
            quantumCore, new ItemStack(Material.NETHERITE_INGOT), quantumCore
        }).register(plugin);

        // --- Spacetime Resonance Core (Tier 4 ultimate material) ---
        SlimefunItemStack spacetimeCore = new SlimefunItemStack(
            ID_SPACETIME_CORE, Material.SCULK_SHRIEKER,
            "&6时空共振核心",
            "",
            "&7融合海洋之心、回声碎片与风暴矩阵的终极产物。",
            "&7能扭曲时空，将无限风能从虚空中抽取。",
            "",
            "&4神级材料 &7- &e仅用于量子MK III"
        );
        new SlimefunItem(group, spacetimeCore, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            stormMatrix, new ItemStack(Material.HEART_OF_THE_SEA), stormMatrix,
            new ItemStack(Material.ECHO_SHARD), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.ECHO_SHARD),
            stormMatrix, new ItemStack(Material.HEART_OF_THE_SEA), stormMatrix
        }).register(plugin);
    }

    // ================================================================
    //  Wind Turbines — all use custom player-head textures
    // ================================================================
    private static void registerWindTurbines(WindPower plugin, ItemGroup group) {

        // Re-create SlimefunItemStack references for use in turbine recipes
        SlimefunItemStack carbonFiberSheet = new SlimefunItemStack(
            ID_CARBON_FIBER_SHEET, Material.PAPER, "&f碳纤维板");
        SlimefunItemStack titaniumIngot = new SlimefunItemStack(
            ID_TITANIUM_INGOT, Material.IRON_INGOT, "&f钛锭");
        SlimefunItemStack magneticAlloy = new SlimefunItemStack(
            ID_MAGNETIC_ALLOY, Material.NETHERITE_SCRAP, "&f磁性合金锭");
        SlimefunItemStack windCrystal = new SlimefunItemStack(
            ID_WIND_CRYSTAL, Material.AMETHYST_SHARD, "&b风之水晶");
        SlimefunItemStack reinforcedBlade = new SlimefunItemStack(
            ID_REINFORCED_BLADE, Material.FEATHER, "&f强化叶片");
        SlimefunItemStack aerogel = new SlimefunItemStack(
            ID_AEROGEL, Material.WHITE_STAINED_GLASS, "&f气凝胶");
        SlimefunItemStack quantumCore = new SlimefunItemStack(
            ID_QUANTUM_CORE, Material.END_CRYSTAL, "&d量子核心");
        SlimefunItemStack stormMatrix = new SlimefunItemStack(
            ID_STORM_MATRIX, Material.SCULK_CATALYST, "&5风暴矩阵");
        SlimefunItemStack spacetimeCore = new SlimefunItemStack(
            ID_SPACETIME_CORE, Material.SCULK_SHRIEKER, "&6时空共振核心");

        // ===== Basic Wind Turbine (20 J/s) =====
        int e1 = plugin.getConfig().getInt("wind-turbines.basic.energy-output", 20);
        int h1 = plugin.getConfig().getInt("wind-turbines.basic.min-height", 60);
        int c1 = plugin.getConfig().getInt("wind-turbines.basic.capacity", 0);
        boolean ih1 = plugin.getConfig().getBoolean("wind-turbines.basic.ignore-height", false);

        SlimefunItemStack basicTurbine = new SlimefunItemStack(
            ID_TURBINE_BASIC, HEAD_MACHINE_PART,
            "&f基础风力发电机",
            "",
            "&7利用风力发电的基础装置。",
            "&7需放置在高处且上方有天空视野。",
            "",
            "&e\u26A1 发电量: &a" + e1 + " J/s",
            "&e\u26A1 缓冲容量: " + (c1 > 0 ? "&a" + c1 + " J" : "&c无 (需即时消耗)"),
            "&e最低高度: &aY=" + h1,
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, basicTurbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            reinforcedBlade, reinforcedBlade, reinforcedBlade,
            carbonFiberSheet, new ItemStack(Material.IRON_BLOCK), magneticAlloy,
            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)
        }, e1, h1, c1, ih1).register(plugin);

        // ===== Reinforced Wind Turbine (35 J/s) =====
        int e2 = plugin.getConfig().getInt("wind-turbines.reinforced.energy-output", 35);
        int h2 = plugin.getConfig().getInt("wind-turbines.reinforced.min-height", 60);
        int c2 = plugin.getConfig().getInt("wind-turbines.reinforced.capacity", 0);
        boolean ih2 = plugin.getConfig().getBoolean("wind-turbines.reinforced.ignore-height", false);

        SlimefunItemStack reinforcedTurbine = new SlimefunItemStack(
            ID_TURBINE_REINFORCED, HEAD_CARGO_NODE,
            "&f强化风力发电机",
            "",
            "&7采用钛合金框架强化的风力发电机。",
            "&7结构更稳固，发电效率更高。",
            "",
            "&e\u26A1 发电量: &a" + e2 + " J/s",
            "&e\u26A1 缓冲容量: " + (c2 > 0 ? "&a" + c2 + " J" : "&c无 (需即时消耗)"),
            "&e最低高度: &aY=" + h2,
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, reinforcedTurbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            reinforcedBlade, reinforcedBlade, reinforcedBlade,
            titaniumIngot, basicTurbine, magneticAlloy,
            titaniumIngot, titaniumIngot, titaniumIngot
        }, e2, h2, c2, ih2).register(plugin);

        // ===== Advanced Wind Turbine (45 J/s, ignores height) =====
        int e3 = plugin.getConfig().getInt("wind-turbines.advanced.energy-output", 45);
        int h3 = plugin.getConfig().getInt("wind-turbines.advanced.min-height", 0);
        int c3 = plugin.getConfig().getInt("wind-turbines.advanced.capacity", 0);
        boolean ih3 = plugin.getConfig().getBoolean("wind-turbines.advanced.ignore-height", true);

        SlimefunItemStack advancedTurbine = new SlimefunItemStack(
            ID_TURBINE_ADVANCED, HEAD_MACHINE_PART_2,
            "&b高级风力发电机",
            "",
            "&7集成风之水晶与气凝胶叶片的高级发电机。",
            "&7量子减阻涂层使其无视高度限制运转。",
            "",
            "&e\u26A1 发电量: &a" + e3 + " J/s",
            "&e\u26A1 缓冲容量: " + (c3 > 0 ? "&a" + c3 + " J" : "&c无 (需即时消耗)"),
            "&e高度限制: &a无 (任意高度)",
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, advancedTurbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            windCrystal, new ItemStack(Material.ENDER_EYE), windCrystal,
            aerogel, reinforcedTurbine, aerogel,
            titaniumIngot, new ItemStack(Material.DIAMOND_BLOCK), titaniumIngot
        }, e3, h3, c3, ih3).register(plugin);

        // ===== Quantum Wind Turbine (75 J/s, ignores height) =====
        int e4 = plugin.getConfig().getInt("wind-turbines.quantum.energy-output", 150);
        int h4 = plugin.getConfig().getInt("wind-turbines.quantum.min-height", 0);
        int c4 = plugin.getConfig().getInt("wind-turbines.quantum.capacity", 0);
        boolean ih4 = plugin.getConfig().getBoolean("wind-turbines.quantum.ignore-height", true);

        SlimefunItemStack quantumTurbine = new SlimefunItemStack(
            ID_TURBINE_QUANTUM, HEAD_BATTERY,
            "&d量子风力发电机",
            "",
            "&7运用量子纠缠技术的终极风力发电机。",
            "&7量子核心矩阵使其在任何高度满功率运转。",
            "",
            "&e\u26A1 发电量: &a" + e4 + " J/s",
            "&e\u26A1 缓冲容量: " + (c4 > 0 ? "&a" + c4 + " J" : "&c无 (需即时消耗)"),
            "&e高度限制: &a无 (任意高度)",
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, quantumTurbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            windCrystal, aerogel, windCrystal,
            new ItemStack(Material.NETHERITE_INGOT), advancedTurbine, new ItemStack(Material.NETHERITE_INGOT),
            aerogel, quantumCore, aerogel
        }, e4, h4, c4, ih4).register(plugin);

        // ===== Quantum MK I Wind Turbine (90 J/s, ignores height) =====
        int e5 = plugin.getConfig().getInt("wind-turbines.quantum-mk1.energy-output", 180);
        int h5 = plugin.getConfig().getInt("wind-turbines.quantum-mk1.min-height", 0);
        int c5 = plugin.getConfig().getInt("wind-turbines.quantum-mk1.capacity", 1024);
        boolean ih5 = plugin.getConfig().getBoolean("wind-turbines.quantum-mk1.ignore-height", true);

        SlimefunItemStack quantumMk1Turbine = new SlimefunItemStack(
            ID_TURBINE_QUANTUM_MK1, HEAD_COMPUTER,
            "&d量子MK I 风力发电机",
            "",
            "&7在量子涡轮基础上集成风暴矩阵的强化版。",
            "&7风暴能量使其发电效率突破极限。",
            "",
            "&e\u26A1 发电量: &a" + e5 + " J/s",
            "&e\u26A1 缓冲容量: " + (c5 > 0 ? "&a" + c5 + " J" : "&c无 (需即时消耗)"),
            "&e高度限制: &a无 (任意高度)",
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, quantumMk1Turbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            stormMatrix, quantumCore, stormMatrix,
            new ItemStack(Material.NETHERITE_INGOT), quantumTurbine, new ItemStack(Material.NETHERITE_INGOT),
            new ItemStack(Material.NETHER_STAR), stormMatrix, new ItemStack(Material.NETHER_STAR)
        }, e5, h5, c5, ih5).register(plugin);

        // ===== Quantum MK II Wind Turbine (128 J/s, ignores height) =====
        int e6 = plugin.getConfig().getInt("wind-turbines.quantum-mk2.energy-output", 256);
        int h6 = plugin.getConfig().getInt("wind-turbines.quantum-mk2.min-height", 0);
        int c6 = plugin.getConfig().getInt("wind-turbines.quantum-mk2.capacity", 2048);
        boolean ih6 = plugin.getConfig().getBoolean("wind-turbines.quantum-mk2.ignore-height", true);

        SlimefunItemStack quantumMk2Turbine = new SlimefunItemStack(
            ID_TURBINE_QUANTUM_MK2, HEAD_CAMERA,
            "&8量子MK II 风力发电机",
            "",
            "&7融合末影水晶与风暴矩阵的终极发电机。",
            "&7传说其叶片转动时能撕裂时空。",
            "",
            "&e\u26A1 发电量: &a" + e6 + " J/s",
            "&e\u26A1 缓冲容量: " + (c6 > 0 ? "&a" + c6 + " J" : "&c无 (需即时消耗)"),
            "&e高度限制: &a无 (任意高度)",
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, quantumMk2Turbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            stormMatrix, new ItemStack(Material.NETHER_STAR), stormMatrix,
            new ItemStack(Material.END_CRYSTAL), quantumMk1Turbine, new ItemStack(Material.END_CRYSTAL),
            stormMatrix, new ItemStack(Material.NETHER_STAR), stormMatrix
        }, e6, h6, c6, ih6).register(plugin);

        // ===== Quantum MK III Wind Turbine (256 J/s, ignores height) =====
        int e7 = plugin.getConfig().getInt("wind-turbines.quantum-mk3.energy-output", 512);
        int h7 = plugin.getConfig().getInt("wind-turbines.quantum-mk3.min-height", 0);
        int c7 = plugin.getConfig().getInt("wind-turbines.quantum-mk3.capacity", 4096);
        boolean ih7 = plugin.getConfig().getBoolean("wind-turbines.quantum-mk3.ignore-height", true);

        SlimefunItemStack quantumMk3Turbine = new SlimefunItemStack(
            ID_TURBINE_QUANTUM_MK3, HEAD_WIND_ENTITY,
            "&6量子MK III 风力发电机",
            "",
            "&7融合时空共振核心的终极风力发电机。",
            "&7其叶片旋转时能撕裂时空维度，",
            "&7从虚空中汲取无限风能。",
            "",
            "&e\u26A1 发电量: &a" + e7 + " J/s",
            "&e\u26A1 缓冲容量: " + (c7 > 0 ? "&a" + c7 + " J" : "&c无 (需即时消耗)"),
            "&e高度限制: &a无 (任意高度)",
            "",
            "&c注意: &7被遮挡时发电量减半"
        );
        new WindTurbine(group, quantumMk3Turbine, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            quantumMk2Turbine, spacetimeCore, quantumMk2Turbine,
            spacetimeCore, new ItemStack(Material.NETHER_STAR), spacetimeCore,
            quantumMk2Turbine, spacetimeCore, quantumMk2Turbine
        }, e7, h7, c7, ih7).register(plugin);
    }
}
