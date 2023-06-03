package oldschoolproject.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.exceptions.LobbyOperationException;
import oldschoolproject.lobby.Lobby;
import oldschoolproject.lobby.Minigame;

public class LobbyManager {
	
	public static List<Lobby> lobbyList = new ArrayList<>();
	
	public LobbyManager() {
		try {
			loadLobbys();
		} catch (LobbyOperationException e) { e.printStackTrace(); }
	}
	
	public static Lobby link(Minigame minigame, String gameId) throws LobbyOperationException {
		Lobby lobby = minigame.instanceNewLobby(gameId);
		
		lobbyList.add(lobby);
		
		updateLobby(gameId, "playermin", "1");
		updateLobby(gameId, "playermax", "1");
		updateLobby(gameId, "timelimit", "60");
		
		return lobby;
	}
	
	public static Lobby createLobby(Minigame minigame, String gameId) throws LobbyOperationException {
		if (minigame == null) {
			throw new LobbyOperationException("O minigame informado não existe");
		} 
		
		if (lobbyExists(gameId)) {
			throw new LobbyOperationException("O id informado já foi utilizado");
		}
		
		Lobby lobby = link(minigame, gameId);
		
		return lobby;
	}
	
	public static void updateLobby(String gameId, String field, String value) throws LobbyOperationException {
		if (!lobbyExists(gameId)) {
			throw new LobbyOperationException("O lobby não existe");
		}
		
		if (!value.matches("[0-9]+")) {
			throw new LobbyOperationException("§cO campo \"value\" deve ser um número inteiro");
		}
		
		Lobby lobby = getLobby(gameId);
		SettingsManager sm = SettingsManager.load(lobby.getLowerCaseTag());
		
		switch (field) {
		case "playermin": {
			lobby.setMinPlayers(Integer.valueOf(value));
			sm.set(lobby.getId() + ".minPlayers", Integer.valueOf(value));
			break;
		}
		case "playermax": {
			lobby.setMaxPlayers(Integer.valueOf(value));
			sm.set(lobby.getId() + ".maxPlayers", Integer.valueOf(value));
			break;
		}
		case "timelimit": {
			lobby.setTimeLimit(Integer.valueOf(value));
			sm.set(lobby.getId() + ".timeLimit", Integer.valueOf(value));
			break;
		}
		default:
			throw new LobbyOperationException("§cO campo \"field\" deve ser um valor predefinido");
		}
	}
	
	public static void deleteLobby(String gameId) throws LobbyOperationException {
		if (!lobbyExists(gameId)) {
			throw new LobbyOperationException("O lobby não existe");
		}
		
		Lobby lobby = getLobby(gameId);
		
		SettingsManager sm = SettingsManager.load(lobby.getLowerCaseTag());
		
		sm.set(lobby.getId(), null);
		
		lobby.getSigns().forEach(loc -> {
			SignManager.erase(loc);
			
			Sign sign = (Sign) loc.getBlock().getState();
			
			sign.setLine(0, "");
			sign.setLine(1, "§4§lSign Erased");
			sign.setLine(2, "§c§l" + ((Sign) sign.getBlock().getState()).getLine(1));
			sign.setLine(3, "");
			sign.update(true);
		});
		
		lobbyList.remove(lobby);
	}
	
	public static Lobby getLobby(String gameId) {
		return lobbyList.stream().filter(lobby -> lobby.getId().equals(gameId)).findFirst().orElse(null);
	}
	
	public static boolean lobbyExists(String gameId) {
		return getLobby(gameId) != null;
	}
	
	public static boolean lobbyIsFull(String gameId) {
		return getLobby(gameId).getPlayerList().size() == getLobby(gameId).getMaxPlayers();
	}
	
	public static boolean isPlayerPlaying(Player p) {
		return getPlayerLobby(p) != null;
	}
	
	public static Lobby getPlayerLobby(Player p) {
		return lobbyList.stream().filter(lobby -> lobby.getPlayerList().contains(p)).findFirst().orElse(null);
	}
	
	public static void listTags(Player p) {
		p.sendMessage("");
		p.sendMessage("§eLOBBY TAGS: ");
		p.sendMessage("");
		
		Arrays.asList(Minigame.values()).forEach(minigame -> p.sendMessage("§6- " + minigame.getTag()));
	}
	
	public static void listLobbys(Player p) {
		p.sendMessage("");
		p.sendMessage("§eLOBBY LIST: ");
		p.sendMessage("");
		p.sendMessage("§7| §cMINIGAME §7| §cID §7| §cSTATUS §7| §cPLAYERS §7| §cTEMPO §7|");
		p.sendMessage("");
		 
		lobbyList.forEach(lobby -> {
			p.sendMessage("§a" + lobby.getMinigame().getTag() + " §7- §a" + lobby.getId() + " §7- §a" + lobby.getStage().toString() + " §7- §a" + lobby.getPlayerList().size() + "§a/" + lobby.getMaxPlayers() + " §7: §a" + lobby.getMinPlayers() + " §7- §a" + lobby.getTimer().getTime());
		});
	}
	
	public static void addLocationToLobby(String gameId, String keyName, Location loc) throws LobbyOperationException {
		if (!lobbyExists(gameId)) {
			throw new LobbyOperationException("O lobby não existe");
		}
		
		Lobby lobby = getLobby(gameId);
		SettingsManager sm = SettingsManager.load(lobby.getLowerCaseTag());
		
		sm.set(lobby.getId() + ".locations." + keyName + ".x", loc.getX());
		sm.set(lobby.getId() + ".locations." + keyName + ".y", loc.getY());
		sm.set(lobby.getId() + ".locations." + keyName + ".z", loc.getZ());
	}
	
	public static void join(Player p, String gameId) throws LobbyOperationException {
		if (!lobbyExists(gameId)) {
			throw new LobbyOperationException("O lobby não existe");
		}
		
		if (isPlayerPlaying(p)) {
			throw new LobbyOperationException("Você já está em um lobby");
		}
		
		if (lobbyIsFull(gameId)) {
			throw new LobbyOperationException("O lobby está lotado");
		}
		
		getLobby(gameId).join(p);
	}
	
	public static void quit(Player p) throws LobbyOperationException {
		if (!isPlayerPlaying(p)) {
			throw new LobbyOperationException("Você não está em um lobby");
		}
		
		getPlayerLobby(p).quit(p);
 	}

	public void loadLobbys() throws LobbyOperationException {
		int i = 0;
		
		for (Minigame minigame : Minigame.values()) {
			
			File file = new File(Main.getInstance().getDataFolder(), minigame.getTag().toLowerCase() + ".yml");
			
			if (file.exists()) {
				
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				
				for (String key : config.getKeys(false)) {
					
					link(minigame, key);
					
					i++;
				}
			}
		}
		Main.getInstance().getLogger().info("[LobbyManager] " + i + " lobbys loaded");
	}
}
