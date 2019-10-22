package me.Sp3eex.CustomHelp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("help")) {
				List<String> list = getConfig().getStringList("help");
				if (list.size() > 0) {
					for (String line : list) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', line).replace("-", ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 1)));
					}
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-help-message")));
				}
			}
		}
		return true;
	}
}
