package oldschoolproject;

import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.managers.LobbyManager;
import oldschoolproject.managers.SignManager;
import oldschoolproject.utils.loaders.AutoReloader;
import oldschoolproject.utils.loaders.command.CommandLoader;
import oldschoolproject.utils.loaders.listener.ListenerLoader;

public class Main extends JavaPlugin {
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	// TODO: What if I use ObjectOutputStream and save all the lobbys in a data file? :)
	
	public void onEnable() {
		
		new CommandLoader();
		
		new ListenerLoader();
		
		new AutoReloader();
		
		new LobbyManager();
		
		new SignManager();
		
		getLogger().info("[Main] Plugin loaded");
		
	}
}