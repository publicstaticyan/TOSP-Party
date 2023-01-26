package oldschoolproject.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class GameManager {
	
	private static GameType currentGame = GameType.NONE;
	private static List<Player> players = new ArrayList<>();
	
	public static GameType getCurrentGame() {
		return currentGame;
	}
	
	public static boolean isPlaying() {
		return getCurrentGame() != GameType.NONE;
	}
	
	public static void setCurrentGame(GameType game) {
		currentGame = game;
		
		switch (currentGame) {
		
		case SABOTAGE:
			
			break;
			
		case HG:
					
			break;
			
		case PARTY:
			
			break;
			
		case NONE:
			
			break;
		}
	}
	
	public static void addPlayer(Player player) {
		players.add(player);
	}
	
	public static void removePlayer(Player player) {
		players.remove(player);
	}
	
	public static List<Player> getPlayers() {
		return players;
	}
}
