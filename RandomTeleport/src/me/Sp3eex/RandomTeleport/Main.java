package me.Sp3eex.RandomTeleport;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("rtp")) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.GRAY + "Usage: /rtp <underground/far/close>");
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("underground")) {
						int randomX = new Random().nextInt(600);
						int randomZ = new Random().nextInt(600);
						Location loc = player.getLocation();
						for (int i = 0; i < 60; i++) {
							loc = new Location(player.getWorld(), randomX, i, randomZ);
							if (loc.getBlock().getType() == Material.AIR && loc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
								player.teleport(loc);
								player.sendMessage(ChatColor.GRAY + "You teleported to a random location. (" + 
										player.getWorld().getName() + ", " + loc.getBlockX() + ", " + i + ", " + loc.getBlockZ() + ")");
								return true;
							}
						}
						player.sendMessage(ChatColor.RED + "Could not find a safe location. Try again.");
					} else if (args[0].equalsIgnoreCase("far")) {
						int randomX = new Random().nextInt(700);
						int randomZ = new Random().nextInt(700);
						Location loc = player.getLocation();
						for (int i = 60; i < 240; i++) {
							loc = new Location(player.getWorld(), randomX, i, randomZ);
							if (loc.getBlock().getType() == Material.AIR && loc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
								player.teleport(loc);
								player.sendMessage(ChatColor.GRAY + "You teleported to a random location. (" + 
										player.getWorld().getName() + ", " + loc.getBlockX() + ", " + i + ", " + loc.getBlockZ() + ")");
								return true;
							}
						}
						player.sendMessage(ChatColor.RED + "Could not find a safe location. Try again.");
					} else if (args[0].equalsIgnoreCase("close")) {
						int randomX = new Random().nextInt(200);
						int randomZ = new Random().nextInt(200);
						Location loc = player.getLocation();
						for (int i = 60; i < 240; i++) {
							loc = new Location(player.getWorld(), randomX, i, randomZ);
							if (loc.getBlock().getType() == Material.AIR && loc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
								player.teleport(loc);
								player.sendMessage(ChatColor.GRAY + "You teleported to a random location. (" + 
										player.getWorld().getName() + ", " + loc.getBlockX() + ", " + i + ", " + loc.getBlockZ() + ")");
								return true;
							}
						}
						player.sendMessage(ChatColor.RED + "Could not find a safe location. Try again.");
					} else {
						player.sendMessage(ChatColor.RED + "That teleportation mode does not exist.");
					}
				}
			}
		}
		return true;
	}
}