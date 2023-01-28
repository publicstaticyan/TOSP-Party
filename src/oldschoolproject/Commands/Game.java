package oldschoolproject.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Utils.Base.BaseCommand;

public class Game extends BaseCommand {

	public Game() {
		super("game");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /game start | reset | forcestart");
			return;
		}
		
		switch (args[0]) {
		
		case "start":
			p.sendMessage("start");
			break;
			
		case "reset":
			p.sendMessage("reset");
			break;
			
		case "forcestart":
			p.sendMessage("forcestart");
			break;
			
		default:
			p.sendMessage("§cErro: /game start | reset | forcestart");
			break;
		}
	}
}
