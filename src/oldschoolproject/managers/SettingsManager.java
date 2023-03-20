package oldschoolproject.managers;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;

public class SettingsManager {
	
	private static final SettingsManager 
		configuration = new SettingsManager("config"),
		arenas = new SettingsManager("arenas"),
		signs = new SettingsManager("signs");

	public static SettingsManager getConfig() {
		return configuration;
	}

	public static SettingsManager getArenas() {
		return arenas;
	}

	public static SettingsManager getSigns() {
		return signs;
	}
	
	private File file;
	private static FileConfiguration fileConfiguration;
	
	public SettingsManager(String fileName) {
		file = new File(Main.getInstance().getDataFolder(), fileName + ".yml");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) { e.printStackTrace(); }
		}
		
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String path) {
		return (T) fileConfiguration.get(path);
	}
	
	public Set<String> getKeys() {
		return fileConfiguration.getKeys(false);
	}
	
	public void set(String path, Object value) {
		fileConfiguration.set(path, value);
		save();
	}
	
	public boolean contains(String path) {
		return fileConfiguration.contains(path);
	}
	
	public ConfigurationSection createSection(String path) {
		ConfigurationSection section = fileConfiguration.createSection(path);
		save();
		return section;
	}
	
	public ConfigurationSection getSection(String path) {
		ConfigurationSection section = fileConfiguration.getConfigurationSection(path);
		return section;
	}
	
	private void save() {
		try {
			fileConfiguration.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveLocation(Location location, ConfigurationSection section) {
		section.set("world", location.getWorld().getName());
		section.set("x", location.getX());
		section.set("y", location.getY());
		section.set("z", location.getZ());
	}
	
	public static Location loadLocation(ConfigurationSection section) {
		return new Location(
				Bukkit.getServer().getWorld(section.getString("world")),
				section.getDouble("x"),
				section.getDouble("y"),
				section.getDouble("z")
		);
	}
}
