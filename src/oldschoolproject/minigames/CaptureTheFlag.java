package oldschoolproject.minigames;

import org.bukkit.entity.Player;

import oldschoolproject.lobby.Lobby;
import oldschoolproject.lobby.Minigame;
import oldschoolproject.managers.SettingsManager;

public class CaptureTheFlag extends Lobby {
	
	public CaptureTheFlag(String game_id) {
		super(Minigame.CAPTURE_THE_FLAG, game_id);
	}

	@Override
	public void start() {

	}

	@Override
	public void teleport() {
		for (Player all : getPlayerList()) {
			all.teleport(SettingsManager.load(getLowerCaseTag()).getLocation(getId() + ".arena"));
			all.sendMessage("§acomeçou!");
		}
	}
}
