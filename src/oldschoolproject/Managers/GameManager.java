package oldschoolproject.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class GameManager {
	
	private static GameType currentGame = GameType.NONE;
	private static List<Player> serverPlayers = new ArrayList<>();
	
	public static GameType getCurrentGame() {
		return currentGame;
	}
	
	public static boolean isPlaying() {
		return getCurrentGame() != GameType.NONE;
	}
	
	public static void setGame(GameType game) {
		currentGame = game;
		
		switch (currentGame) {
		
		case SABOTAGE:
			
			break;
			
		case HOTPOTATO:
			
			break;
			
		case NONE:
			
			break;
		}
	}
	
	public static void addPlayer(Player player) {
		serverPlayers.add(player);
	}
	
	public static void removePlayer(Player player) {
		serverPlayers.remove(player);
	}
	
	public static List<Player> getPlayers() {
		return serverPlayers;
	}
}
