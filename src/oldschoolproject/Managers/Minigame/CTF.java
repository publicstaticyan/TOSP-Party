package oldschoolproject.Managers.Minigame;

import org.bukkit.entity.Player;

import oldschoolproject.Managers.Game.GameManager;
import oldschoolproject.Managers.Game.GameType;

public class CTF {
	
	static GameManager gm = new GameManager(GameType.CAPTURE_THE_FLAG, 15, 50, 120, 15, 600);
	
	public static void joinGame(Player p) {
		gm.addPlayer(p);
	}
	
	public static void quitGame(Player p) {
		gm.removePlayer(p);
	}

}
