package me.Sp3eex.AppleBurp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onConsumeItem(PlayerItemConsumeEvent event) {
		ItemStack consumedItem = event.getItem();
		if (consumedItem.getType() == Material.APPLE) {
			event.getPlayer().giveExp(5);
			Location loc = event.getPlayer().getLocation();
	        for (double y = 0; y <= 5; y += 0.45) {
	            double x = 0.7 * Math.cos(y);
	            double z = 0.7 * Math.sin(y);
	            event.getPlayer().getWorld().spawnParticle(Particle.SPELL, new Location(event.getPlayer().getWorld(), 
	            		(float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z)), 1);
	        }
		}
	}
}
