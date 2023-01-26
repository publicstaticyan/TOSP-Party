package oldschoolproject;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import oldschoolproject.Commands.Generic;
import oldschoolproject.Commands.Builder.Command;
import oldschoolproject.Commands.Builder.CommandArgs;
import oldschoolproject.Commands.Builder.CommandFramework;
import oldschoolproject.Listeners.Interactions;
import oldschoolproject.Listeners.Join;
import oldschoolproject.Listeners.Quit;
import oldschoolproject.Listeners.Vote;

public class Main extends JavaPlugin {
	
	public static CommandFramework cf;
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		cf = new CommandFramework(this);
		
		registerAll();
		
		Bukkit.getConsoleSender().sendMessage("§e[OldSchool] Plugin loaded");
	}
	
	void registerAll() {
		// Commands
		new Generic();
		
		// Events
		new Vote();
		new Interactions();
		new Join();
		new Quit();
	}
}
