package oldschoolproject.Managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import oldschoolproject.Main;
import oldschoolproject.Utils.Methods;

public class VoteManager {
	
	static Map<Player, GameType> playerVote = new HashMap<>();
	static Map<GameType, Integer> voteTable = new HashMap<>();
	public static int timerCountdown = 60;
	static BukkitTask bt;
	
	public static void vote(Player player, GameType game) {
		playerVote.put(player, game);
		
		Bukkit.broadcastMessage("hp: " + getVotes(GameType.HOTPOTATO));
		Bukkit.broadcastMessage("sab: " + getVotes(GameType.SABOTAGE));
		
		if (getVotes(GameType.HOTPOTATO) + getVotes(GameType.SABOTAGE) > Bukkit.getOnlinePlayers().size() / 2) {
			startCounter();
		}
	}
	
//	public static void countVotes() {
//		for (GameType gt : playerVote.values()) {
//			voteTable.put(gt, voteTable.getOrDefault(gt, 0) + 1);
//		}
//	}
	
	public static Integer getVotes(GameType gt) {
		int i = 0;
		for (GameType gth : playerVote.values()) {
			if (gth == gt)
				i++;
		}
		
		return i;
	}
	
	public static void startCounter() {
		bt = new BukkitRunnable() {
			public void run() {
				timerCountdown--;
				switch (timerCountdown) {
				case 59:
					Bukkit.broadcastMessage("§eEncerrando votação em 1 minuto.");
					break;
				case 29:
					Bukkit.broadcastMessage("§eEncerrando votação em 30 segundos.");
					break;
				case 9:
					Bukkit.broadcastMessage("§eEncerrando votação em 10 segundos.");
					break;
				case -1:
					Bukkit.broadcastMessage("§aVotação encerrada!");
					bt.cancel();
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}
	
	public static String getCountdown() {
		return timerCountdown == 60 ? "-" : Methods.convertSecondsToStr(timerCountdown);
	}
	
//	public static void printVotes() {
//		for (GameType key : voteTable.keySet()) {
//			Integer votes = voteTable.get(key);
//			Bukkit.getLogger().info(key + " : " + votes);
//		}
//	}
}
