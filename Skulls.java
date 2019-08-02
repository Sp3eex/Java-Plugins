package me.Sp3eex.Skulls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("skull") && sender instanceof Player) {
			Player player = (Player) sender;
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
	        SkullMeta skullMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
	        skullMeta.setOwner(args[0]);
	        skull.setItemMeta(skullMeta);
	        if (args[0] != null) {
	        	skullMeta.setDisplayName(ChatColor.RESET + skullMeta.getOwningPlayer().toString());
	        }
	        player.getInventory().addItem(skull); return true;
		} else if (cmd.getName().equalsIgnoreCase("skull") && args.length == 0) {
			sender.sendMessage(ChatColor.RED + "/skull <playername>"); 
			return true;
		}
		return true;
	}	
}
