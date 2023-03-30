package oldschoolproject.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.game.Lobby;
import oldschoolproject.game.Minigame;

public class LobbyManager {
	
	public static List<Lobby> lobbyList = new ArrayList<>();
	
	public LobbyManager() {
		loadLobbys();
	}
	
	public void loadLobbys() {
		int i = 0;
		
		for (Minigame minigame : Minigame.values()) {
			
			File file = new File(Main.getInstance().getDataFolder(), minigame.getTag().toLowerCase() + ".yml");
			
			// If the file exists means that at least 1 lobby of the minigame exists
			// The file itself is created whenever a new lobby is instanced
			if (file.exists()) {
				
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				
				for (String key : config.getKeys(false)) {
					
					createLobby(minigame, key);
					
					i++;
				}
			}
		}
		Main.getInstance().getLogger().info("[LobbyManager] " + i + " lobbys loaded");
	}
	
	public static void deleteLobby(String gameId) {
		Lobby lobby = getLobby(gameId);
		lobby.eraseConfig();
		lobbyList.remove(lobby);
	}
	
	public static Lobby createLobby(Minigame minigame, String gameId) {
		Lobby lobby = minigame.instance(gameId);
		lobbyList.add(lobby);
		return lobby;
	}
	
	public static boolean isPlaying(Player p) {
		return getPlayerLobby(p) != null;
	}
	
	public static Lobby getPlayerLobby(Player p) {
		return lobbyList.stream().filter(lobby -> lobby.getPlayerList().contains(p)).findFirst().orElse(null);
	}
	
	public static boolean lobbyExists(String gameId) {
		return getLobby(gameId) != null;
	}
	
	public static Lobby getLobby(String gameId) {
		return lobbyList.stream().filter(lobby -> lobby.getId().equals(gameId)).findFirst().orElse(null);
	}
	
	public static void join(Player p, String gameId) {
		getLobby(gameId).join(p);
	}
	
	public static void quit(Player p) {
		getPlayerLobby(p).quit(p);
 	}
}