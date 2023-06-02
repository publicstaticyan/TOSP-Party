package oldschoolproject.managers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import oldschoolproject.Main;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignManager extends BaseListener {

	public SignManager() {
		loadSigns();
	}
	
	// Um lobby é apagado
	public static void erase(Location s) {
		SettingsManager.load("signs").set(s.getBlockX() + "#" + s.getBlockY() + "#" + s.getBlockZ(), null);
	}
	
	// Um player destroi a placa
	public static void destroy(Location s) {
		LobbyManager.getLobby(((Sign) s.getBlock().getState()).getLine(1).toLowerCase()).removeSign(s);
		
		erase(s);
	}
	
	// O servidor carrega
	public static void link(Location s, String gameId) {
		LobbyManager.getLobby(gameId).addSign(s);
	}
	
	// Um player cria uma placa
	public static void create(Location s, String gameId) {
		link(s, gameId);
		
		SettingsManager.load("signs").set(s.getBlockX() + "#" + s.getBlockY() + "#" + s.getBlockZ(), gameId);
	}
	
	public static boolean isLinkedToLobby(Location s) {
		return LobbyManager.getLobby(((Sign) s.getBlock().getState()).getLine(1).toLowerCase()).getSigns().contains(s);
		
//		return LobbyManager.lobbyList.stream().map(Lobby::getSigns).anyMatch(signs -> signs.contains(s));
	}
	
	public void loadSigns() {
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
				
				link(loc, config.getString(signId));
				
				i++;
				
				}
			}
			
			Main.getInstance().getLogger().info("[SignManager] " + i + " signs loaded");
		}
	}
}
