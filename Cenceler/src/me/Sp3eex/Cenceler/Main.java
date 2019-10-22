package me.Sp3eex.Cenceler;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {

	public static Permission permission = null;
	
	@Override
	public void onEnable() {
		super.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this); 
		setupPermissions();
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		boolean isDisabled = getConfig().getBoolean("FullyDisableChat");
		
		if (isDisabled) { 
			if (getConfig().getString("disabled-chat-message") != "") {
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("disabled-chat-message")));	
			}
			event.setCancelled(true); 
		}
	}
	
	@EventHandler
	public void onServerCmd(ServerCommandEvent event) {
		boolean isDisabled = getConfig().getBoolean("DisableCommandsInConsole");
		if (isDisabled) { event.setCancelled(true); }
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
	    String command = event.getMessage();
	    
	    if (player.isOp() || permission.playerHas(player, "cenceler." + command.replace("/", ""))) {
	    	return;
	    }
	    
	    List<String> bCmds = getConfig().getStringList("blocked-ingame-commands");
	    for (String bCmd : bCmds)  {
	        if(command.equalsIgnoreCase(bCmd)) {
	        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));
	            event.setCancelled(true);
	        }
	    }
	}
}
