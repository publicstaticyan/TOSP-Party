package oldschoolproject.game;

import oldschoolproject.minigames.CTF;
import oldschoolproject.minigames.SurvivalGames;

public enum Minigame {
	
	// Capture_The_Flag("CTF", 4, 20, 1200, new CTF())
	SURVIVAL_GAMES("SurvivalGames", "SG"),
	CAPTURE_THE_FLAG("Capture the Flag", "CTF");
	
	String tag, name;
	
	// TODO: In a later release, erase this enum. Create lobbys types based on command inputs
	
	Minigame(String name, String tag) {
		this.setTag(tag);
		this.setName(name);
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
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
