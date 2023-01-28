package oldschoolproject;

import org.bukkit.plugin.java.JavaPlugin;

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

		getLogger().info("[Main] Plugin carregado");
	}
}