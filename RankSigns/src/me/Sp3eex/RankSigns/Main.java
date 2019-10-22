package me.Sp3eex.RankSigns;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {

	public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
	
	@Override
	public void onEnable() {
		super.saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Commands commands = new Commands(this);
		getCommand("rs").setExecutor(commands);
		this.setupPermissions();
		this.setupChat();
		this.setupEconomy();
	}

	Commands cmds = new Commands(this);
	
	@Override
	public void onDisable() {
		super.saveDefaultConfig();
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

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
	
    String removeColors(String input) {
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
    	return output;
    }
    
    @EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] lines = event.getLines();
		Location signloc = event.getBlock().getLocation();
		if (lines[0].equalsIgnoreCase("[ranksign]") && player.isOp()) {
			if (lines[1].length() == 0 || lines[2].length() == 0){
				player.sendMessage(ChatColor.RED + "RankSign format is wrong! The format should be: ");
				player.sendMessage(ChatColor.YELLOW + "[ranksign]");
				player.sendMessage(ChatColor.YELLOW + "[rankname]");
				player.sendMessage(ChatColor.YELLOW + "price");
				player.sendMessage(ChatColor.YELLOW + "optional custom text");
				event.setCancelled(true);
				return;
			} else {
				String rank = ChatColor.stripColor(this.removeColors(lines[1].replace("[", "").replace("]", "")));
				if (cmds.signExists(rank)) {
					player.sendMessage(ChatColor.RED + "A ranksign with that name already exists! To delete it type '/rs delete " + rank + "'");
					event.setCancelled(true); 
					return;
				}
				
				int price = 0;
				try { 
					price = Integer.parseInt(lines[2]);
				} catch (Exception e) {
					player.sendMessage(ChatColor.RED + "RankSign format is wrong! The format should be: ");
					player.sendMessage(ChatColor.YELLOW + "[ranksign]");
					player.sendMessage(ChatColor.YELLOW + "[rankname]");
					player.sendMessage(ChatColor.YELLOW + "price");
					player.sendMessage(ChatColor.YELLOW + "optional custom text");
					event.setCancelled(true);
					return;
				}
				
				getConfig().set("signs." + rank + ".world", signloc.getWorld().getName());
				getConfig().set("signs." + rank + ".x", signloc.getBlockX());
				getConfig().set("signs." + rank + ".y", signloc.getBlockY());
				getConfig().set("signs." + rank + ".z", signloc.getBlockZ());
				getConfig().set("signs." + rank + ".rank", rank);
				getConfig().set("signs." + rank + ".price", String.valueOf(price));
				getConfig().set("signs." + rank + ".permissions", Arrays.asList(""));
				saveConfig(); reloadConfig();
				
				event.setLine(0, ChatColor.WHITE + "Rank");
				event.setLine(1, ChatColor.translateAlternateColorCodes('&', lines[1]));
				event.setLine(2, ChatColor.WHITE + "$" + String.valueOf(price));
				event.setLine(3, ChatColor.translateAlternateColorCodes('&', lines[3]));
				
				player.sendMessage(ChatColor.GRAY + "Ranksign for rank " + rank + ChatColor.GRAY + " (" + lines[1] + 
						ChatColor.GRAY + ")" + ChatColor.GRAY + " created in world: " + signloc.getWorld().getName() + 
						", x: " + signloc.getBlockX() + ", y: " + signloc.getBlockY() + ", z: " + signloc.getBlockZ() + ".");
			}
		}
    }
    
    @EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		try {
			for (String locx : cmds.getSigns()) {
				String world = getConfig().getString("signs." + locx + ".world");
				int x = Integer.parseInt(getConfig().getString("signs." + locx + ".x"));
				int y = Integer.parseInt(getConfig().getString("signs." + locx + ".y"));
				int z = Integer.parseInt(getConfig().getString("signs." + locx + ".z"));
				if (loc.getWorld().getName().equalsIgnoreCase(world) && loc.getBlockX() == x && loc.getBlockY() == y && loc.getBlockZ() == z) {
					if (!player.isOp()) {
						event.setCancelled(true);
					} else {
						if (!player.isSneaking()) {
							player.sendMessage(ChatColor.RED + "You must be sneaking to break rank signs.");
							event.setCancelled(true);
						} else {
							getConfig().set("signs." + locx, null);
							saveConfig(); reloadConfig();
							player.sendMessage(ChatColor.RED + "Ranksign '" + locx + "' removed. (world: " + world + ", x: " + x + ", y: " + y + ", z: " + z + ")");
						}
					}
				}
			}
		} catch (Exception e) {
			return;
		}
    }
    
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getState() instanceof Sign) {
				Player player = event.getPlayer();
				Location location = event.getClickedBlock().getLocation();
				Sign sign = (Sign) event.getClickedBlock().getState();
				String[] lines = sign.getLines();
				String rank = ChatColor.stripColor(removeColors(lines[1].replace("[", "").replace("]", "")));
				if (rank.equalsIgnoreCase("")) { return; }
				if (getConfig().isSet("signs." + rank)) {
					int price = Integer.parseInt(getConfig().getString("signs." + rank + ".price").replace("$", ""));
					double playerbalance = economy.getBalance(player);
					String world = getConfig().getString("signs." + rank + ".world");
					int x = Integer.parseInt(getConfig().getString("signs." + rank + ".x"));
					int y = Integer.parseInt(getConfig().getString("signs." + rank + ".y"));
					int z = Integer.parseInt(getConfig().getString("signs." + rank + ".z"));
					if (location.getWorld().getName().equalsIgnoreCase(world) && location.getBlockX() == x && location.getBlockY() == y && location.getBlockZ() == z) {
						if (permission.playerInGroup(player, rank)) { 
							player.sendMessage(ChatColor.RED + "You already bought this rank.");
							return; 
						}
						if (!permission.playerHas(player, "ranksigns." + rank)) { 
							player.sendMessage(ChatColor.RED + "You cant buy this rank yet.");
							return; 
						}
						if(playerbalance >= price) {
							player.sendMessage(ChatColor.BLUE + "Congratulations! " + ChatColor.GRAY + "You bought a " + rank + " rank for $" + price + ".");
							Bukkit.broadcastMessage(ChatColor.GRAY + ">> " + ChatColor.YELLOW + player.getName() + " just bought a " + rank + " rank!");
							economy.withdrawPlayer(player, price);
							permission.playerAddGroup("world", player, rank);
							permission.playerAddGroup("world_nether", player, rank);
							permission.playerAddGroup("world_the_end", player, rank);
							permission.playerAddGroup("Askyblock", player, rank);
						} else {
							player.sendMessage(ChatColor.RED + "You dont have enough money.");
						}
					}
				}
			}
		}
	}
}
