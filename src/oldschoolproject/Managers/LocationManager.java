package oldschoolproject.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationManager {
	
	private static FileConfiguration fc = ConfigurationFile.getConfig();
	
	public static Location getSpawnLocation() {
		if (fc.get("spawn") == null) {
			return new Location(Bukkit.getWorld("world"), 0, 0, 0);
		}
		
		return new Location(Bukkit.getWorld("world"), fc.getDouble("spawn.X"), fc.getDouble("spawn.Y"),fc.getDouble("spawn.Z"));
	}

}
