package oldschoolproject;

import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Modules.AutoReloader;
import oldschoolproject.Modules.Loaders.RegistrationLoader;

public class Main extends JavaPlugin {
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		new RegistrationLoader();
		
		new AutoReloader();
		
		getLogger().info("[Main] Plugin loaded");
		
		
	}
}