package oldschoolproject;

import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.utils.AutoReloader;
import oldschoolproject.utils.loaders.RegistrationLoader;

public class Main extends JavaPlugin {
	
	/*
	 * GameSigns:
	 * 
	 * As for a first installment I'll handle signs directly from code and not dinamically
	 * But, a good option would be to manually create the game instances and corresponding signs
	 *	
	 */
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		new RegistrationLoader();
		
		new AutoReloader();
		
//		new SettingsManager("locations.yml").load();
		
		getLogger().info("[Main] Plugin loaded");
		
	}
}