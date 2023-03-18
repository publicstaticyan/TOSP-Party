package oldschoolproject.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Minigames.CTF;
import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Spawn extends BaseCommand {

	public Spawn() {
		super("spawn");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
	}

}
