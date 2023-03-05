package oldschoolproject.Managers.Game;

public enum GameType {
	
	CAPTURE_THE_FLAG("CTF"), HOT_POTATO("HotPotato");
	
	String displayName;
	
	GameType(String displayName) {
		this.displayName = displayName;
	}
	
	String getDisplayName() {
		return displayName;
	}
	
	void beginGame() {
		
	}
}
