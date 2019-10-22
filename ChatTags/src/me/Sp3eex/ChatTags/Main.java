package me.Sp3eex.ChatTags;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		ChListener chl = new ChListener();
		Bukkit.getServer().getPluginManager().registerEvents(chl, this);
	}
}
