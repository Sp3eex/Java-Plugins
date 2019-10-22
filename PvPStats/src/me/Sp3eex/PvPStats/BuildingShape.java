package me.Sp3eex.PvPStats;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BuildingShape {
	
	public static Location[] GetBlockLocations(Player player, boolean horizontal) {
		// Shape
		//   -
		// - - -
		Location loc = player.getLocation();
		if (horizontal) {
			Location[] blockLocations = {
				new Location(player.getWorld(), loc.getBlockX() + 1, loc.getBlockY() ,loc.getBlockZ()), // 1 right
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY(),loc.getBlockZ()), // middle
				new Location(player.getWorld(), loc.getBlockX() - 1, loc.getBlockY() ,loc.getBlockZ()), // 1 left
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 1,loc.getBlockZ()), // 1 up in the middle
			};
			return blockLocations;
		} else {
			Location[] blockLocations = {
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY(),loc.getBlockZ()), // middle
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY(),loc.getBlockZ() + 1), // middle
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY(),loc.getBlockZ() - 1), // middle
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 1,loc.getBlockZ()), // 1 up in the middle
			};
			return blockLocations;
		}
	}
	
	public static Location[] PlayerHeadLocations(Player player, boolean horizontal) {
		Location loc = player.getLocation();
		if (horizontal) {
			Location[] headLocations = {
				new Location(player.getWorld(), loc.getBlockX() + 1, loc.getBlockY() + 1,loc.getBlockZ()), // 1 right
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 2,loc.getBlockZ()), // middle
				new Location(player.getWorld(), loc.getBlockX() - 1, loc.getBlockY() + 1,loc.getBlockZ()), // 1 left
			};
			return headLocations;
		} else {
			Location[] headLocations = {
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 1,loc.getBlockZ() + 1), // middle
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 1,loc.getBlockZ() - 1), // middle
				new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 2,loc.getBlockZ()), // 1 up in the middle
			};
			return headLocations;
		}
	}
}
