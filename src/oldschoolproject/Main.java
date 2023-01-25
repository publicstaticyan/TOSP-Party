package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("[Sabotage] Plugin loaded");
	}
}
