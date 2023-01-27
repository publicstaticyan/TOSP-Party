package oldschoolproject.Listeners.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import oldschoolproject.Main;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Managers.GameType;
import oldschoolproject.Managers.VoteManager;
import oldschoolproject.Utils.Methods;

public class VoteInventory implements Listener {
	
	public VoteInventory() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
		
		setItems();
	}
	
	public static Inventory voteInventory = Bukkit.createInventory(null, 27, "Jogar");
	
	void setItems() {
		voteInventory.setItem(12, Methods.ci(Material.STONE_SWORD, 1, "§6Sabotador"));
		voteInventory.setItem(14, Methods.ci(Material.BAKED_POTATO, 1, "§6Batata Quente"));
	}
	
	public static void openInventory(Player p) {
		p.openInventory(voteInventory);
	}
	
	@EventHandler
	public void voteForGame(InventoryClickEvent e) {
		if (!GameManager.isPlaying()) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory() == voteInventory) {
				if (e.getCurrentItem() != null) {
					switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
					case "§6Sabotador":
						VoteManager.vote(p, GameType.SABOTAGE);
						p.sendMessage("sabotage");
						break;
		
					case "§6Batata Quente":
						VoteManager.vote(p, GameType.HOTPOTATO);
						p.sendMessage("hotpotato");
						break;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void openVoteInventory(PlayerInteractEvent e) {
		if (!GameManager.isPlaying()) {
			if (e.getItem().getType() == Material.CHEST) {
				e.getPlayer().performCommand("open");
			}
		}
	}

}
