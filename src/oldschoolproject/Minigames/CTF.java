package oldschoolproject.Minigames;

import org.bukkit.entity.Player;

import oldschoolproject.Managers.Game.GameManager;
import oldschoolproject.Managers.Game.GameType;
import oldschoolproject.Modules.Loaders.Listener.BaseListener;

public class CTF extends BaseListener {
	
	/*
	 * 
	 * TODO
	 * @Custom Event
	 * The server is always listening to it
	 * The event tracks the gm's GameStage
	 * when the game's stage hits beggining it triggers the event
	 * and the event teleports all players to the corresponding game arena
	 * 
	 */
	
	static GameManager gm;

	public CTF() {
		gm = new GameManager(GameType.CAPTURE_THE_FLAG, 4, 50, 600);
	}
	
	public static void joinGame(Player p) {
		gm.addPlayer(p);
	}
	
	public static void quitGame(Player p) {
		gm.removePlayer(p);
	}
	
	public static void joinGameSpectator(Player p) {
		gm.addSpectator(p);
	}
	
	public static void quitGameSpectator(Player p) {
		gm.removeSpectator(p);
	}
}
