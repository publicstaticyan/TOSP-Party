package oldschoolproject.lobby;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import oldschoolproject.managers.SettingsManager;

public abstract class Lobby {
	
	private SettingsManager sm;
	private String id;
	
	private Stage stage;
	private Minigame minigame;
	
	private List<Player> playerList;
	private List<Sign> entrySigns;
	
	int minPlayers, maxPlayers, timeLimit;
	
	public Lobby(Minigame minigame, String id) {
		this.id = id.toLowerCase();
		this.minigame = minigame;
		this.playerList = new ArrayList<>();
		this.entrySigns = new ArrayList<>();
		
		this.setStage(Stage.WAITING);
		
		new Timer(this);
		
		this.sm = SettingsManager.load(lowerCaseTag());
		
		this.setMinPlayers(1);
		this.setMaxPlayers(1);
		this.setTimeLimit(60);
	}
	
	public void quit(Player p) {
		playerList.remove(p);
		updateAllSigns();
	}
	
	public void join(Player p) {
		playerList.add(p);
		updateAllSigns();
	}
	
	public String lowerCaseTag() {
		return this.minigame.getTag().toLowerCase();
	}
	
	public void updateSign(Sign sign) {
		sign.setLine(0, "[" + this.minigame.getTag() + "]");
		sign.setLine(1, this.id);
		sign.setLine(2, this.playerList.size() + "/" + this.maxPlayers);
		sign.setLine(3, this.stage.toString());
		sign.update(true);
	}
	
	public void setLocation(String key, Location loc) {
		sm.set(this.id + ".locations." + key + ".x", loc.getX());
		sm.set(this.id + ".locations." + key + ".y", loc.getY());
		sm.set(this.id + ".locations." + key + ".z", loc.getZ());
	}
	
	public void updateAllSigns() {
		this.entrySigns.forEach(sign -> { updateSign(sign); });
	}
	
	public void destroy() {
		sm.set(this.id, null);
	}
	
	public void addSign(Sign entrySign) {
		this.entrySigns.add(entrySign);
		updateSign(entrySign);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		updateAllSigns();
	}
	
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		sm.set(this.id + ".maxPlayers", maxPlayers);
	}
	
	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
		sm.set(this.id + ".minPlayers", minPlayers);
	}
	
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
		sm.set(this.id + ".timeLimit", timeLimit);
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public Minigame getMinigame() {
		return minigame;
	}
	
	public String getId() {
		return this.id;
	}

	public Stage getStage() {
		return stage;
	}
	
	public List<Sign> getSigns() {
		return this.entrySigns;
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public int getMinPlayers() {
		return this.minPlayers;
	}
	
	public abstract void teleport();
	
	public abstract void start();
	
}
