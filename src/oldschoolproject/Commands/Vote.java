package oldschoolproject.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Listeners.Inventories.VoteInventory;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Utils.Base.BaseCommand;

public class Vote extends BaseCommand {

	public Vote() {
		super("vote");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!GameManager.isPlaying()) {
			Player p = (Player)sender;
			VoteInventory.openInventory(p);
		}
	}

}
