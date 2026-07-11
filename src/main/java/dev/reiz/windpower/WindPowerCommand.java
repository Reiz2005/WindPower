package dev.reiz.windpower;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the /windpower command.
 *
 * Usage:
 *   /windpower         — Show plugin info and turbine stats
 *   /windpower reload  — Reload config (OP only)
 */
public class WindPowerCommand implements CommandExecutor, TabCompleter {

    private static final String PREFIX = ChatColor.DARK_AQUA + "[WindPower] " + ChatColor.GRAY;
    private static final String LINE = ChatColor.DARK_AQUA + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    private final WindPower plugin;

    public WindPowerCommand(WindPower plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("windpower.admin")) {
                sender.sendMessage(PREFIX + ChatColor.RED + "你没有权限执行此操作。");
                return true;
            }
            plugin.reloadConfig();
            sender.sendMessage(PREFIX + ChatColor.GREEN + "配置已重新加载。");
            sender.sendMessage(PREFIX + ChatColor.YELLOW + "注意: 发电量等数值需要重启服务器才能完全生效。");
            return true;
        }

        // Default: show info
        sender.sendMessage(LINE);
        sender.sendMessage(ChatColor.AQUA + "  ⚡ WindPower v" + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GRAY + "  Slimefun4 风力发电附属插件");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GOLD + "  风力发电机一览:");
        sender.sendMessage("");

        printTurbine(sender, "基础",    "basic",        false);
        printTurbine(sender, "强化",    "reinforced",   false);
        printTurbine(sender, "高级",    "advanced",     true);
        printTurbine(sender, "量子",    "quantum",      true);
        printTurbine(sender, "量子MK I",  "quantum-mk1",  true);
        printTurbine(sender, "量子MK II", "quantum-mk2",  true);
        printTurbine(sender, "量子MK III","quantum-mk3",  true);

        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "  发电条件: ");
        sender.sendMessage(ChatColor.GRAY + "  • 基础/强化: 需放置在最低高度以上，且上方有天空视野");
        sender.sendMessage(ChatColor.GRAY + "  • 高级以上: &a无视高度限制&r，但仍需天空视野");
        sender.sendMessage(ChatColor.GRAY + "  • 遮挡时发电量减半");
        sender.sendMessage(LINE);

        return true;
    }

    private void printTurbine(CommandSender sender, String name, String key, boolean ignoreHeight) {
        int energy  = plugin.getConfig().getInt("wind-turbines." + key + ".energy-output", 0);
        int height  = plugin.getConfig().getInt("wind-turbines." + key + ".min-height", 0);
        int cap     = plugin.getConfig().getInt("wind-turbines." + key + ".capacity", 0);

        String heightStr;
        if (ignoreHeight) {
            heightStr = ChatColor.GREEN + "无视高度";
        } else {
            heightStr = ChatColor.AQUA + "Y=" + height;
        }

        sender.sendMessage(String.format(
            "%s  %s%s 风力发电机 %s- %s⚡ %d J/s %s| %s高度 %s %s| %s容量 %d J",
            ChatColor.GRAY,
            ChatColor.WHITE, name,
            ChatColor.DARK_GRAY,
            ChatColor.YELLOW, energy,
            ChatColor.DARK_GRAY,
            heightStr,
            ChatColor.DARK_GRAY,
            ChatColor.GREEN, cap
        ));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }
        }
        return completions;
    }
}
