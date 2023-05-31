package oldschoolproject.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import oldschoolproject.utils.loaders.listener.BaseListener;

public class LQuit extends BaseListener {
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

}
