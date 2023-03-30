package oldschoolproject.minigames;

import org.bukkit.entity.Player;

import oldschoolproject.managers.SettingsManager;
import oldschoolproject.managers.game.Lobby;
import oldschoolproject.managers.game.Minigame;

public class CTF extends Lobby {
	
	public CTF(String game_id) {
		
		// The enum class might be useless, since all parameters can be storaged in the "Lobby" class and passed through
		// the super method
		
		// The problem is: How do I verify if the user is typing a valid gametype when creating a lobby since I dont know how it 
		// looks like in first place
		
		// I'll keep using the enum to generate the tag AND some useful values that i might erase later and only leave the tag
		// Maybe this is the cleanest way to do it, instead of losing the control over simple String arrays.
		
		// !! Answer in enum class !! 
		
		super(Minigame.CAPTURE_THE_FLAG, game_id);
	}

	@Override
	public void start() {
		for (Player all : getPlayerList()) {
			all.teleport(SettingsManager.load(lowerCaseTag()).getLocation(getGame_id() + ".arena"));
			all.sendMessage("§acomeçou!");
		}
	}
}
