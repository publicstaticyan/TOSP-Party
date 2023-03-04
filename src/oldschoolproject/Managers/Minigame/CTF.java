package oldschoolproject.Managers.Minigame;

import org.bukkit.entity.Player;

import oldschoolproject.Managers.Game.GameManager;
import oldschoolproject.Managers.Game.GameType;

public class CTF {
	
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
