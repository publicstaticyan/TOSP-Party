package oldschoolproject.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.managers.SettingsManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class Setspawn extends BaseCommand {

	public Setspawn() {
		super("setspawn");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		Location roundedLocation = new Location(
				p.getLocation().getWorld(),
				Math.round(p.getLocation().getX()) + 0.5,
				Math.round(p.getLocation().getY()),
				Math.round(p.getLocation().getZ()) + 0.5);
		
		SettingsManager.saveLocation(roundedLocation, SettingsManager.getConfig().createSection("spawn"));
		
		p.sendMessage("§7Localização: §aSpawn §7setada com sucesso");
	}

}
