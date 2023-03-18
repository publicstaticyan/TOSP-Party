package oldschoolproject.Managers.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import oldschoolproject.Main;
import oldschoolproject.Managers.Game.GameManager;
import oldschoolproject.Managers.Game.GameStage;
import oldschoolproject.Managers.Game.GameType;
import oldschoolproject.Modules.Loaders.Listener.BaseListener;

public class GameListener extends BaseListener {
	
	@EventHandler
	public void gameStageListener(GameStageEvent event) {
		GameManager gameManager = event.getGameManager();
		
//		if (gameManager.getGameType() == GameType.CAPTURE_THE_FLAG) {
//			if (gameManager.getGameStage() == GameStage.BEGGINING) {
//				if (Main.getInstance().getConfig().get("locations.cft.lobby") == null) {
//					return;
//				}
//				
//				for (Player players : gameManager.getPlayersList()) {
//					players.teleport(new Location(Bukkit.getWorld("world"),
//						Main.getInstance().getConfig().getInt("locations.cft.lobby.x"),
//						Main.getInstance().getConfig().getInt("locations.cft.lobby.y"),
//						Main.getInstance().getConfig().getInt("locations.cft.lobby.z")
//					));
//				}
//			}
//		}
	}
}
