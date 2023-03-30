package oldschoolproject.game;

import java.util.ArrayList;
import java.util.List;

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
		
		new Timer(this, 10);
		
		this.sm = SettingsManager.load(lowerCaseTag());
		
		this.setMinPlayers(1);
		this.setMaxPlayers(1);
		this.setTimeLimit(60);
	}
	
	public void addSign(Sign entrySign) {
		this.entrySigns.add(entrySign);
	}
	
	public List<Sign> getSigns() {
		return this.entrySigns;
	}
	
	public String lowerCaseTag() {
		return getMinigame().getTag().toLowerCase();
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
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public void quit(Player p) {
		playerList.remove(p);
		this.entrySigns.forEach(sign -> {sign.update();});
	}
	
	public void join(Player p) {
		playerList.add(p);
		this.entrySigns.forEach(sign -> {sign.update();});
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public int getMinPlayers() {
		return this.minPlayers;
	}
	
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		sm.set(getId() + ".maxPlayers", maxPlayers);
	}
	
	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
		sm.set(getId() + ".minPlayers", minPlayers);
	}
	
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
		sm.set(getId() + ".timeLimit", timeLimit);
	}
	
	public void eraseConfig() {
		sm.set(getId(), null);
	}
	
	public abstract void start();
	
}
