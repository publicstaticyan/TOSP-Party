package oldschoolproject.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import oldschoolproject.Main;
import oldschoolproject.Utils.Methods;

public class Vote implements Listener {
	
	public static Inventory gameInventory = Bukkit.createInventory(null, 27, "Jogar");
	
	public Vote() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
		
		initItems();
	}
	
	void initItems() {
		gameInventory.setItem(12, Methods.ci(Material.STONE_SWORD, 1, "§6Sabotage"));
		gameInventory.setItem(13, Methods.ci(Material.LEGACY_MUSHROOM_SOUP, 1, "§6HG"));
		gameInventory.setItem(14, Methods.ci(Material.FIREWORK_ROCKET, 1, "§6Party"));
	}
	
	public static void openInventory(Player p) {
		p.openInventory(gameInventory);
	}

}
