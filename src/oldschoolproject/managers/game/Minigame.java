package oldschoolproject.managers.game;

import oldschoolproject.minigames.CTF;
import oldschoolproject.minigames.SurvivalGames;

public enum Minigame {
	
	// Capture_The_Flag("CTF", 4, 20, 1200, new CTF())
	SURVIVAL_GAMES("CTF", 4, 20, 1200),
	CAPTURE_THE_FLAG("CTF", 4, 20, 1200);
	
	String tag;
	int minPlayers, maxPlayers, timeLimit;
	
	// TODO: Remove the minMax Players and timeLimit from here, put all in Lobby
	
	// TODO: In a later release, erase this enum. Create lobbys types based on command inputs
	
	Minigame(String tag, int minPlayers, int maxPlayers, int timeLimit) {
		this.setTag(tag);
		this.setMinPlayers(minPlayers);
		this.setMaxPlayers(maxPlayers);
		this.timeLimit = timeLimit;
	}
	
	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}
	
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public Lobby instance(String gameId) {
		switch (this) {
		case CAPTURE_THE_FLAG:
			return new CTF(gameId);
		case SURVIVAL_GAMES:
			return new SurvivalGames(gameId);
		}
		return null;
	}
	
//	public static boolean minigameExists(String str) {
//		return getMinigame(str) != null;
//	}
	
	public static Minigame getMinigame(String str) {
		for (Minigame minigame : Minigame.values()) {
			if (minigame.getTag().equalsIgnoreCase(str)) {
				return minigame;
			}
		}
		return null;
	}
}
