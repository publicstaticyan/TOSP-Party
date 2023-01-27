package oldschoolproject.Commands;

import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.Commands.Framework.Command;
import oldschoolproject.Commands.Framework.CommandArgs;
import oldschoolproject.Listeners.Inventories.VoteInventory;
import oldschoolproject.Managers.VoteManager;

public class Generic {
	
	public Generic() {
		Main.commandFramework.registerCommands(this);
	}
	
	@Command(name = "open")
	public void openGameInventory(CommandArgs args) {
		Player p = (Player)args.getSender();
		VoteInventory.openInventory(p);
	}
	
	@Command(name = "count")
	public void count(CommandArgs args) {
		VoteManager.countVotes();
	}
	
	@Command(name = "print")
	public void printVotes(CommandArgs args) {
		VoteManager.printVotes();
	}

}
