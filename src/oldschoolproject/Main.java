package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Managers.GameManager;
import oldschoolproject.Managers.GameType;

public class Main extends JavaPlugin {
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage("[OldSchool] Plugin loaded");
	}
}
