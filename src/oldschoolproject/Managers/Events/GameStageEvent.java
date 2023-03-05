package oldschoolproject.Managers.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import oldschoolproject.Managers.Game.GameManager;
import oldschoolproject.Managers.Game.GameStage;
import oldschoolproject.Managers.Game.GameType;

public class GameStageEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	GameManager gameManager;
	
	public GameStageEvent(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	public GameManager getGameManager() {
		return this.gameManager;
	}
	
	public HandlerList getHandlers() {
		return handlers;
	}

}
