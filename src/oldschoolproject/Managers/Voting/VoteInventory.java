//package oldschoolproject.Managers.Voting;
//
//import java.util.Arrays;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemFlag;
//
//import oldschoolproject.Main;
//import oldschoolproject.Managers.Game.GameManager;
//import oldschoolproject.Managers.Game.GameType;
//import oldschoolproject.Modules.Loaders.Listener.BaseListener;
//
//public class VoteInventory extends BaseListener {
//	
//	public static Inventory voteInventory = Bukkit.createInventory(null, 27, "§a§lVotação");
//	
//	void setItems() {
////		voteInventory.setItem(12, Methods.ci(Material.STONE_SWORD, 1, "§6Sabotador", Arrays.asList("§7Trabalhe em conjunto para descobrir quem é o sabotador", "§7ou sabote todos antes que te descubram!"), ItemFlag.HIDE_ATTRIBUTES));
////		voteInventory.setItem(14, Methods.ci(Material.BAKED_POTATO, 1, "§6Batata Quente", Arrays.asList("§7Passe a batata para outra pessoa antes que seja tarde de mais", "§7O ultimo que sobrar ganha!")));
//	}
//	
//	public static void openInventory(Player p) {
//		p.openInventory(voteInventory);
//	}
//	
//	@EventHandler
//	public void hotbarItemLock(InventoryClickEvent e) {
//		if (!GameManager.isPlaying()) {
//			if (!e.getWhoClicked().isOp()) {
//				if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.CHEST && e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Votação")) {
//					e.setCancelled(true);
//				}
//			}
//		}
//	}
//	
//	@EventHandler
//	public void voteForGame(InventoryClickEvent e) {
//		if (!GameManager.isPlaying()) {
//			Player p = (Player) e.getWhoClicked();
//			if (e.getClickedInventory() == voteInventory) {
//				if (e.getCurrentItem() != null) {
//					switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
//					case "§6Sabotador":
//						VoteManager.vote(p, GameType.SABOTAGE);
//						break;
//		
//					case "§6Batata Quente":
//						VoteManager.vote(p, GameType.HOTPOTATO);
//						break;
//					}
//					e.setCancelled(true);
//				}
//			}
//		}
//	}
//	
//	@EventHandler
//	public void openVoteInventory(PlayerInteractEvent e) {
//		if (!GameManager.isPlaying()) {
//			if (e.getItem() != null && e.getItem().getType() == Material.CHEST) {
//				e.getPlayer().performCommand("vote");
//			}
//		}
//	}
//
//}
