package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import oldschoolproject.Scoreboard.SimpleScoreboard;
import oldschoolproject.Utils.AutoReloader;
import oldschoolproject.Utils.Loaders.CommandLoader;
import oldschoolproject.Utils.Loaders.ListenerLoader;

public class Main extends JavaPlugin {
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		ListenerLoader.loadListenersAndRegister();
		CommandLoader.loadCommandsAndRegister();
		
		new AutoReloader();
		
		new SimpleScoreboard("§e§lOLD §6§lSCHOOL");
		
		getLogger().info("[Main] Plugin carregado");
		

	}
}