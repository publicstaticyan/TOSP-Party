package oldschoolproject.Utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import oldschoolproject.Main;

public class Methods {
	
	public static ItemStack ci(Material material, int amount, String s) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(s);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack ci(Material material, int amount, String s, List<String> desc) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(s);
		im.setLore(desc);
		is.setItemMeta(im);
		return is;
	}
}
