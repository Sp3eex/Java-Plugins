package me.Sp3eex.RankSigns;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	private Main main;
	public Commands(Main instance) {
		main = instance;
	}
	
	public List<String> getSigns() {
		List<String> signs = new ArrayList<String>();
		ConfigurationSection configsec = main.getConfig().getConfigurationSection("signs");
		for (String sign : configsec.getKeys(false)) {
			signs.add(sign);
		}
		return signs;
	}
	
	public boolean signExists(String signname) {
		if (main.getConfig().isSet("signs." + signname)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("rs") && sender.isOp()) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.BLUE + "--------------------------------------");
					sender.sendMessage(ChatColor.RED + "RankSigns by Sp3eex");
					sender.sendMessage(ChatColor.RED + "Discord: Sp3eex#1427");
					sender.sendMessage(ChatColor.WHITE + "Rank Sign Permission: ranksigns.<ranksignName>");
					sender.sendMessage(ChatColor.WHITE + "/rs list - list of ranksigns");
					sender.sendMessage(ChatColor.WHITE + "/rs delete <ranksign> - delete a ranksign");
					sender.sendMessage(ChatColor.WHITE + "/rs addperm <ranksign> <permission> - add permission");
					sender.sendMessage(ChatColor.BLUE + "--------------------------------------");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("addperm")) {
						sender.sendMessage(ChatColor.RED + "/rs addperm <ranksign> <permission>");
					}
					if (args[0].equalsIgnoreCase("delete")) {
						sender.sendMessage(ChatColor.RED + "/rs delete <ranksign>");
					}
					if (args[0].equalsIgnoreCase("list")) {
						int size = this.getSigns().size();
						if (size == 0) {
							sender.sendMessage(ChatColor.RED + "There are no signs created.");
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Rank Signs:");
							for (int i = 0; i < this.getSigns().size(); i++) {
								String rank = getSigns().get(i);
								String world = main.getConfig().getString("signs." + rank + ".world");
								int x = Integer.parseInt(main.getConfig().getString("signs." + rank + ".x"));
								int y = Integer.parseInt(main.getConfig().getString("signs." + rank + ".y"));
								int z = Integer.parseInt(main.getConfig().getString("signs." + rank + ".z"));
								sender.sendMessage(i+1 + ". '" + this.getSigns().get(i) + "'" + ChatColor.GRAY + " (World: " + world + ", x: " + x + ", y: " + y + ", z: " + z + ")");
							}
							return true;
						}
					}
				} else if (args.length == 2) {
					String rank = args[1];
					if (args[0].equalsIgnoreCase("addperm")) {
						sender.sendMessage(ChatColor.RED + "/ranksigns addperm <ranksign> <permission>");
					}
					if (args[0].equalsIgnoreCase("delete")) {
						if (!this.signExists(rank)) {
							sender.sendMessage(ChatColor.RED + "A ranksign with that name does not exist!");
							return true;
						}
						String world = main.getConfig().getString("signs." + rank + ".world");
						int x = main.getConfig().getInt("signs." + rank + ".x");
						int y = main.getConfig().getInt("signs." + rank + ".y");
						int z = main.getConfig().getInt("signs." + rank + ".z");
						Location signLoc = new Location(Bukkit.getWorld(world), x, y, z);
						signLoc.getBlock().breakNaturally();
						main.getConfig().set("signs." + rank, null);
						sender.sendMessage(ChatColor.YELLOW + "Ranksign '" + rank + "' removed");
						main.saveConfig(); main.reloadConfig();
						return true;
					}
					return true;
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("addperm")) {
						String rank = args[1];
						String perm = args[2];
						ArrayList<String> listperms = (ArrayList<String>) main.getConfig().getStringList("signs." + rank + ".permissions");
						listperms.add(perm);
						main.getConfig().set("signs." + rank + ".permissions", listperms);
						Main.permission.playerAdd(player, perm);
						main.saveConfig(); main.reloadConfig();
						sender.sendMessage(ChatColor.GREEN + "Permission '" + perm + "' adedd to '" + rank + "' rank!");
					}
					
				}
			}
		} else if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage("Only in-game players can execute this command.");
		}
		return true;
	}
}
