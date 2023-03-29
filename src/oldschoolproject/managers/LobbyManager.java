package oldschoolproject.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.managers.game.Lobby;
import oldschoolproject.managers.game.Minigame;
import oldschoolproject.minigames.CTF;

public class LobbyManager {
	
	public static List<Lobby> lobbyList = new ArrayList<>();
	
	public LobbyManager() {
		loadLobbys();
	}
	
	// TODO: These methods should be a forEach, but for now this will serve its pourpouse
	// And they also should be one, like, i can create a action method that has a Action Enum
	public static boolean join(Player p, String gameId) {
		Lobby lobby = lobbyExists(gameId);
		
		if (lobby != null) {
			if (isPlaying(p) == null) {
				lobby.join(p);
				return true;
			}
		}
		return false;
	}
	
	public static boolean quit(Player p) {
		Lobby lobby = isPlaying(p);
		
		if (lobby != null) {
			lobby.quit(p);
			return true;
		}
		
		return false;
 	}
	
	// TODO: The methods "isPlaing" and "lobbyExists" should be one, but for now this is the way
	public static Lobby isPlaying(Player p) {
		return lobbyList.stream().filter(lobby -> lobby.getPlayerList().contains(p)).findFirst().orElse(null);
		
//		for (Lobby lobby : lobbyList) {
//			if (lobby.getPlayerList().contains(p)) {
//				return lobby;
//			}
//		}
//		return null;
	}
	
	public static Lobby lobbyExists(String gameId) {
		return lobbyList.stream().filter(lobby -> lobby.getGame_id().equals(gameId)).findFirst().orElse(null);
		
//		for (Lobby lobby : lobbyList) { 
//			if (lobby.getGame_id().equals(gameId)) {
//				return lobby;
//			}
//		}
//		return null;
	}
	
	public static Lobby createLobby(Minigame minigame, String gameId) {
		Lobby lobby = null;
		
		switch (minigame) {
		
		case Capture_The_Flag:
			lobby = new CTF(gameId);
			break; 
		}
		
		lobbyList.add(lobby);
		
		return lobby;
	}
	
	public void loadLobbys() {
		for (Minigame minigame : Minigame.values()) {
			File file = new File(Main.getInstance().getDataFolder(), minigame.getTag().toLowerCase() + ".yml");
			
			// If the file exists means that at least 1 lobby of the minigame exists
			// The file itself is created whenever a new lobby is instanced
			if (file.exists()) {
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				
				for (String key : config.getKeys(false)) {
					
					createLobby(minigame, key);
				}
			}
		}
	}
}
