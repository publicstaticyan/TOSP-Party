package oldschoolproject.managers.game;

public enum Minigame {
	
	// Capture_The_Flag("CTF", 4, 20, 1200, new CTF())
	Capture_The_Flag("CTF", 4, 20, 1200);
	
	String tag;
	int minPlayers, maxPlayers, timeLimit;
	
	// TODO: Remove the minMax Players and timeLimit from here, put all in Lobby
	
	// TODO: In a later release, erase this enum. Create lobbys based on command inputs
	
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
	
	public static Minigame hasTag(String str) {
		for (Minigame minigame : Minigame.values()) {
			if (minigame.getTag().equalsIgnoreCase(str)) {
				return minigame;
			}
		}
		return null;
	}
	
	

}
