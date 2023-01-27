package oldschoolproject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import oldschoolproject.Commands.Chat;
import oldschoolproject.Commands.Game;
import oldschoolproject.Commands.Vote;
import oldschoolproject.Listeners.Extra;
import oldschoolproject.Listeners.Join;
import oldschoolproject.Listeners.Quit;
import oldschoolproject.Listeners.Inventories.VoteInventory;
import oldschoolproject.Utils.CommandFramework.CommandFramework;

public class Main extends JavaPlugin {
	
	public static CommandFramework commandFramework;
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public void onEnable() {
		
		commandFramework = new CommandFramework(this);
		
		registerListeners();
		registerCommands();
		
		Bukkit.getConsoleSender().sendMessage("§e[OldSchool] Plugin loaded");
		
		logTimes();
		
	    (new BukkitRunnable() {
	        public void run() {
	          checkIfModified();
	        }
	      }).runTaskTimer(this, 1L, 20L);
	}
	
	void registerCommands() {
		new Vote();
		new Game();
		new Chat();
	}
	
	void registerListeners() {
		new VoteInventory();
		new Extra();
		new Join();
		new Quit();
	}
	
	private final Map<String, Long> timeSinceLastChanged = new HashMap<>();

	  
	  void checkIfModified() {
	    File folder = new File("plugins/");
	    if (folder.exists() && folder.isDirectory()) {
	    File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      String fileName = listOfFiles[i].getName();
	      if (listOfFiles[i].isFile() && fileName.endsWith(".jar"))
	        if (this.timeSinceLastChanged.containsKey(fileName)) {
	          long time = ((Long)this.timeSinceLastChanged.get(fileName)).longValue();
	          if (time < listOfFiles[i].lastModified()) {
	             Bukkit.reload();
	              this.timeSinceLastChanged.remove(fileName);
	              this.timeSinceLastChanged.put(fileName, Long.valueOf(listOfFiles[i].lastModified()));
	          } 
	        }  
	    } 
	    }
	  }
	  
	  void logTimes() {
		    File folder = new File("plugins/");
		    File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".jar")) {
		        this.timeSinceLastChanged.put(listOfFiles[i].getName(), Long.valueOf(listOfFiles[i].lastModified()));
		      } 
		    } 
		  }
}