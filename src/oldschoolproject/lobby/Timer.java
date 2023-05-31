package oldschoolproject.lobby;

import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import oldschoolproject.Main;

public class Timer {
	
	private static final int WAITING_COUNTDOWN_SECONDS = 181;
	private static final int BEGGINING_COUNTDOWN_SECONDS = 9;
	private static final int[] MESSAGE_MARKS = {180, 150, 120, 90, 60, 30, 10, 5, 4, 3, 2, 1};
	private boolean reTry = false;
	private Lobby game;
	
	public Timer(Lobby game) {
		this.game = game;
		
		countdown();
	}
	
	private void beggining() {
		sendMessageToAll("§aComeçando em 10 segundos!");
		
		new BukkitRunnable() {
			
			int counter = BEGGINING_COUNTDOWN_SECONDS;
			
			public void run() {
				
				if (contains(MESSAGE_MARKS, counter)) {
					sendMessageToAll("§a" + counter + "§a...");
				}
				
				if (counter < 0) {
					game.start();
					cancel();
				}
				
				counter--;
			}
		}.runTaskTimer(Main.getInstance(), 0, 20);
	}
	
	private void countdown() {
		new BukkitRunnable() {
			
			int counter = WAITING_COUNTDOWN_SECONDS;
			
			public void run() {
				if (contains(MESSAGE_MARKS, counter)) {
					countdownMessage(counter);
				}
				
				sendMessageToAll(convertSecondsToMinutes(counter));
				
				if (counter == 90) {
					if (!playerCountValid()) {
						sendMessageToAll("§eNão há jogadores suficientes para começar o jogo! Reiniciando contador...");
						counter = WAITING_COUNTDOWN_SECONDS;
					}
				}
				
				if (counter < 30) {
					if (!playerCountValid()) {
						if (!reTry) {
							counter = 60;
							sendMessageToAll("§cPoxa... Um jogador saiu e agora não há jogadores suficientes para começar o jogo");
							sendMessageToAll("§cContador reiniciado para um minuto");
							reTry = true;
						} else {
							counter = WAITING_COUNTDOWN_SECONDS;
							sendMessageToAll("§eNão há jogadores suficientes para começar o jogo! Reiniciando contador...");
							reTry = false;
						}
					}
				}
				
				if (counter < 0) {
					game.teleport();
					game.setStage(Stage.BEGINNING);
					
					beggining();
					cancel();
				}
				
				counter--;
			}
		}.runTaskTimer(Main.getInstance(), 0, 20);
	}
	
	private boolean contains(final int[] array, final int key) {
		return ArrayUtils.contains(array, key);
	}
	
	private boolean playerCountValid() {
		if (this.game.getPlayerList().size() >= this.game.getMinPlayers()) {
			return true;
		}
		return false;
	}
	
	private void sendMessageToAll(String s) {
		for (Player p : this.game.getPlayerList()) {
			p.sendMessage("§3[" + this.game.getMinigame().getTag() + "§3] " + s);
		}
	}
	
	private void countdownMessage(int s) {
		StringBuilder sb = new StringBuilder("§eO jogo irá começar em ");
		if (s < 60) {
			sb.append("§a" + convertSecondsToMinutes(s) + " segundo(s)!");
		} else {
			sb.append("§b" + convertSecondsToMinutes(s) + " minuto(s)!");
		}
		sendMessageToAll(sb.toString());
	}
	
	private String convertSecondsToMinutes(int sec) {
	    return String.format("%02d:%02d", sec / 60, sec % 60);
	}
}
