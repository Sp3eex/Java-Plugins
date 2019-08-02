package _UtilsClasses;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager extends JavaPlugin {

	public ConfigManager plugin;
	public FileConfiguration fileconfig;
	public File file;

	public void SetupCustomConfig(String File) {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		file = new File(getDataFolder(), FileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage("The " + FileName + " file has been created.");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage("Could not create the " + FileName + " file.");
			}
		}
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getFileConfig() {
		return fileconfig;
	}

	public void saveCustomConfig() {
		try {
			fileconfig.save(file);
			Bukkit.getServer().getConsoleSender().sendMessage("The players.yml file has been saved.");

		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage("Could not save the players.yml file.");
		}
	}

	public void reloadCustomConfig() {
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}
}
