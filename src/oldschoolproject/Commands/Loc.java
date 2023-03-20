package oldschoolproject.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Managers.ConfigurationFile;
import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Loc extends BaseCommand {

	public Loc() {
		super("loc");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		Location loc = p.getLocation();
		
		double x = loc.getX(); double y = loc.getY(); double z = loc.getZ();
		
		if (args.length < 3) {
			p.sendMessage("§c/loc [set | remove] [spawn | lobby | arena] [game: (ctf, hotpotato)]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("set")) {
			
			ConfigurationFile.getConfig().set(args[2].toLowerCase() + "." + args[1].toLowerCase() + ".X", x);
			ConfigurationFile.getConfig().set(args[2].toLowerCase() + "." + args[1].toLowerCase() + ".Y", y);
			ConfigurationFile.getConfig().set(args[2].toLowerCase() + "." + args[1].toLowerCase() + ".Z", z);
			ConfigurationFile.saveConfig();
			
			p.sendMessage("§aLocalização: §7" + args[1].toLowerCase() + "§a setada com sucesso para as coordenadas: ");
			p.sendMessage("§aX: §7" + x + " §aY: §7" + y + " §aZ: §7" + z);
			
			return;
		}
		
		if (args[0].equalsIgnoreCase("remove")) {
			
			if (ConfigurationFile.getConfig().get(args[2].toLowerCase() + "." + args[1].toLowerCase()) == null) {
				p.sendMessage("§cEssa localização ainda não foi setada");
				return;
			}
			
			ConfigurationFile.getConfig().set(args[2].toLowerCase() + "." + args[1].toLowerCase(), null);
			ConfigurationFile.saveConfig();
			
			p.sendMessage("§cLocalização: §7" + args[2].toLowerCase() + "§c removida para as coordenadas: ");
			p.sendMessage("§cX: §7" + x + " §cY: §7" + y + " §cZ: §7" + z);
			return;
		}
	}

}
