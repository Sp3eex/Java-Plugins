package me.Sp3eex.PvPStats;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	private Main main;
	public Commands(Main instance) {
		main = instance;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("pvp")) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.BLUE + "PvP Stats: " + ChatColor.GRAY + " Kills: " + ChatColor.YELLOW + main.getKills(player.getName()) + 
						ChatColor.GRAY + ", Mob kills: " + ChatColor.YELLOW + main.getMobKills(player.getName()) +
					    ChatColor.GRAY + ", Deaths: " + ChatColor.YELLOW + main.getDeaths(player.getName()));
					if (player.isOp()) {
						player.sendMessage("" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 50));
						player.sendMessage(ChatColor.GRAY + "/pvp addtoppvp <x(true/false)> <headFace(NORTH/WEST/SOUTH/EAST)> <material> - add a new toppvp");
						player.sendMessage(ChatColor.GRAY + "/pvp list - list of toppvps set");
						player.sendMessage(ChatColor.GRAY + "/pvp delete <toppvpID> - delete a toppvp");
						player.sendMessage("" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 50));
					}
				} else if (args.length == 1) { 
					if (args[0].equalsIgnoreCase("addtoppvp") && player.isOp()) {
						player.sendMessage(ChatColor.GRAY + "/pvp settoppvp <x(true/false)> <headFace(NORTH/WEST/SOUTH/EAST)> <material>");
						return true;
					}
					if (args[0].equalsIgnoreCase("delete") && player.isOp()) {
						player.sendMessage(ChatColor.GRAY + "/pvp delete <toppvpID> - delete a toppvp");
						return true;
					}
					if (args[0].equalsIgnoreCase("list") && player.isOp()) {
						ConfigurationSection section = main.getConfig().getConfigurationSection("toppvps");
						ArrayList<String> list = new ArrayList<String>();
						for (String toppvp : section.getKeys(false)) {
							list.add(toppvp);
						}
						if (list.isEmpty()) {
							player.sendMessage(ChatColor.RED + "There are no toppvp builds set.");
						} else {
							for (int i = 0; i < list.size(); i++) {
								int calc = i + 1;
								player.sendMessage(ChatColor.GRAY + "" + calc + ". " + list.get(i));
							}	
						}
						return true;
					}
					String playername = args[0];
					if (!main.getConfig().isSet("stats." + playername)) {
						player.sendMessage(ChatColor.RED + "This player does not exist.");
					} else {
						String customplayer = args[0];
						player.sendMessage(ChatColor.BLUE + customplayer + "'s PvP Stats: " + ChatColor.GRAY + " Kills: " + ChatColor.YELLOW + main.getKills(customplayer) + 
								ChatColor.GRAY + ", Mob kills: " + ChatColor.YELLOW + main.getMobKills(customplayer) +
							    ChatColor.GRAY + ", Deaths: " + ChatColor.YELLOW + main.getDeaths(customplayer));
					}
				} else if (args.length == 2 && player.isOp()) {
					if (args[0].equalsIgnoreCase("delete") && player.isOp()) {
						if (main.doesSectionExists(args[1])) {
							List<String> locations = main.getConfig().getStringList("toppvps." + args[1].toString());
							for (String loc : locations) {
								String[] split = loc.split(":");	
								String world = split[0];
								int x = Integer.parseInt(split[1]);
								int y = Integer.parseInt(split[2]);
								int z = Integer.parseInt(split[3]);
								Location lx = new Location(Bukkit.getWorld(world), x, y, z);
								lx.getBlock().setType(Material.AIR);
							}
							main.getConfig().set("toppvps." + args[1].toString(), null);
							main.saveConfig(); main.reloadConfig();
							player.sendMessage(ChatColor.GREEN + "TopPvp removed.");
						} else {
							player.sendMessage(ChatColor.RED + "This TopPvp does not exist.");
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("addtoppvp") && player.isOp()) {
						player.sendMessage(ChatColor.GRAY + "/pvp settoppvp <x(true/false)> <headFace(NORTH/WEST/SOUTH/EAST)> <material>");
						return true;
					}
				} else if (args.length == 3 && player.isOp()) {
					if (args[0].equalsIgnoreCase("addtoppvp") && player.isOp()) {
						player.sendMessage(ChatColor.GRAY + "/pvp settoppvp <x(true/false)> <headFace(NORTH/WEST/SOUTH/EAST)> <material>");
						return true;
					}
				} else if (args.length == 4 && player.isOp()) {
					try {
						if (args[0].equalsIgnoreCase("addtoppvp") && player.isOp()) {
							boolean horizontal = Boolean.parseBoolean(args[1]);
							Material material = Material.getMaterial(args[3].toUpperCase());
							BlockFace headFace = BlockFace.valueOf(args[2].toUpperCase());
							// main thing set heads and custom blocks type
							for (Location headblock : BuildingShape.PlayerHeadLocations(player, horizontal)) {
								headblock.getBlock().setType(Material.PLAYER_HEAD);
								if (horizontal) {
									Skull skull = (Skull) headblock.getBlock().getState();
									skull.setRotation(headFace);
									skull.update();
								}
							}
							for (Location block : BuildingShape.GetBlockLocations(player, horizontal)) {
								block.getBlock().setType(material);
							}
							main.AddNewTopPvP(player, BuildingShape.GetBlockLocations(player, horizontal), BuildingShape.PlayerHeadLocations(player, horizontal));
							player.sendMessage(ChatColor.GREEN + "TopPvP set successfully!");
						}
					} catch (Exception e) {
						player.sendMessage(ChatColor.RED + "Erorr.. Check your arguments!");
					}
				}
				return true;
			} else if (cmd.getName().equalsIgnoreCase("pvptop")) {
				main.getTopPvPers(player);
			}
		}
		return true;
	}
}
