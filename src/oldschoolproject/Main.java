package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Commands.GameCommands;
import oldschoolproject.Commands.Generic;
import oldschoolproject.Commands.Framework.Command;
import oldschoolproject.Commands.Framework.CommandArgs;
import oldschoolproject.Commands.Framework.CommandFramework;
import oldschoolproject.Listeners.Interactions;
import oldschoolproject.Listeners.Join;
import oldschoolproject.Listeners.Quit;
import oldschoolproject.Listeners.Inventories.VoteInventory;

public class Main extends JavaPlugin {
	
	public static CommandFramework commandFramework;
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		commandFramework = new CommandFramework(this);
		
		registerAll();
		
		Bukkit.getConsoleSender().sendMessage("§e[OldSchool] Plugin loaded");
	}
	
	void registerAll() {
		// Commands
		new Generic();
		new GameCommands();
		
		// Events
		new VoteInventory();
		new Interactions();
		new Join();
		new Quit();
	}
}
