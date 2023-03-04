package oldschoolproject.Managers.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import oldschoolproject.Main;
import oldschoolproject.Modules.Builders.MessageBuilder;
import oldschoolproject.Modules.Builders.MessageBuilder.MessageType;

public class GameManager {
	
	private GameType gameType;
	private GameStage gameStage;
	
	private int minPlayers;
	private int maxPlayers;

	private int timeCoutdown;
	private int timeCurrent;
	private int timeLimit;

	private List<Player> playersList;
	private List<Player> spectatorsList;
	
	private MessageBuilder mb;
	
	private boolean paused;
	
	public GameManager(GameType gameType, int minPlayers, int maxPlayers, int timeLimit) {
		this.gameType = gameType;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.timeLimit = timeLimit;
		
		this.setGameStage(GameStage.WAITING);
		this.setCountdownTime(180);
		this.setCurrentTime(0);
		this.playersList = new ArrayList<>();
		this.spectatorsList = new ArrayList<>();
		this.paused = false;
		this.mb = new MessageBuilder(gameType.getDisplayName());
		
		this.startTickingLobby();
	}
	
	private void broadcastMessage(String message) {
		for (Player players : playersList) {
			players.sendMessage(message);
		}
	}
	
	private void startCheckingPlayers() {
		new BukkitRunnable() {
			public void run() {
				if (getPlayersQuantity() < getMinPlayers()) {
					broadcastMessage(mb.setMessage("§cPoxa... Não haviam jogadores suficientes e o contador reiniciou!").setType(MessageType.DANGER).toString());
					resetWaitingTimer();
					setGameStage(GameStage.WAITING);
					cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}
	
	private void resetWaitingTimer() {
		this.setCountdownTime(180 + 5);
	}
	
	private void startTickingGame() {
		setGameStage(GameStage.PLAYING);
		
		new BukkitRunnable() {
			
			int tickingLimitTimer = getTimeLimit();
			
			public void run() {
				setCurrentTime(getCurrentTime() + 1);
				
				if (!isPaused()) {
					tickingLimitTimer--;
				}
				
				switch (tickingLimitTimer) {
				case 59:
					
					broadcastMessage(mb.setMessage("§7O tempo vai se esgotar! Terminem o jogo antes que seja tarde!").setType(MessageType.DANGER).toString());
					break;
				case 30:
					broadcastMessage(mb.setMessage("§cO tempo vai se esgotar! Terminem o jogo antes que seja tarde!").setType(MessageType.DANGER).toString());
					break;
				case 10:
					broadcastMessage(mb.setMessage("§4O tempo vai se esgotar! Terminem o jogo antes que seja tarde!").setType(MessageType.DANGER).toString());
					break;
				case 1:
					broadcastMessage(mb.setMessage("§4TEMPO ESGOTADO!").setType(MessageType.DANGER).toString());
					break;
				case 0:
					cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20);
	}
	
	private void startTickingLobby() {
		new BukkitRunnable() {
			
			int tickingWaitingTimer = getCountdownTime();
			
			public void run() {
				
				if (!isPaused()) {
					tickingWaitingTimer--;
				}
				
				switch (tickingWaitingTimer) {
				case 179:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §f3 minutos!").setType(MessageType.WARNING).toString());
					break;
				case 119:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §f2 minutos!").setType(MessageType.WARNING).toString());
					break;
				case 59:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §f1 minuto!").setType(MessageType.WARNING).toString());
					break;
				case 29:
					startCheckingPlayers();
					break;
				case 9:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §e10 segundos!").setType(MessageType.WARNING).toString());
					setGameStage(GameStage.BEGGINING);
					break;
				case 4:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §a5...").setType(MessageType.WARNING).toString());
					break;
				case 3:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §a4...").setType(MessageType.WARNING).toString());
					break;
				case 2:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §a3...").setType(MessageType.WARNING).toString());
					break;
				case 1:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §a2...").setType(MessageType.WARNING).toString());
					break;
				case 0:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §a1...").setType(MessageType.WARNING).toString());
					break;
				case -1:
					broadcastMessage(mb.setMessage("§aA festa começou! Bom jogo a todos!").setType(MessageType.COMMON).toString());
					startTickingGame();
					cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}
	

	
//	private String getGameLogo(ChatColor logoColor, ChatColor bracketsColor) {
//		switch (this.getGameType()) {
//		
//		case CAPTURE_THE_FLAG:
//			return bracketsColor + "[" + logoColor + "Capture the Flag" + bracketsColor + "]";
//			
//		case HOT_POTATO:
//			
//			return bracketsColor + "[" + logoColor + "Hot Potato" + bracketsColor + "]";
//		}
//		
//		return bracketsColor + "[" + logoColor + "Lobby" + bracketsColor + "]";
//	}
	
	public void addPlayer(Player p) {
		if (getPlayersQuantity() >= getMaxPlayers()) {
			p.sendMessage("§cNão foi possivel juntar-se, este lobby já está lotado!");
			return;
		}
		
		if (getGameStage() == GameStage.PLAYING) {
			p.sendMessage("§cNão foi possivel juntar-se, este lobby já começou!");
			return;
		}
		
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
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getCountdownTime() {
		return timeCoutdown;
	}

	public int getCurrentTime() {
		return timeCurrent;
	}
	
	public void setCountdownTime(int seconds) {
		this.timeCoutdown = seconds;
	}

	public void setCurrentTime(int currentTime) {
		this.timeCurrent = currentTime;
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
	
	private int getPlayersQuantity() {
		return playersList.size();
	}
}
