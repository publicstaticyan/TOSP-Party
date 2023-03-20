package oldschoolproject.Managers.Game;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import oldschoolproject.Main;
import oldschoolproject.Modules.Builders.MessageBuilder;
import oldschoolproject.Modules.Builders.MessageBuilder.MessageType;

public class GameTimer {
	
	private GameManager game;
	private MessageBuilder mb;
	
	private int limitTime;
	
	private int countdownTime;
	private int currentTime;
	
	private static final int LOBBY_WAITING_TIME = 180;
	private static final int LOBBY_PLAYER_CHECK_TIME = 60;
	
	public GameTimer(GameManager game, int limitTime) {
		this.limitTime = limitTime;
		this.game = game;
		
		this.mb = new MessageBuilder(game.getGameType().getDisplayName());
		
		this.setCountdownTime(LOBBY_WAITING_TIME);
		this.setCurrentTime(0);
	}
	
	public void startLobbyCoundown() {
		new BukkitRunnable() {
			
			public void run() {
				
				if (!game.isPaused()) {
					countdownTime--;
				}
				
				switch (countdownTime) {
				
				case LOBBY_PLAYER_CHECK_TIME:
					startCheckingPlayers();
					break;
					
				case 179:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §f3 minutos!").setType(MessageType.WARNING).toString());
					break;
					
				case 119:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §f2 minutos!").setType(MessageType.WARNING).toString());
					break;
					
				case 59:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §f1 minuto!").setType(MessageType.WARNING).toString());
					break;
					
				case 9:
					broadcastMessage(mb.setMessage("§7A festa vai começar em §e10 segundos!").setType(MessageType.WARNING).toString());
					game.setGameStage(GameStage.BEGGINING);
					
					// TODO: Teleport players to arena
					
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
					startGameTimer();
					
					cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}
	
	public void startGameTimer() {
		game.setGameStage(GameStage.PLAYING);
		
		new BukkitRunnable() {
			
			public void run() {
				setCurrentTime(getCurrentTime() + 1);
				
				if (!game.isPaused()) {
					limitTime--;
				}
				
				switch (limitTime) {
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
					// TODO teleport all to lobby
					cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20);
	}

	public void resetLobbyTimer() {
		setCountdownTime(LOBBY_WAITING_TIME + 5);
	}
	
	public void startCheckingPlayers() {
		new BukkitRunnable() {
			public void run() {
				if (game.getPlayersQuantity() < game.getMinPlayers()) {
					broadcastMessage(mb.setMessage("§cPoxa... Não haviam jogadores suficientes e o contador reiniciou!").setType(MessageType.DANGER).toString());
					
					game.setGameStage(GameStage.WAITING);
					resetLobbyTimer();
					cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}

	public void broadcastMessage(String msg) {
		for (Player players : game.getPlayersList()) {
			players.sendMessage(msg);
		}
	}
	
	public int getCountdownTime() {
		return countdownTime;
	}

	public void setCountdownTime(int countdownTime) {
		this.countdownTime = countdownTime;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	
}