package me.Sp3eex.Crates;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	private static FileConfiguration config;
	private static File file;
	public static FileConfiguration GetConfigFile() { return config; }

	public static void SaveConfigFile(String configName) {
    	try {
    		File tfile = new File(Main.Instance().getDataFolder(), configName);
			config.save(tfile); 
			file = new File(Main.Instance().getDataFolder(), configName);
		} catch (IOException e) { 
			Main.Instance().getLogger().info("Could not save the " + config.getName() + " file.");
		}
    }
	
	public static void CreateConfigFile(String configName) {
		if (!Main.Instance().getDataFolder().exists()) {
			Main.Instance().getDataFolder().mkdir();
        }
        file = new File(Main.Instance().getDataFolder(), configName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                config.save(file);
                Main.Instance().getLogger().info("Config file created.");
            } catch (IOException e) {
            	Main.Instance().getLogger().info("Could not create the " + configName + " file.");
            }
        } else {
        	config = YamlConfiguration.loadConfiguration(file);
        	Main.Instance().getLogger().info("Config file loaded.");
        }
	}
}
