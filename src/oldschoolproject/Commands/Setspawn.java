package oldschoolproject.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.Managers.ConfigurationFile;
import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Setspawn extends BaseCommand {

	public Setspawn() {
		super("setspawn");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		Location loc = p.getLocation();
		double x = loc.getX(); double y = loc.getY(); double z = loc.getZ();
		
		ConfigurationFile.getConfig().set("spawn.X", x);
		ConfigurationFile.getConfig().set("spawn.Y", y);
		ConfigurationFile.getConfig().set("spawn.Z", z);
		ConfigurationFile.saveConfig();
		
		p.sendMessage("§aLocalização: §7Spawn §asetada com sucesso para as coordenadas: ");
		p.sendMessage("§aX: §7" + x + " §aY: §7" + y + " §aZ: §7" + z);
	}

}
