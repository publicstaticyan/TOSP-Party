package oldschoolproject.managers.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import oldschoolproject.managers.game.Game;

public class GameStageEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	Game gameManager;
	
	public GameStageEvent(Game gameManager) {
		this.gameManager = gameManager;
	}
	
	public Game getGameManager() {
		return this.gameManager;
	}
	
	public HandlerList getHandlers() {
		return handlers;
	}

}
