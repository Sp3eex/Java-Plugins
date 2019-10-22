package me.Sp3eex.SuperPressurePlates;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerInteractWithPressurePlate(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK) { 
			Vector vector = new Vector();
			double rotX = player.getLocation().getYaw();
			double rotY = player.getLocation().getPitch();
			vector.setY(-Math.sin(Math.toRadians(2)));
			double h = Math.cos(Math.toRadians(rotY));
			vector.setX(-h * Math.sin(Math.toRadians(rotX)));
			vector.setZ(h * Math.cos(Math.toRadians(rotX)));
			player.setVelocity(vector.multiply(2));
		} else if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
			player.teleport(player.getWorld().getSpawnLocation());
		}
	}
}
