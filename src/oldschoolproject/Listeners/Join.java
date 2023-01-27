package oldschoolproject.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;

import oldschoolproject.Main;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Utils.Methods;

public class Join implements Listener {
	
	public Join() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void preJoin(AsyncPlayerPreLoginEvent e) {
		if (GameManager.isPlaying()) {
			e.disallow(Result.KICK_BANNED, "§cEstamos jogando, aguarde o jogo acabar e tente novamente!");
		}
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		
		Player p = e.getPlayer();
	
		p.getInventory().clear();
		p.setHealth(20D);
		p.setFireTicks(0);
		p.setExhaustion(0);
		p.setExp(0);
		p.setFoodLevel(10);
		p.setLevel(0);
		
//		p.setGameMode(GameMode.ADVENTURE);
		
		GameManager.addPlayer(p);
		
		p.getInventory().setItem(4, Methods.ci(Material.CHEST, 1, "§6Votação"));
		
	}
}
