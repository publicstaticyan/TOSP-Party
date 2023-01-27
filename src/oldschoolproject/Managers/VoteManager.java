package oldschoolproject.Managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VoteManager {
	
	public static Map<Player, GameType> playerVote = new HashMap<>();
	public static Map<GameType, Integer> voteTable = new HashMap<>();
	
	public static void vote(Player player, GameType game) {
		playerVote.put(player, game);
	}
	
	public static void countVotes() {
		for (Player p : playerVote.keySet()) {
			GameType gt = playerVote.get(p);
			voteTable.put(gt, voteTable.getOrDefault(gt, 0) + 1);
		}
	}
	
	public static void printVotes() {
		for (GameType key : voteTable.keySet()) {
			Integer votes = voteTable.get(key);
			Bukkit.getLogger().info(key + " : " + votes);
		}
	}
}
