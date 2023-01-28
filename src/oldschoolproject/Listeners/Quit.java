package oldschoolproject.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import oldschoolproject.Main;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Utils.Base.BaseListener;

public class Quit extends BaseListener {
	
	public Quit() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		
		Player p = e.getPlayer();
		
		GameManager.removePlayer(p);
	}

}
