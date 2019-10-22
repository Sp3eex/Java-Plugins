package me.Sp3eex.SuperRespawn;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	static HashMap<Player, Integer> respawners = new HashMap<Player, Integer>();
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Location loc = player.getLocation();
		for (double y = 0; y <= 5; y += 0.45) {
            double x = 0.7 * Math.cos(y);
            double z = 0.7 * Math.sin(y);
            player.getWorld().spawnParticle(Particle.SPELL_WITCH, new Location(player.getWorld(), 
            		(float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z)), 1);
        }
		if (!respawners.containsKey(player)) { 
			player.spigot().respawn();
			player.setGameMode(GameMode.SPECTATOR);
			player.teleport(loc);
            int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                int counter = 6; 
                @Override 
                public void run() { 
                	counter--;
                    if (counter == 1 || counter == 2 || counter == 3 || counter == 4 || counter == 5) {
                    	player.sendTitle(ChatColor.YELLOW + "Respawning..", ChatColor.YELLOW + "In: " + counter, 15, 30, 15);
                    }
                    if (counter == 0) {
                    	player.setGameMode(GameMode.SURVIVAL);
                    	player.sendTitle(ChatColor.YELLOW + "Respawned", "", 5, 25, 5);
                    	player.teleport(player.getWorld().getSpawnLocation());
                    	CencelRunnable(player);
                    }
                }
            },0 , 28);
            respawners.put(player, taskID);
        }
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.getPlayer().setGameMode(GameMode.SURVIVAL);
		CencelRunnable(event.getPlayer());
	}
	
	public void CencelRunnable(Player player) {
        if (respawners.containsKey(player)) {
            int taskToCencel = respawners.get(player);
            Bukkit.getScheduler().cancelTask(taskToCencel);
            respawners.remove(player);
        }
    }
}
