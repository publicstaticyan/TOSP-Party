package oldschoolproject.managers;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;
import oldschoolproject.lobby.Lobby;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignManager extends BaseListener {
	
	/*
	 * lobby-key:
	 * 	signs:
	 * 		x#y#z: x y z
	 */
	
	public SignManager() {
		loadSigns();
	}
	
	public static void registerSign(Location loc, String gameId) {
		Lobby lobby = LobbyManager.getLobby(gameId);
		
		lobby.addSign(loc);
	}
	
	public static void saveSign(Location loc, String gameId) {
			registerSign(loc, gameId);
			
			String key = loc.getBlockX() + "#" + loc.getBlockY() + "#" + loc.getBlockZ();
			
			SettingsManager.load("signs").set(key, gameId);
	}
	
	public static void unlinkAndDestroyAll(Lobby lobby) {
		lobby.getSigns().forEach(sign -> {
			destroy(sign);
		});
		
		lobby.getSigns().clear();
	}
	
	public static void unlink(Location s) {
		// Maybe update the sign to display : "Unlinked" ?
		
		LobbyManager.lobbyList.forEach(lobby -> {
			lobby.getSigns().remove(s);
		});
	}
	
	public static void destroy(Location s) {
		SettingsManager.load("signs").set(s.getBlockX() + "#" + s.getBlockY() + "#" + s.getBlockZ(), null);
	}
	
	public static void unlinkAndDestroy(Location s) {
		unlink(s); destroy(s);
	}
	
	public static boolean isLinkedToLobby(Location s) {
		return LobbyManager.lobbyList.stream().map(Lobby::getSigns).anyMatch(signs -> signs.contains(s));
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
				
				if (LobbyManager.lobbyExists(config.getString(signId))) {
					
				String[] coords = new String[3]; // x#y#z -> [x, y, z]
				coords = signId.split("#");
				
				double x = Double.parseDouble(coords[0]);
				double y = Double.parseDouble(coords[1]);
				double z = Double.parseDouble(coords[2]);
				
				Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
				
				if (!(loc.getBlock().getState() instanceof Sign)) {
					Main.getInstance().getLogger().warning("[SignManager] Could not load sign \"" + config.getString(signId) + "\" at X(" + loc.getX() + ") Y(" + loc.getY() + ") Z(" + loc.getZ() + ")"); 
					continue;
				}
				
				registerSign(loc, config.getString(signId));
				
				i++;
				
				}
			}
			
			Main.getInstance().getLogger().info("[SignManager] " + i + " signs loaded");
		}
	}
}
