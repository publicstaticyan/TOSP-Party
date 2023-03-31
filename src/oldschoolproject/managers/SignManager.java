package oldschoolproject.managers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;
import oldschoolproject.game.Lobby;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignManager extends BaseListener {
	
	// TODO: This code is a little bit weird, I should refactor it
	
	public SignManager() {
		loadSigns();
	}
	
	public static Sign newSign(Sign sign, String gameId) {
		if (LobbyManager.lobbyExists(gameId)) {
			
			Lobby lobby = LobbyManager.getLobby(gameId);
			
			// Lobby min players and max players should be dynamically managed
			// So the max players for now is a placeholder
		
			lobby.generateSign(sign);
			
			String key = sign.getLocation().getBlockX() + "#" + sign.getLocation().getBlockY() + "#" + sign.getLocation().getBlockZ();
			
			SettingsManager.load("signs").set(key, gameId);
			
			lobby.addSign(sign);
			
			return sign;
		}
		
		return null;
	}
	
	public static void unbindFromLobby(Sign s) {
		LobbyManager.lobbyList.forEach(lobby -> {
			lobby.getSigns().remove(s);
		});
	}
	
	// TODO: Refactor this
	public static boolean boundToLobby(Sign s) {
		for (Lobby lobby : LobbyManager.lobbyList) {
			for (Sign sign : lobby.getSigns()) {
				if (sign.getLocation().equals(s.getLocation())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void loadSigns() {
		// The sign key should be the same as the gameId it points to
		// So the signs file should be something like:
		
		/*
		 * sings.yml **
		 * 
		 * <gameId> (signId)
		 * 		x: 0 
		 * 		y: 0 
		 * 		z: 0
		 */
		
		// Then, if I have more than one sign looking for the same gameId, 
		// they will differenciate in location rather than id
		
		// USE CONSOLE LOGS TO VERIFY EVERYTHING
		// The code should look for the key, check if the lobby exists,
		// If it does, then generate the sign at the location
		
		File file = new File(Main.getInstance().getDataFolder(), "signs.yml");
		
		if (file.exists()) {
			
			int i = 0;
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			for (String signId : config.getKeys(false)) {
				
				if (LobbyManager.lobbyExists(config.getString(signId))) {
					
					String[] parse = new String[3];
					parse = signId.split("#");
					
					double x = Double.parseDouble(parse[0]);
					double y = Double.parseDouble(parse[1]);
					double z = Double.parseDouble(parse[2]);
					
					Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
					
					if (!(loc.getBlock().getState() instanceof Sign)) {
						Main.getInstance().getLogger().warning("[SignManager] Could not load sign at X(" + loc.getX() + ") Y(" + loc.getY() + ") Z(" + loc.getZ() + ")"); 
						continue;
					}
					
					// TODO: Ask Hugo
					// Does creating new references consume memory? I'm assuming it doesn't
					// The compiler must replace where the reference is used with it's object property
					// Something like: newSign(loc.getBlock().getState(), config.getString(signId));
					
					// I've done my Google search, but it's like asking the hour and get answered who created the clock
					
					Sign sign = (Sign) loc.getBlock().getState();
					
					newSign(sign, config.getString(signId));
					
					i++;
				}
			}
			
			Main.getInstance().getLogger().info("[SignManager] " + i + " signs loaded");
		}
	}
}
