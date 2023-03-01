package oldschoolproject.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import oldschoolproject.Modules.Loaders.Listener.BaseListener;

public class Quit extends BaseListener {
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

}
