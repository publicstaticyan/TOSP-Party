package oldschoolproject.managers.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import oldschoolproject.managers.SettingsManager;

public abstract class Lobby {
	
	private String game_id;
	private Stage stage;
	private Minigame minigame;
	private List<Player> playerList;
	private List<Sign> entrySigns;
	
	public Lobby(Minigame minigame, String game_id) {
		this.game_id = game_id.toLowerCase();
		this.minigame = minigame;
		this.playerList = new ArrayList<>();
		this.entrySigns = new ArrayList<>();
		
		setStage(Stage.WAITING);
		
		new Timer(this, 10);
		
		SettingsManager sm = SettingsManager.load(lowerCaseTag());
		
		sm.set(getGame_id(), "");
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
	
	public String getGame_id() {
		return game_id;
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
	
	public abstract void start();
	
}
