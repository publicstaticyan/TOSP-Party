package oldschoolproject.managers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;
import oldschoolproject.lobby.Lobby;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignManager extends BaseListener {
	
	// TODO: Refactor this code
	
	// TODO: 
	
	/*
	 * lobby-key:
	 * 	signs:
	 * 		x#y#z: x y z
	 */
	
	public SignManager() {
		loadSigns();
	}
	
	public static Sign newSign(Sign sign, String gameId) {
		if (LobbyManager.lobbyExists(gameId)) {
			
			Lobby lobby = LobbyManager.getLobby(gameId);
			
			lobby.addSign(sign);
			
			String key = sign.getLocation().getBlockX() + "#" + sign.getLocation().getBlockY() + "#" + sign.getLocation().getBlockZ();
			
			SettingsManager.load("signs").set(key, gameId);
			
			return sign;
		}
		
		return null;
	}
	
	public static void destroyAll(Lobby l) {
		for (Sign s : l.getSigns()) {
			SettingsManager.load("signs").set(s.getLocation().getBlockX() + "#" + s.getLocation().getBlockY() + "#" + s.getLocation().getBlockZ(), null);
			LobbyManager.lobbyList.forEach(lobby -> { lobby.getSigns().remove(s); });
		}
	}
	
	public static void unbindFromLobby(Sign s) {
		SettingsManager.load("signs").set(s.getLocation().getBlockX() + "#" + s.getLocation().getBlockY() + "#" + s.getLocation().getBlockZ(), null);
		LobbyManager.lobbyList.forEach(lobby -> { lobby.getSigns().remove(s); });
	}
	
	// TODO: Refactor this
	public static boolean isBoundToLobby(Sign s) {
		return LobbyManager.lobbyList.stream().map(Lobby::getSigns).anyMatch(signs -> signs.contains(s));
//		for (Lobby lobby : LobbyManager.lobbyList) {
//			if (lobby.getSigns().contains(s)) {
//				return true;
//			}
//		}
	}
	
	public void loadSigns() {
		// Signs ID's are their location in the world
		
		// The code should look for the key, check if the lobby exists,
		// If it does, then generate the sign at the location
		
		File file = new File(Main.getInstance().getDataFolder(), "signs.yml");
		
		if (file.exists()) {
			
			int i = 0;
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			for (String signId : config.getKeys(false)) {
				
				if (!LobbyManager.lobbyExists(config.getString(signId))) {
					
					continue;
				}
					
				String[] parse = new String[3];
				parse = signId.split("#");
				
				double x = Double.parseDouble(parse[0]);
				double y = Double.parseDouble(parse[1]);
				double z = Double.parseDouble(parse[2]);
				
				Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
				
				if (!(loc.getBlock().getState() instanceof Sign)) {
					Main.getInstance().getLogger().warning("[SignManager] Could not load sign \"" + config.getString(signId) + "\" at X(" + loc.getX() + ") Y(" + loc.getY() + ") Z(" + loc.getZ() + ")"); 
					continue;
				}
				
				Sign sign = (Sign) loc.getBlock().getState();
				
				newSign(sign, config.getString(signId));
				
				i++;
			}
			
			Main.getInstance().getLogger().info("[SignManager] " + i + " signs loaded");
		}
	}
}
