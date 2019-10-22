package me.Sp3eex.PvPStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Skull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		super.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		Commands cmds = new Commands(this);
		getCommand("pvp").setExecutor(cmds);
		getCommand("pvptop").setExecutor(cmds);
		
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
        	int counter = 0;
            @SuppressWarnings("deprecation")
			@Override
            public void run() {
            	counter++;
            	if (counter == 100) {
            		if (TopPvPers().size() == 0) { return; }
                	ConfigurationSection section = getConfig().getConfigurationSection("toppvps");
                	for (String toppvpname : section.getKeys(false)) {
                		List<String> locations = getConfig().getStringList("toppvps." + toppvpname);
                		Location first_head = new Location(
                			Bukkit.getWorld(locations.get(locations.size()-1).split(":")[0]),
                			Integer.parseInt(locations.get(locations.size()-1).split(":")[1]),
                			Integer.parseInt(locations.get(locations.size()-1).split(":")[2]),
                			Integer.parseInt(locations.get(locations.size()-1).split(":")[3])
                		); 
                		Location second_head = new Location(
                    		Bukkit.getWorld(locations.get(locations.size()-2).split(":")[0]),
                    		Integer.parseInt(locations.get(locations.size()-2).split(":")[1]),
                    		Integer.parseInt(locations.get(locations.size()-2).split(":")[2]),
                    		Integer.parseInt(locations.get(locations.size()-2).split(":")[3])
                    	); 
                		Location third_head = new Location(
                        	Bukkit.getWorld(locations.get(locations.size()-3).split(":")[0]),
                        	Integer.parseInt(locations.get(locations.size()-3).split(":")[1]),
                        	Integer.parseInt(locations.get(locations.size()-3).split(":")[2]),
                        	Integer.parseInt(locations.get(locations.size()-3).split(":")[3])
                        ); 
                		
                		int loc_x = second_head.getBlockX();
    					int loc_y = second_head.getBlockY();
    					int loc_z = second_head.getBlockZ();
    					Location la = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                        Location la2 = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                        Location la3 = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                        Location la4 = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                        second_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la2, 1);
                        second_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la3, 1);
                        second_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la4, 1);
                        second_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la, 1);
                        
                        int loc_x_1 = first_head.getBlockX();
    					int loc_y_1 = first_head.getBlockY();
    					int loc_z_1 = first_head.getBlockZ();
    					Location la1 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                        Location la21 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                        Location la31 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                        Location la41 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                        first_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la1, 1);
                        first_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la21, 1);
                        first_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la31, 1);
                        first_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la41, 1);
                        
                        int loc_x_11 = third_head.getBlockX();
    					int loc_y_11 = third_head.getBlockY();
    					int loc_z_11 = third_head.getBlockZ();
    					Location la15 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                        Location la215 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                        Location la315 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                        Location la415 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                        third_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la15, 1);
                        third_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la215, 1);
                        third_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la315, 1);
                        third_head.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, la415, 1);
                		
                		List<Entry<String, Integer>> list = TopPvPers();
                		if (list.size() >= 3) {
                			Entry<String, Integer> third = list.get(list.size()-1);
                			Entry<String, Integer> second = list.get(list.size()-2);
                			Entry<String, Integer> first = list.get(list.size()-3);
                			Skull skull = (Skull) third_head.getBlock().getState();
        					skull.setOwner(first.getKey());
        					skull.update(true, true);
        					
        					Skull skull2 = (Skull) first_head.getBlock().getState();
        					skull2.setOwner(second.getKey());
        					skull2.update(true, true);
        					
        					Skull skull3 = (Skull) second_head.getBlock().getState();
        					skull3.setOwner(third.getKey());
        					skull3.update(true, true);
                		} else {
                			if (list.size() == 2) {
                    			Entry<String, Integer> second = list.get(list.size()-2);
                    			Entry<String, Integer> first = list.get(list.size()-3);
                				Skull skull = (Skull) third_head.getBlock().getState();
            					skull.setOwner(first.getKey());
            					skull.update(true, true);
            					
            					Skull skull2 = (Skull) first_head.getBlock().getState();
            					skull2.setOwner(second.getKey());
            					skull2.update(true, true);
                			}
                			if (list.size() == 1) {
                    			Entry<String, Integer> first = list.get(list.size()-3);
                				Skull skull = (Skull) third_head.getBlock().getState();
            					skull.setOwner(first.getKey());
            					skull.update(true, true);
                			}
                			if (list.size() == 0) {
                				return;
                			}
                		}
                		
                	}
                	counter = 0;
            	} else {
            		if (counter == 0 || counter == 5 || counter == 10 || counter == 15 || counter == 20 || counter == 25
            			|| counter == 30 || counter == 35 || counter == 40 || counter == 45 || counter == 50 || counter == 55
            			|| counter == 60 || counter == 65 || counter == 70 || counter == 75 || counter == 80
            			|| counter == 85 || counter == 90 || counter == 95) {
            			ConfigurationSection section = getConfig().getConfigurationSection("toppvps");
            			ArrayList<String> toppvps = new ArrayList<String>();
            			for (String s : section.getKeys(false)) {
            				toppvps.add(s);
            			}
            			for (int i = 0; i < toppvps.size(); i++) {
            				List<String> locations = getConfig().getStringList("toppvps." + toppvps.get(i));
                    		Location first_head = new Location(
                    			Bukkit.getWorld(locations.get(locations.size()-1).split(":")[0]),
                    			Integer.parseInt(locations.get(locations.size()-1).split(":")[1]),
                    			Integer.parseInt(locations.get(locations.size()-1).split(":")[2]),
                    			Integer.parseInt(locations.get(locations.size()-1).split(":")[3])
                    		); 
                    		Location second_head = new Location(
                        		Bukkit.getWorld(locations.get(locations.size()-2).split(":")[0]),
                        		Integer.parseInt(locations.get(locations.size()-2).split(":")[1]),
                        		Integer.parseInt(locations.get(locations.size()-2).split(":")[2]),
                        		Integer.parseInt(locations.get(locations.size()-2).split(":")[3])
                        	); 
                    		Location third_head = new Location(
                            	Bukkit.getWorld(locations.get(locations.size()-3).split(":")[0]),
                            	Integer.parseInt(locations.get(locations.size()-3).split(":")[1]),
                            	Integer.parseInt(locations.get(locations.size()-3).split(":")[2]),
                            	Integer.parseInt(locations.get(locations.size()-3).split(":")[3])
                            ); 
                    		
                    		int loc_x = second_head.getBlockX();
        					int loc_y = second_head.getBlockY();
        					int loc_z = second_head.getBlockZ();
        					Location la = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                            Location la2 = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                            Location la3 = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                            Location la4 = new Location(second_head.getWorld(), loc_x+new Random().nextDouble(), loc_y+new Random().nextDouble(), loc_z+new Random().nextDouble());
                            second_head.getWorld().spawnParticle(Particle.SPELL_MOB, la2, 1);
                            second_head.getWorld().spawnParticle(Particle.SPELL_MOB, la3, 1);
                            second_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la4, 1);
                            second_head.getWorld().spawnParticle(Particle.HEART, la, 1);
                            
                            int loc_x_1 = first_head.getBlockX();
        					int loc_y_1 = first_head.getBlockY();
        					int loc_z_1 = first_head.getBlockZ();
        					Location la1 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                            Location la21 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                            Location la31 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                            Location la41 = new Location(second_head.getWorld(), loc_x_1+new Random().nextDouble(), loc_y_1+new Random().nextDouble(), loc_z_1+new Random().nextDouble());
                            first_head.getWorld().spawnParticle(Particle.SPELL_MOB, la1, 1);
                            first_head.getWorld().spawnParticle(Particle.SPELL_MOB, la21, 1);
                            first_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la31, 1);
                            first_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la41, 1);
                            
                            int loc_x_11 = third_head.getBlockX();
        					int loc_y_11 = third_head.getBlockY();
        					int loc_z_11 = third_head.getBlockZ();
        					Location la15 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                            Location la215 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                            Location la315 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                            Location la415 = new Location(second_head.getWorld(), loc_x_11+new Random().nextDouble(), loc_y_11+new Random().nextDouble(), loc_z_11+new Random().nextDouble());
                            third_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la15, 1);
                            third_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la215, 1);
                            third_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la315, 1);
                            third_head.getWorld().spawnParticle(Particle.SPELL_INSTANT, la415, 1);
            			}
            		}
            	}	
            }
        }, 3, 3);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		addDeaths(player.getName());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!getConfig().isSet("stats." + player.getName())) {
			getConfig().set("stats." + player.getName() + ".deaths", 0);
			getConfig().set("stats." + player.getName() + ".kills", 0);
			getConfig().set("stats." + player.getName() + ".mobkills", 0);
			saveConfig();
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
	    Entity killer = event.getEntity().getKiller();
	    if (killer instanceof Player && entity.getType() != EntityType.PLAYER) {
	    	Player killerp = (Player) killer;
	    	this.addMobKills(killerp.getName()); 
	    } else if (killer instanceof Player && entity.getType() == EntityType.PLAYER) {
	    	Player killerp = (Player) killer; Player otherp = (Player) entity;
	    	this.addKills(killerp.getName()); this.addDeaths(otherp.getName());
	    }
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		ConfigurationSection section = getConfig().getConfigurationSection("toppvps");
		ArrayList<String> toppvps = new ArrayList<String>();
		for (String s : section.getKeys(false)) {
			toppvps.add(s);
		}
		for (int i = 0; i < toppvps.size(); i++) {
			List<String> locations = getConfig().getStringList("toppvps." + toppvps.get(i));
			for (int u = 0; u < locations.size(); u++) {
				String[] split = locations.get(u).split(":");	
				String world = split[0];
				int x = Integer.parseInt(split[1]);
				int y = Integer.parseInt(split[2]);
				int z = Integer.parseInt(split[3]);
				if (event.getBlock().getWorld().getName().equalsIgnoreCase(world) 
						&& event.getBlock().getX() == x && event.getBlock().getY() == y && event.getBlock().getZ() == z) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	int getDeaths(String player) {
		return getConfig().getInt("stats." + player + ".deaths");
	}
	
	int getKills(String player) {
		return getConfig().getInt("stats." + player + ".kills");
	}
	
	int getMobKills(String player) {
		return getConfig().getInt("stats." + player + ".mobkills");
	}
	
	void addDeaths(String player) {
		int currentDeaths = getConfig().getInt("stats." + player + ".deaths");
		getConfig().set("stats." + player + ".deaths", currentDeaths+1);
		saveConfig(); reloadConfig();
	}
	
	void addKills(String player) {
		int currentKills = getConfig().getInt("stats." + player + ".kills");
		getConfig().set("stats." + player + ".kills", currentKills+1);
		saveConfig(); reloadConfig();
	}
	
	void addMobKills(String player) {
		int currentMobKills = getConfig().getInt("stats." + player + ".mobkills");
		getConfig().set("stats." + player + ".mobkills", currentMobKills+1);
		saveConfig(); reloadConfig();
	}
	
	boolean doesSectionExists(String ID) {
		ConfigurationSection section = getConfig().getConfigurationSection("toppvps");
		if (section.contains(ID)) {
			return true;
		} else {
			return false;
		}
	}
	
	void AddNewTopPvP(Player player, Location[] locations, Location[] headlocations) {
		String ID = String.valueOf(new Random().nextInt(89999) + 10000);
		for (Location loc : locations) {
			int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
			String formattedlocation = player.getWorld().getName() + ":" + x + ":" + y + ":" + z;
			List<String> list = getConfig().getStringList("toppvps." + ID);
			list.add(formattedlocation);
			getConfig().set("toppvps." + ID, list);
		}
		for (Location loc : headlocations) {
			int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
			String formattedlocation = player.getWorld().getName() + ":" + x + ":" + y + ":" + z;
			List<String> list = getConfig().getStringList("toppvps." + ID);
			list.add(formattedlocation);
			getConfig().set("toppvps." + ID, list);
		}
		saveConfig(); reloadConfig();
	}
	
	public void getTopPvPers(Player player) {
		ConfigurationSection section = getConfig().getConfigurationSection("stats");
		
		HashMap<String, Integer> kills_hashmap = new HashMap<String, Integer>();
		for (String playername : section.getKeys(false)) {
			kills_hashmap.put(playername, getConfig().getInt("stats." + playername + ".kills"));
		}
		
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(kills_hashmap.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> entry, Entry<String, Integer> sentry) {
				return entry.getValue().compareTo(sentry.getValue());
			}
			
		});
		
		if (list.size() >= 3) {
			Entry<String, Integer> first = list.get(list.size()-1);
			Entry<String, Integer> second = list.get(list.size()-2);
			Entry<String, Integer> third = list.get(list.size()-3);
			player.sendMessage(ChatColor.BLUE + "Top Pvpers: " + 
					ChatColor.YELLOW + first.getKey() + ChatColor.GRAY + "(" + getKills(first.getKey()) + ChatColor.GRAY + ") ," +
					ChatColor.YELLOW + second.getKey() + ChatColor.GRAY + "(" + getKills(second.getKey()) + ChatColor.GRAY + ") ," +
					ChatColor.YELLOW + third.getKey() + ChatColor.GRAY + "(" + getKills(third.getKey()) + ChatColor.GRAY + ")");
		} else {
			if (list.size() == 2) {
				Entry<String, Integer> first = list.get(list.size()-1);
				Entry<String, Integer> second = list.get(list.size()-2);
				player.sendMessage(ChatColor.BLUE + "Top Pvpers: " + 
						ChatColor.YELLOW + first.getKey() + ChatColor.GRAY + "(" + getKills(first.getKey()) + ChatColor.GRAY + ") ," +
						ChatColor.YELLOW + second.getKey() + ChatColor.GRAY + "(" + getKills(second.getKey()) + ChatColor.GRAY + ")");
			}
			if (list.size() == 1) {
				Entry<String, Integer> first = list.get(list.size()-1);
				player.sendMessage(ChatColor.BLUE + "Top Pvpers: " + 
						ChatColor.YELLOW + first.getKey() + ChatColor.GRAY + "(" + getKills(first.getKey()) + ChatColor.GRAY + ")");
			}
			if (list.size() == 0) {
				player.sendMessage(ChatColor.RED + "There are no top pvpers right now. Time to kill someone!");
			}
		}
	}
	
	public List<Entry<String, Integer>> TopPvPers() {
		ConfigurationSection section = getConfig().getConfigurationSection("stats");
		
		HashMap<String, Integer> kills_hashmap = new HashMap<String, Integer>();
		for (String playername : section.getKeys(false)) {
			kills_hashmap.put(playername, getConfig().getInt("stats." + playername + ".kills"));
		}
		
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(kills_hashmap.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> entry, Entry<String, Integer> sentry) {
				return entry.getValue().compareTo(sentry.getValue());
			}
			
		});
		
		List<Entry<String, Integer>> toreturn = new LinkedList<Entry<String, Integer>>();
		if (list.size() >= 3) {
			Entry<String, Integer> first = list.get(list.size()-1);
			Entry<String, Integer> second = list.get(list.size()-2);
			Entry<String, Integer> third = list.get(list.size()-3);
			toreturn.add(first); toreturn.add(second); toreturn.add(third);
		} else {
			if (list.size() == 2) {
				Entry<String, Integer> first = list.get(list.size()-1);
				Entry<String, Integer> second = list.get(list.size()-2);
				toreturn.add(first); toreturn.add(second);
			}
			if (list.size() == 1) {
				Entry<String, Integer> first = list.get(list.size()-1);
				toreturn.add(first);
			}
			if (list.size() == 0) {
				return toreturn; // empty list != null
			}
		}
		return list;
	}
}
