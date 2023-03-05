package oldschoolproject.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Game extends BaseCommand {

	public Game() {
		super("game");
	}
	
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§c/game [join : quit] [ctf]");
			return;
		}
		
	}

}
