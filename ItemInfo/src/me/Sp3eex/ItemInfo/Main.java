package me.Sp3eex.ItemInfo;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("item") && args.length == 0) { 
			try {
			int itemTypeId = player.getInventory().getItemInMainHand().getType().getId();
			short itemDur = player.getInventory().getItemInMainHand().getDurability();
			int count = player.getInventory().getItemInMainHand().getAmount();
			if (itemDur == 0 == true && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
				player.sendMessage(ChatColor.GRAY + "name: " + ChatColor.BLUE + player.getInventory().getItemInMainHand().getType().name() + 
						ChatColor.GRAY + " | " + ChatColor.BLUE + "(" + count + "x)" + ChatColor.BLUE + player.getInventory().getItemInMainHand().getType().name().replace("_", " ").toLowerCase() + 
						ChatColor.GRAY + " | id: " + ChatColor.BLUE + itemTypeId); return true; 
			} else if (itemDur == 0 == false && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
				player.sendMessage(ChatColor.GRAY + "name: " + ChatColor.BLUE + player.getInventory().getItemInMainHand().getType().name() + 
						ChatColor.GRAY + " | " + ChatColor.BLUE + "(" + count + "x)" + ChatColor.BLUE + player.getInventory().getItemInMainHand().getType().name().replace("_", " ").toLowerCase() + 
						ChatColor.GRAY + " | id: " + ChatColor.BLUE + itemTypeId + ":" + itemDur); return true; 
				} 	
			} catch(Exception e) {
				player.sendMessage(ChatColor.RED + "You're not holding an item in your main hand.");
				return true;
			}
		}
		return true;
	}
}