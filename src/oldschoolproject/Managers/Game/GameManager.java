package oldschoolproject.Managers.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import oldschoolproject.Managers.Events.GameStageEvent;

public class GameManager {
	
	private GameType gameType;
	private GameStage gameStage;
	private GameTimer gameTimer;
	
	private int minPlayers;
	private int maxPlayers;

	private int timeLimit;

	private List<Player> playersList;
	private List<Player> spectatorsList;
	
	private boolean paused;
	
	public GameManager(GameType gameType, int minPlayers, int maxPlayers, int timeLimit) {
		this.gameType = gameType;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.timeLimit = timeLimit;
		
		this.setGameStage(GameStage.WAITING);
		
		this.playersList = new ArrayList<>();
		this.spectatorsList = new ArrayList<>();
		
		this.gameTimer = new GameTimer(this, timeLimit);
		
		this.paused = false;
		
		this.gameTimer.startLobbyCoundown();
	}
	
	public void addPlayer(Player p) {
		if (getPlayersQuantity() >= getMaxPlayers()) {
			p.sendMessage("§cNão foi possivel juntar-se, este lobby já está lotado!");
			return;
		}
		
		if (getGameStage() == GameStage.PLAYING) {
			p.sendMessage("§cNão foi possivel juntar-se, este lobby já começou!");
			return;
		}
		
		// TODO
		// teleport to game lobby location
		// show only players in the same lobby
		// use same lobby world
		
		playersList.add(p);
	}
	
	public void removePlayer(Player p) {
		if (playersList.contains(p)) {
			playersList.remove(p);
		}
	}
	
	public void addSpectator(Player p) {
		if (getGameStage() == GameStage.PLAYING) {
			spectatorsList.add(p);
		}
	}
	
	public void removeSpectator(Player p) {
		if (spectatorsList.contains(p)) {
			spectatorsList.remove(p);
		}
	}
	
	public GameType getGameType() {
		return gameType;
	}

	public GameStage getGameStage() {
		return gameStage;
	}

	public void setGameStage(GameStage gameStage) {
		this.gameStage = gameStage;
		Bukkit.getServer().getPluginManager().callEvent(new GameStageEvent(this));
	}
	
	public List<Player> getPlayersList() {
		return this.playersList;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public int getPlayersQuantity() {
		return playersList.size();
	}
}
