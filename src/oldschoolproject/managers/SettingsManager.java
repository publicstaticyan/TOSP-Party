package oldschoolproject.managers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;

public class SettingsManager {
	
	private FileConfiguration config;
	private File file;
	
	private SettingsManager(File file, FileConfiguration config) { 
		this.file = file;
		this.config = config;
	}
	
	// This is REALLY wrong, but I would like to use the "(T) get" method and I still don't know how to override the
	// FileConfiguration class "get" method while keeping the my "load" method. So, for now, this is it.
	
 	public static SettingsManager load(String fileName) {
		File file = new File(Main.getInstance().getDataFolder(), fileName + ".yml");
		
		if (!file.exists()) {
			try { file.createNewFile(); } catch (Exception e) { e.printStackTrace(); }
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return new SettingsManager(file, config);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String path) {
		return (T) config.get(path);
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
		
		try { config.save(file); } catch (Exception e) { e.printStackTrace(); }
	}

	public Location getLocation(String path) {
		return new Location(Bukkit.getWorld("world"),
				config.getDouble(path + ".x"),
				config.getDouble(path + ".y"),
				config.getDouble(path + ".z"));
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
}
