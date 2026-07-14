package dev.reiz.windpower;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * WindPower - A Slimefun4 addon adding wind turbines and related materials.
 *
 * Features:
 * - 10 new crafting materials (carbon fiber, titanium, aerogel, quantum core, etc.)
 * - 7 tiers of wind turbines (20 / 35 / 45 / 150 / 180 / 256 / 512 J/s)
 * - All turbines use custom player-head textures
 * - Height-based generation with sky exposure checks
 */
public class WindPower extends JavaPlugin implements SlimefunAddon {

    private static WindPower instance;
    private ItemGroup itemGroup;

    /** Bump this when config.yml structure changes to force-reset old configs. */
    private static final int CURRENT_CONFIG_VERSION = 5;

    @Override
    public void onEnable() {
        instance = this;

        // --- Config version management ---
        // If the server has an older config.yml (from a previous plugin version),
        // saveDefaultConfig() won't overwrite it. We detect the version and force
        // an update so that new energy/capacity values take effect.
        handleConfigUpdate();

        // Create the item group for the Slimefun guide
        itemGroup = new ItemGroup(
            new NamespacedKey(this, "windpower"),
            new CustomItemStack(
                new ItemStack(Material.SCAFFOLDING),
                "&a风力能源",
                "",
                "&7> 风力发电机与相关材料"
            ),
            3
        );

        // Register all items (materials + turbines + paper mill)
        WindPowerItems.setup(this, itemGroup);


        // Register research tree (must be after items are registered)
        try {
            WindPowerResearches.setup(this);
            getLogger().info("✓ 研究树注册完成 (12 项)");
        } catch (Exception e) {
            getLogger().severe("✗ 研究树注册失败: " + e.getMessage());
            e.printStackTrace();
        }

        // Register /windpower command
        if (getCommand("windpower") != null) {
            WindPowerCommand cmd = new WindPowerCommand(this);
            getCommand("windpower").setExecutor(cmd);
            getCommand("windpower").setTabCompleter(cmd);
        }

        getLogger().info("WindPower v" + getDescription().getVersion() + " 已启用!");
        getLogger().info("添加了 10 种新材料、7 个等级的风力发电机、1 台造纸机和 12 项研究。");
    }

    /**
     * Checks the config-version in the existing config file. If it doesn't match
     * CURRENT_CONFIG_VERSION, backs up the old config and replaces it with the
     * fresh default from the jar so that updated values are applied.
     */
    private void handleConfigUpdate() {
        // First, ensure the default config exists (creates from jar if absent)
        saveDefaultConfig();

        File configFile = new File(getDataFolder(), "config.yml");
        FileConfiguration config = getConfig();

        int savedVersion = config.getInt("config-version", 0);

        if (savedVersion < CURRENT_CONFIG_VERSION) {
            getLogger().warning("检测到旧版配置文件 (v" + savedVersion + ")，正在更新到 v"
                + CURRENT_CONFIG_VERSION + " ...");

            // Back up the old config
            if (configFile.exists()) {
                File backup = new File(getDataFolder(), "config.yml.bak.v" + savedVersion);
                try {
                    Files.copy(configFile.toPath(), backup.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    getLogger().info("旧配置已备份到: " + backup.getName());
                } catch (IOException e) {
                    getLogger().warning("备份旧配置失败: " + e.getMessage());
                }
            }

            // Force-overwrite with the fresh default config from the jar
            saveResource("config.yml", true);
            reloadConfig();

            getLogger().info("配置已更新到 v" + CURRENT_CONFIG_VERSION + "。");
            getLogger().info("如需自定义，请编辑 plugins/WindPower/config.yml 后使用 /windpower reload。");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("WindPower 已禁用!");
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/reiz/WindPower/issues";
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public static WindPower getInstance() {
        return instance;
    }
}
