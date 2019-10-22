package me.Sp3eex.ChatTags;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChListener implements Listener {

	@EventHandler
	public void onPlayerAsyncChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String[] args = event.getMessage().split(" ");
		for (String arg : args) {
			if (arg.charAt(0) == '@') {
				Player toTag = Bukkit.getPlayer(arg.replace("@", ""));
				if (toTag != null) {
					if (toTag.getName() == player.getName()) {
						event.setCancelled(true);
						player.sendMessage(ChatColor.RED + "You cannot tag yourself.");
						return;
					}
					player.sendMessage(ChatColor.GRAY + ">>>" + ChatColor.GREEN + "You tagged " + toTag.getName() + " in chat.");
					toTag.playSound(toTag.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 5);
					toTag.sendMessage(ChatColor.GRAY + ">>>" + ChatColor.GREEN + player.getName() + " tagged you in the chat.");
					return;
				}
			}
		}
		
	}
}
