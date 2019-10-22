package me.Sp3eex.MiniCrates;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

	private Main main;

	Items it = new Items();

	public Commands(Main instance) {
		main = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("crate")) {
				if (!player.isOp()) {
					player.sendMessage(ChatColor.WHITE + "Unknown command. Type \"/help\" for help.");
					return true;
				}
				if (args.length == 0) {
					player.sendMessage("" + ChatColor.BLUE + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 50));
					player.sendMessage(ChatColor.GRAY + "/crate get" + ChatColor.WHITE + " get the crate.");
					player.sendMessage(ChatColor.GRAY + "/crate delete" + ChatColor.WHITE + " remove the crate "
							+ ChatColor.UNDERLINE + "when standing on a crate.");
					player.sendMessage(ChatColor.GRAY + "/crate add" + ChatColor.WHITE + " add the crate "
							+ ChatColor.UNDERLINE + "when standing on a crate.");
					player.sendMessage(ChatColor.GRAY + "/crate list" + ChatColor.WHITE + " list of crates.");
					player.sendMessage(
							ChatColor.GRAY + "/crate key <player>" + ChatColor.WHITE + " get the crate key.");
					player.sendMessage("" + ChatColor.BLUE + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 50));
					return true;
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("add")) {
						try {
							if (!main.isChestValid(player.getLocation())) {
								main.CreateCrate(player, player.getLocation());
								player.sendMessage(ChatColor.GRAY + "Crate set on location: " + ChatColor.YELLOW
										+ player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY()
										+ ", " + player.getLocation().getBlockZ());
								return true;
							} else {
								player.sendMessage(ChatColor.RED + "Crate already exists on that location.");
								return true;
							}
						} catch (IOException e) {
							player.sendMessage(ChatColor.RED + "Erorr! Cannot create a crate...");
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("get")) {
						player.getInventory().addItem(new ItemStack(Material.CHEST));
						return true;
					}
					if (args[0].equalsIgnoreCase("delete")) {
						if (!main.isChestValid(player.getLocation())) {
							sender.sendMessage(ChatColor.RED + "Crate does not exist on your location.");
							return true;
						}
						for (String crateloc : main.GetCrates()) {
							String world = main.getConfig().getString("crates." + crateloc + ".world");
							int x = main.getConfig().getInt("crates." + crateloc + ".x");
							int y = main.getConfig().getInt("crates." + crateloc + ".y");
							int z = main.getConfig().getInt("crates." + crateloc + ".z");
							if (player.getWorld().getName().equalsIgnoreCase(world)
									&& player.getLocation().getBlockX() == x && player.getLocation().getBlockY() == y
									&& player.getLocation().getBlockZ() == z) {
								player.getLocation().getBlock().breakNaturally();
								main.getConfig().set("crates." + crateloc, null);
								main.saveConfig();
								main.reloadConfig();
								sender.sendMessage(ChatColor.GRAY + "Crate with ID '" + ChatColor.RED + crateloc
										+ ChatColor.GRAY + "' has been removed.");
								return true;
							}
						}
					}

					if (args[0].equalsIgnoreCase("list")) {
						if (main.GetCrates().size() == 0) {
							player.sendMessage(ChatColor.RED + "There are no crates.");
							return true;
						}
						for (int i = 0; i < main.GetCrates().size(); i++) {
							String crate = main.GetCrates().get(i);
							String world = main.getConfig().getString("crates." + crate + ".world");
							int x = Integer.parseInt(main.getConfig().getString("crates." + crate + ".x"));
							int y = Integer.parseInt(main.getConfig().getString("crates." + crate + ".y"));
							int z = Integer.parseInt(main.getConfig().getString("crates." + crate + ".z"));
							sender.sendMessage(i + 1 + ". '" + main.GetCrates().get(i) + "'" + ChatColor.GRAY
									+ " (World: " + world + ", x: " + x + ", y: " + y + ", z: " + z + ")");
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("key")) {
						if (player.getInventory().firstEmpty() != -1) {
							player.getInventory().addItem(it.CrateKey());
						} else {
							player.getWorld().dropItem(player.getLocation(), it.CrateKey());
						}
						return true;
					}
				} else if (args.length == 2 && player.isOp()) {
					Player dplayer = Bukkit.getPlayer(args[1]);
					if (dplayer != null) {
						if (dplayer.getInventory().firstEmpty() != -1) {
							dplayer.getInventory().addItem(it.CrateKey());
						} else {
							dplayer.getWorld().dropItem(player.getLocation(), it.CrateKey());
						}
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "Player '" + args[1] + "' does not exist / is not online.");
					}
				}
			}
		} else if (sender instanceof ConsoleCommandSender) {
			if (cmd.getName().equalsIgnoreCase("crate")) {
				if (args.length == 0) {
					sender.sendMessage("-------------------------");
					sender.sendMessage("MiniCrates by Sp3eex");
					sender.sendMessage("Discord: Sp3eex#1427");
					sender.sendMessage("Email: janezvod0000@gmail.com");
					sender.sendMessage("-------------------------");
				} else if (args.length == 1 && args[0].equalsIgnoreCase("key")) {
					sender.sendMessage("/crate key <player>");
				} else if (args.length == 2 && args[0].equalsIgnoreCase("key")) {
					if (args[1].equalsIgnoreCase("*") || args[1].equalsIgnoreCase("@a")) {
						for (Player kplayer : Bukkit.getServer().getOnlinePlayers()) {
							if (kplayer.getInventory().firstEmpty() != -1) {
								kplayer.getInventory().addItem(it.CrateKey());
							} else {
								kplayer.getWorld().dropItem(kplayer.getLocation(), it.CrateKey());
							}
						}
					}
					Player dplayer = Bukkit.getPlayer(args[1]);
					if (dplayer != null) {
						if (dplayer.getInventory().firstEmpty() != -1) {
							dplayer.getInventory().addItem(it.CrateKey());
						} else {
							dplayer.getWorld().dropItem(dplayer.getLocation(), it.CrateKey());
						}
						return true;
					}
				}
			}
			return true;
		}
		return true;
	}
}