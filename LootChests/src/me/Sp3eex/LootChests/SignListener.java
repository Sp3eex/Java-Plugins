package me.Sp3eex.LootChests;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] lines = event.getLines();
		Location signloc = event.getBlock().getLocation();
		if (lines[0].equalsIgnoreCase("[lctimer]") && player.isOp()) {
			event.setLine(0, ChatColor.BLACK + "Looting chests");
			event.setLine(1, ChatColor.BLACK + "restocks in: ");
			event.setLine(2, ChatColor.RED + "0" + " seconds.");
			
			int ID = new Random().nextInt(8999) + 1000;	
			Main.instance().getConfig().set("signs." + ID + ".Location.x" , signloc.getBlockX());
			Main.instance().getConfig().set("signs." + ID + ".Location.y" , signloc.getBlockY());
			Main.instance().getConfig().set("signs." + ID + ".Location.z" , signloc.getBlockZ());
			Main.instance().getConfig().set("signs." + ID + ".Location.world" , signloc.getWorld().getName());
			Main.instance().saveConfig(); 
			Main.instance().reloadConfig();
			player.sendMessage(ChatColor.GREEN + "Timer sign adedd.");
		}
	}
}
