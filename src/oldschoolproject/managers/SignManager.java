package oldschoolproject.managers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;
import oldschoolproject.managers.game.Lobby;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignManager extends BaseListener {
	
	// TODO: This code is VERY weird, I should refactor is asap
	
	public SignManager() {
		loadSigns();
	}
	
	public static boolean newSign(Sign sign, String gameId) {
		Lobby lobby = LobbyManager.lobbyExists(gameId);
		
		if (lobby != null) {
			
			// Lobby min players and max players should be dynamically managed
			// So the max players for now is a placeholder
		
			sign.setLine(0, "[" + lobby.getMinigame().getTag() + "]");
			sign.setLine(1, lobby.getGame_id());
			sign.setLine(2, lobby.getPlayerList().size() + "/0");
			sign.setLine(3, lobby.getStage().toString());
			
			sign.update(true);
			
			lobby.addSign(sign);
			
			SettingsManager sm = SettingsManager.load("signs");
			
			String key = sign.getLocation().getBlockX() + "#" + sign.getLocation().getBlockY() + "#" + sign.getLocation().getBlockZ();
			
			sm.set(key, gameId);
			
			return true;
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
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
			for (String signId : config.getKeys(false)) {
				
				Lobby lobby = LobbyManager.lobbyExists(config.getString(signId));
				
				if (lobby != null) {
					
					String[] parse = new String[3];
					parse = signId.split("#");
					
					double x = Double.parseDouble(parse[0]);
					double y = Double.parseDouble(parse[1]);
					double z = Double.parseDouble(parse[2]);
					
					Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
					
					if (!(loc.getBlock().getState() instanceof Sign)) {
						Main.getInstance().getLogger().info("[Signs] Could not load Sign at " + loc.getX() + " " + loc.getY() + " " + loc.getZ()); 
						continue;
					}
					
					Sign sign = (Sign) loc.getBlock().getState();
					
					newSign(sign, config.getString(signId));
				}
			}
		}
	}
}
