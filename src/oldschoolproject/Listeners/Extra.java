package oldschoolproject.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.ServerListPingEvent;

import oldschoolproject.Main;
import oldschoolproject.Managers.GameManager;

public class Extra implements Listener {
	
	public Extra() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void hotbarItemLock(InventoryClickEvent e) {
		if (!GameManager.isPlaying()) {
			if (e.getCurrentItem() != null && !e.getWhoClicked().isOp()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void setMotd(ServerListPingEvent e) {
		if (GameManager.isPlaying()) {
			e.setMotd("6§l[§e§l§OLD SCHOOL§6§l] §eEstamos jogando: §6" + GameManager.getCurrentGame().toString() + "\n§7Aguarde alguns minutos para jogar conosco!");
		} else {
			e.setMotd("§6§l[§e§lOLD SCHOOL§6§l] §aA festa vai começar! Venha jogar! \n§7Aguardando jogadores para começar...");
		}
	}
	
}
