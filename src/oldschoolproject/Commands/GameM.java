package oldschoolproject.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class GameM extends BaseCommand {

	public GameM() {
		super("gamem");
	}
	
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			
		}
		
	}

}
