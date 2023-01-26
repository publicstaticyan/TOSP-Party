package oldschoolproject.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.ServerListPingEvent;

import oldschoolproject.Main;
import oldschoolproject.Managers.GameManager;
import oldschoolproject.Managers.GameType;

public class Interactions implements Listener {
	
	public Interactions() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void openVoteInventory(PlayerInteractEvent e) {
		if (!GameManager.isPlaying()) {
			if (e.getItem().getType() == Material.CHEST) {
				e.getPlayer().performCommand("open");
			}
		}
	}
	
	@EventHandler
	public void hotbarItemLock(InventoryClickEvent e) {
		if (!GameManager.isPlaying()) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.CHEST) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void setMotd(ServerListPingEvent e) {
		if (GameManager.isPlaying()) {
			e.setMotd("§eEstamos jogando: §6" + GameManager.getCurrentGame() + "\n §7Aguarde alguns minutos para jogar conosco!");
		} else {
			e.setMotd("§aAguardando um novo jogo! Venha jogar conosco!");
		}
	}
	
}
