package me.Sp3eex.LootChests;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin{

	private static Main instance;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Commands cmds = new Commands();
		getCommand("lc").setExecutor(cmds);
		SignListener sl = new SignListener();
		Bukkit.getServer().getPluginManager().registerEvents(sl, this);
		instance = this;
		
		int maxseconds = getConfig().getInt("maxseconds");
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
        	int counter = maxseconds;
            @Override
            public void run() {
            	counter--;
            	if (counter == 0) {
            		ConfigurationSection section = Main.instance().getConfig().getConfigurationSection("lootchests");
            		ArrayList<String> list = new ArrayList<String>();
            		try{ 
            			for (String key : section.getKeys(false)) {
            				list.add(key);
            			}
            		} catch (Exception e) { }
                	if (list.size() == 0) { return; }
                	for (String ID : list) {
                		int x = getConfig().getInt("lootchests." + ID + ".Location.x");
    					int y = getConfig().getInt("lootchests." + ID + ".Location.y");
    					int z = getConfig().getInt("lootchests." + ID + ".Location.z");
    					World world = Bukkit.getWorld(getConfig().getString("lootchests." + ID + ".Location.world"));
    					Location loc = new Location(world, x, y, z);
    					
    					if (loc.getBlock().getType() != Material.CHEST) {
    						loc.getBlock().setType(Material.CHEST);
    					}
    					
    					Chest chest = (Chest) loc.getBlock().getState();
    					chest.getInventory().clear();
    					for (int  i = 0; i < chest.getInventory().getSize(); i++) {
    						chest.getInventory().setItem(i, Items.GetRandomItem());
    					}
    					
                        ConfigurationSection ssection = Main.instance().getConfig().getConfigurationSection("signs");
                		ArrayList<String> slist = new ArrayList<String>();
                		try { 
                			for (String key : ssection.getKeys(false)) {
                				slist.add(key);
                			}
                		} catch (Exception e) { }
                		if (slist.size() == 0) { return; }
                		for (String IDa : slist) {
                    		int xx = getConfig().getInt("signs." + IDa + ".Location.x");
                			int yy = getConfig().getInt("signs." + IDa + ".Location.y");
                			int zz = getConfig().getInt("signs." + IDa + ".Location.z");
                			String worldy = getConfig().getString("signs." + IDa + ".Location.world");
                			Location locc = new Location(Bukkit.getWorld(worldy), xx, yy, zz);
                			Sign sign = (Sign) locc.getBlock().getState();
            				sign.setLine(2, ChatColor.GREEN + "RESTOCKED");
            				sign.update();
                		}
                        
                        counter = maxseconds;
                	}
            	} else {
            		ConfigurationSection ssection = Main.instance().getConfig().getConfigurationSection("signs");
            		ArrayList<String> slist = new ArrayList<String>();
            		try { 
            			for (String key : ssection.getKeys(false)) {
            				slist.add(key);
            			}
            		} catch (Exception e) { }
            		if (slist.size() == 0) { return; }
            		for (String ID : slist) {
                		int x = getConfig().getInt("signs." + ID + ".Location.x");
            			int y = getConfig().getInt("signs." + ID + ".Location.y");
            			int z = getConfig().getInt("signs." + ID + ".Location.z");
            			String world = getConfig().getString("signs." + ID + ".Location.world");
            			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
            			Sign sign = (Sign) loc.getBlock().getState();
        				sign.setLine(2, ChatColor.RED + "" + counter + " seconds.");
        				sign.update();
            		}
            	}
            }
        }, 0, 20);
    }
	
	public static Main instance() { 
		return instance;
	}
}
