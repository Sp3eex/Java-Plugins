package me.Sp3eex.CPrefix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {

	public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
	
	@Override
	public void onEnable() {
		this.setupPermissions();
		this.setupChat();
		this.setupEconomy();
	}
	
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }
    
    int getPrefixLenght(String input) {
    	String output = input
    		.replace("&0", "")
    		.replace("&1", "")
    		.replace("&2", "")
    		.replace("&3", "")
    		.replace("&4", "")
    		.replace("&5", "")
    		.replace("&6", "")
    		.replace("&7", "")
    		.replace("&8", "")
    		.replace("&9", "")
    		.replace("&a", "")
    		.replace("&b", "")
    		.replace("&c", "")
    		.replace("&d", "")
    		.replace("&e", "")
    		.replace("&f", "")
    		.replace("&k", "")
    		.replace("&l", "")
    		.replace("&m", "")
    		.replace("&n", "")
    		.replace("&o", "")
    		.replace("&r", "");
    	return output.length();
    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("prefix")) {
				if (!permission.playerHas(player, "cprefix.prefix")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				if (args.length == 0) {
					if (permission.playerHas(player, "cprefix.prefix.color") && !permission.playerHas(player, "cprefix.prefix.all")) {
						player.sendMessage(ChatColor.GRAY + "Set your prefix: /prefix <prefix> | Max. prefix lenght: 20");
					}
					if (permission.playerHas(player, "cprefix.prefix.all")) {
						player.sendMessage(ChatColor.GRAY + "Set your prefix: /prefix <prefix> | Max. prefix lenght: 35");
					}
					player.sendMessage(ChatColor.GRAY + "Remove your prefix: /prefix remove");
					return true;
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("remove")) {
						player.sendMessage(ChatColor.GREEN + "Prefix removed.");
						chat.setPlayerPrefix(player, "&f");
						return true;
					}
					int playerbalance = (int) economy.getBalance(player);
					if (permission.playerHas(player, "cprefix.prefix.color")) {
						String setprefix = args[0].replace("&k", "").replace("&l", "").replace("&m", "").replace("&n", "").replace("&o", "").replace("&r", "").replace("%b%", Integer.toString(playerbalance)); 
						if (getPrefixLenght(setprefix) > 20) {
							player.sendMessage(ChatColor.RED + "Prefix cannot be longer than 20 char.");
							return true;
						}
						if (setprefix.contains("Owner") || setprefix.contains("Moderator") || setprefix.contains("Chat-Mod") ||
							setprefix.contains("Admin") || setprefix.contains("Helper") || setprefix.contains("owner") || 
							setprefix.contains("moderator") || setprefix.contains("chat-mod") || setprefix.contains("admin") || setprefix.contains("helper")) {
							player.sendMessage(ChatColor.RED + "You can't use that prefix.");
							return true;
						}
						chat.setPlayerPrefix(player, setprefix);
						player.sendMessage(ChatColor.GRAY + "Your prefix has been updated: " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', setprefix.replace("%b%", Integer.toString(playerbalance))));
						return true;
					} else if (permission.playerHas(player, "cprefix.prefix.all")) {
						String setprefix = args[0].replace("&k", "").replace("%b%", Integer.toString(playerbalance));
						if (getPrefixLenght(setprefix) > 35) {
							player.sendMessage(ChatColor.RED + "Prefix cannot be longer than 35 char.");
							return true;
						}
						if (setprefix.contains("Owner") || setprefix.contains("Moderator") || setprefix.contains("Chat-Mod") ||
								setprefix.contains("Admin") || setprefix.contains("Helper") || setprefix.contains("owner") || 
								setprefix.contains("moderator") || setprefix.contains("chat-mod") || setprefix.contains("admin") || setprefix.contains("helper")) {
								player.sendMessage(ChatColor.RED + "You can't use that prefix.");
								return true;
							}
						chat.setPlayerPrefix(player, setprefix);
						player.sendMessage(ChatColor.GRAY + "Your prefix has been updated: " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', setprefix));
						return true;
					}
					return true;
				}
			}
		} else if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage("Only in-game players can use this command.");
		}
		return true;
	}
}
