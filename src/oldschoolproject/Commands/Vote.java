package oldschoolproject.Commands;

import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.Listeners.Inventories.VoteInventory;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Managers.VoteManager;
import oldschoolproject.Utils.CommandFramework.Command;
import oldschoolproject.Utils.CommandFramework.CommandArgs;

public class Vote {
	
	public Vote() {
		Main.commandFramework.registerCommands(this);
	}
	
	@Command(name = "open")
	public void commandExecute(CommandArgs args) {
		Player p = (Player)args.getSender();
		if (!GameManager.isPlaying()) {
			VoteInventory.openInventory(p);
		}
	}
}
