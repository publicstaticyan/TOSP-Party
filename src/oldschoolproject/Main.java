package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Commands.Builder.CommandFramework;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Managers.GameType;

public class Main extends JavaPlugin {
	
	CommandFramework cm;
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		cm = new CommandFramework(this);
		cm.registerCommands(this);
		
		Bukkit.getConsoleSender().sendMessage("[OldSchool] Plugin loaded");
	}
}
