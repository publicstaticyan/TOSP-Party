package oldschoolproject.Commands;

import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.Commands.Builder.Command;
import oldschoolproject.Commands.Builder.CommandArgs;
import oldschoolproject.Listeners.Vote;

public class Generic {
	
	public Generic() {
		Main.cf.registerCommands(this);
	}
	
	@Command(name = "open")
	public void openGameInventory(CommandArgs args) {
		Player p = (Player)args.getSender();
		Vote.openInventory(p);
	}

}
