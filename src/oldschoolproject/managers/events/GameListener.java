package oldschoolproject.managers.events;

import org.bukkit.event.EventHandler;

import oldschoolproject.utils.loaders.listener.BaseListener;

public class GameListener extends BaseListener {
	
	@EventHandler
	public void gameStageListener(GameStageEvent event) {
//		Game gameManager = event.getGameManager();
		
		// This code is wrong, it must dinamically send all players to corresponding arena
		
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
