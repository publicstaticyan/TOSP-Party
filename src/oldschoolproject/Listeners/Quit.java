package oldschoolproject.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import oldschoolproject.Managers.GameManager;

public class Quit implements Listener {
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		GameManager.removePlayer(p);
	}

}
