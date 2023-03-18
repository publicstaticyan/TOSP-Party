package oldschoolproject.Managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;

public class ConfigurationFile {
	
	private static File file;
	private static FileConfiguration fileConfiguration;
	
	public ConfigurationFile(String fileName) {
		file = new File(Main.getInstance().getDataFolder(), fileName);
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		saveConfig();
	}
	
	public void load() {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) { e.printStackTrace(); }
		
		Main.getInstance().getLogger().info("[ConfigurationFile] File " + file.getName() + " loaded");
	}
	
	public static void saveConfig() {
		try {
			fileConfiguration.save(file);
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static FileConfiguration getConfig() {
		return fileConfiguration;
	}
	
	public static Location getSpawnLocation() {
		if (getConfig().get("spawn") == null) {
			return new Location(Bukkit.getWorld("world"), 0, 0, 0);
		}
		
		return new Location(Bukkit.getWorld("world"), getConfig().getInt("spawn.X"), getConfig().getInt("spawn.Y"), getConfig().getInt("spawn.Z"));
	}
}
