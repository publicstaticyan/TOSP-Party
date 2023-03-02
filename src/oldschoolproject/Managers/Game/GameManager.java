package oldschoolproject.Managers.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import oldschoolproject.Main;

public class GameManager {
	
	private GameType gameType;
	private GameStage gameStage;
	
	private int minPlayers;
	private int maxPlayers;

	private int countdownTime;
	private int currentTime;
	private int timeLimit;
	private int lobbyWaitingTime;

	private List<Player> playersList;
	private List<Player> spectatorsList;
	
	private boolean paused;
	
	private BukkitTask waitingTask;
	private BukkitTask playingTask;
	
	public GameManager(GameType gameType, int minPlayers, int maxPlayers, int lobbyWaitingTime, int countdownTime, int timeLimit) {
		this.gameType = gameType;
		this.setMinPlayers(minPlayers);
		this.maxPlayers = maxPlayers;
		this.countdownTime = countdownTime;
		this.timeLimit = timeLimit;
		this.lobbyWaitingTime = lobbyWaitingTime;
		
		this.setGameStage(GameStage.WAITING);
		this.setCurrentTime(0);
		this.playersList = new ArrayList<>();
		this.spectatorsList = new ArrayList<>();
		this.paused = false;
		
		this.startTickingLobby();
	}
	
	private void startTickingGame() {
		playingTask = new BukkitRunnable() {
			
			int tickingLimitTimer = getTimeLimit();
			
			public void run() {
				setCurrentTime(getCurrentTime() + 1);
				
				tickingLimitTimer--;
				
				switch (tickingLimitTimer) {
				case 59:
					broadcastMessage(getGameLogo(ChatColor.DARK_RED, ChatColor.RED) + " §7O tempo vai se esgotar! Terminem o jogo antes que seja tarde!");
					break;
				case 30:
					broadcastMessage(getGameLogo(ChatColor.DARK_RED, ChatColor.RED) + " §cO tempo vai se esgotar! Terminem o jogo antes que seja tarde!");
					break;
				case 10:
					broadcastMessage(getGameLogo(ChatColor.DARK_RED, ChatColor.RED) + " §4O tempo vai se esgotar! Terminem o jogo antes que seja tarde!");
					break;
				case 1:
					broadcastMessage(getGameLogo(ChatColor.DARK_RED, ChatColor.RED) + " §4TEMPO ESGOTADO!");
					break;
				case 0:
					cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20);
	}
	
	private void startTickingLobby() {
		waitingTask = new BukkitRunnable() {
			
			int tickingWaitingTimer = getLobbyWaitingTime();
			
			public void run() {
				tickingWaitingTimer--;
				
				switch (tickingWaitingTimer) {
				case 179:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §f3 minutos!");
					break;
				case 119:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §e2 minutos!");
					break;
				case 59:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §a1 minutos!");
					break;
				case 29:
					if (getPlayerQuantity() < getMinPlayers()) {
						broadcastMessage(getGameLogo(ChatColor.DARK_RED, ChatColor.RED) + " §7Pooxa... que pena! Não haviam jogadores suficientes e o contador reiniciou!");
						tickingWaitingTimer = getLobbyWaitingTime() + 5;
					}
					break;
				case 9:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §e10 segundos!");
					break;
				case 4:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §a5...");
					break;
				case 3:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §a4...");
					break;
				case 2:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §a3...");
					break;
				case 1:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §a2...");
					break;
				case 0:
					broadcastMessage(getGameLogo(ChatColor.GOLD, ChatColor.YELLOW) + " §7A festa vai começar em §a1...");
					break;
				case -1:
					broadcastMessage(getGameLogo(ChatColor.DARK_GREEN, ChatColor.GREEN) + " §aA festa começou! Bom jogo a todos!");
					startTickingGame();
					cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}
	
	public GameType getGameType() {
		return gameType;
	}

	public GameStage getGameStage() {
		return gameStage;
	}

	public void setGameStage(GameStage gameStage) {
		this.gameStage = gameStage;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getCountdownTime() {
		return countdownTime;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
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
	
	public void addPlayer(Player p) {
		playersList.add(p);
	}
	
	public void removePlayer(Player p) {
		playersList.remove(p);
	}

	public List<Player> getPlayersList() {
		return playersList;
	}
	
	public void addSpectator(Player p) {
		spectatorsList.add(p);
	}
	
	public void removeSpectator(Player p) {
		spectatorsList.remove(p);
	}

	public List<Player> getSpectatorsList() {
		return spectatorsList;
	}

	private int getLobbyWaitingTime() {
		return lobbyWaitingTime;
	}
	
	public int getPlayerQuantity() {
		return getPlayersList().size();
	}

	private void broadcastMessage(String message) {
		for (Player players : getPlayersList()) {
			players.sendMessage(message);
		}
	}
	
	// Inconsistente
	
	private String getGameLogo(ChatColor logoColor, ChatColor bracketsColor) {
		switch (this.getGameType()) {
		
		case CAPTURE_THE_FLAG:
			return bracketsColor + "[" + logoColor + "Capture the Flag" + bracketsColor + "]";
			
		case HOT_POTATO:
			
			return bracketsColor + "[" + logoColor + "Hot Potato" + bracketsColor + "]";
		}
		
		return bracketsColor + "[" + logoColor + "Lobby" + bracketsColor + "]";
	}

	public BukkitTask getPlayingTask() {
		return playingTask;
	}

	public BukkitTask getWaitingTask() {
		return waitingTask;
	}
}
