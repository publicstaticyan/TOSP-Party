package oldschoolproject;

import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Modules.AutoReloader;
import oldschoolproject.Modules.Loaders.AutoRegister;

public class Main extends JavaPlugin {
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		new AutoRegister();
		
		new AutoReloader();
		
		getLogger().info("[Main] Plugin loaded");
		

	}
}