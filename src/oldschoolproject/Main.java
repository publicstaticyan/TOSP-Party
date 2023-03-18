package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Managers.ConfigurationFile;
import oldschoolproject.Minigames.CTF;
import oldschoolproject.Modules.AutoReloader;
import oldschoolproject.Modules.Loaders.RegistrationLoader;

public class Main extends JavaPlugin {
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		new RegistrationLoader();
		
		new AutoReloader();
		
		new ConfigurationFile("locations.yml").load();

//		new CTF();
		
		getLogger().info("[Main] Plugin loaded");
		
	}
}