package me.Sp3eex.LootChests;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("lc") && player.isOp()) {
				if (args.length == 0) {
					player.sendMessage("" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 50));
					player.sendMessage(ChatColor.GRAY + "/lc add" + ChatColor.WHITE + " add a loot chest");
					player.sendMessage(ChatColor.GRAY + "/lc restock" + ChatColor.WHITE + " restock all loot chests");
					player.sendMessage(ChatColor.GRAY + "/lc cremove <id>" + ChatColor.WHITE + " remove a specific loot chest");
					player.sendMessage(ChatColor.GRAY + "/lc sremove <id>" + ChatColor.WHITE + " remove a specific timer sign");
					player.sendMessage(ChatColor.GRAY + "/lc clist" + ChatColor.WHITE + " list of loot chests");
					player.sendMessage(ChatColor.GRAY + "/lc slist" + ChatColor.WHITE + " list of timer signs");
					player.sendMessage(ChatColor.GRAY + "sign, first line: [lctimer]" + ChatColor.WHITE + " create a timer sign");
					player.sendMessage("" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 50));
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("add")) {
						int ID = new Random().nextInt(8999) + 1000;
						if (!Main.instance().getConfig().isSet("lootchests." + ID)) {
							Main.instance().getConfig().set("lootchests." + ID + ".Location.x", player.getLocation().getBlockX());
							Main.instance().getConfig().set("lootchests." + ID + ".Location.y", player.getLocation().getBlockY());
							Main.instance().getConfig().set("lootchests." + ID + ".Location.z", player.getLocation().getBlockZ());
							Main.instance().getConfig().set("lootchests." + ID + ".Location.world", player.getLocation().getWorld().getName());
							Main.instance().saveConfig(); 
							Main.instance().reloadConfig();
							player.getLocation().getBlock().setType(Material.CHEST);
						}
					} else if (args[0].equalsIgnoreCase("cremove")) {
						player.sendMessage(ChatColor.RED + "/lc cremove <id>");
					} else if (args[0].equalsIgnoreCase("sremove")) {
						player.sendMessage(ChatColor.RED + "/lc sremove <id>");
					} else if (args[0].equalsIgnoreCase("clist")) {
						ConfigurationSection section = Main.instance().getConfig().getConfigurationSection("lootchests");
						ArrayList<String> list = new ArrayList<String>();
						
						if (section == null) {
							player.sendMessage(ChatColor.RED + "There are no loot chests set.");
							return true;
						}
						
						for (String key : section.getKeys(false)) {
							list.add(key);
						}
						
						player.sendMessage(ChatColor.GREEN + "Loot chests: " + ChatColor.WHITE + list.toString().replace("[", "").replace("]", ""));
					} else if (args[0].equalsIgnoreCase("slist")) {
						ConfigurationSection section = Main.instance().getConfig().getConfigurationSection("signs");
						ArrayList<String> list = new ArrayList<String>();
						try {
							for (String key : section.getKeys(false)) {
								list.add(key);
							}
						} catch (Exception e) { player.sendMessage(ChatColor.RED + "There are no timer signs set."); return true; }
						player.sendMessage(ChatColor.GREEN + "Timer signs: " + ChatColor.WHITE + list.toString().replace("[", "").replace("]", ""));
					} else if (args[0].equalsIgnoreCase("restock")) {
						ConfigurationSection section = Main.instance().getConfig().getConfigurationSection("lootchests");
		        		ArrayList<String> list = new ArrayList<String>();
		        		try{ 
		        			for (String key : section.getKeys(false)) {
		        				list.add(key);
		        			}
		        		} catch (Exception e) {
		        			
		        		}
		        		
		            	if (list.size() == 0) { 
		            		player.sendMessage(ChatColor.RED + "There are no loot chests set.");
		            		return true; 
		            	}
		            	for (String ID : list) {
		            		int x = Main.instance().getConfig().getInt("lootchests." + ID + ".Location.x");
							int y = Main.instance().getConfig().getInt("lootchests." + ID + ".Location.y");
							int z = Main.instance().getConfig().getInt("lootchests." + ID + ".Location.z");
							World world = Bukkit.getWorld(Main.instance().getConfig().getString("lootchests." + ID + ".Location.world"));
							Location loc = new Location(world, x, y, z);
							
							if (loc.getBlock().getType() != Material.CHEST) {
								loc.getBlock().setType(Material.CHEST);
							}
							
							Chest chest = (Chest) loc.getBlock().getState();
							chest.getInventory().clear();
							for (int  i = 0; i < chest.getInventory().getSize(); i++) {
								chest.getInventory().setItem(i, Items.GetRandomItem());
							}
		            	}
		            	player.sendMessage(ChatColor.GREEN + "Restocked " + list.size() + " loot chests.");
					}
					
				}
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("cremove")) {
						if (Main.instance().getConfig().isSet("lootchests." + args[1].toString())) {
							int x = Main.instance().getConfig().getInt("lootchests." + args[1] + ".Location.x");
							int y = Main.instance().getConfig().getInt("lootchests." + args[1] + ".Location.y");
							int z = Main.instance().getConfig().getInt("lootchests." + args[1] + ".Location.z");
							World world = Bukkit.getWorld(Main.instance().getConfig().getString("lootchests." + args[1] + ".Location.world"));
							Location loc = new Location(world, x, y, z); loc.getBlock().setType(Material.AIR);
							Main.instance().getConfig().set("lootchests." + args[1], null);
							Main.instance().saveConfig(); 
							Main.instance().reloadConfig();
							player.sendMessage(ChatColor.GREEN + "Loot chest (ID: " + args[1] + ") removed.");
						} else {
							player.sendMessage(ChatColor.RED + "This loot chest does not exist.");
						}
					} else if (args[0].equalsIgnoreCase("sremove")) {
						if (Main.instance().getConfig().isSet("signs." + args[1].toString())) {
							int x = Main.instance().getConfig().getInt("signs." + args[1] + ".Location.x");
							int y = Main.instance().getConfig().getInt("signs." + args[1] + ".Location.y");
							int z = Main.instance().getConfig().getInt("signs." + args[1] + ".Location.z");
							String world = Main.instance().getConfig().getString("signs." + args[1] + ".Location.world");
							Location loc = new Location(Bukkit.getWorld(world), x, y, z); 
							loc.getBlock().setType(Material.AIR);
							Main.instance().getConfig().set("signs." + args[1], null);
							Main.instance().saveConfig(); 
							Main.instance().reloadConfig();
							player.sendMessage(ChatColor.GREEN + "Timer sign (ID: " + args[1] + ") removed.");
						} else {
							player.sendMessage(ChatColor.RED + "This timer sign does not exist.");
						}
					}
				}
			}
		}
		return true;
	}
}
