package oldschoolproject.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Lobby extends BaseCommand {

	public Lobby() {
		super("lobby");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§c/lobby [set : remove] [game]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("")) {
			
		}
	}

}
