package oldschoolproject.Commands;

import org.bukkit.entity.Player;

import oldschoolproject.Main;
import oldschoolproject.Utils.CommandFramework.Command;
import oldschoolproject.Utils.CommandFramework.CommandArgs;

public class Game {

	public Game() {
		Main.commandFramework.registerCommands(this);
	}
	
	@Command(name = "game", permission = "admin")
	public void commandExecute(CommandArgs args) {
		Player p = (Player)args.getSender();
		
		if (args.length() == 0) {
			p.sendMessage("§cErro: /game (start | reset | forcestart)");
			return;
		}
		
		switch (args.getArgs(0)) {
		
		case "start":
			p.sendMessage("start");
			break;
			
		case "reset":
			p.sendMessage("reset");
			break;
			
		case "forcestart":
			p.sendMessage("forcestart");
			break;
		}
	}
}
