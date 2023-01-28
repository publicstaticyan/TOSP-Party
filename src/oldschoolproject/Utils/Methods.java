package oldschoolproject.Utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
	
	public static ItemStack ci(Material material, int amount, String s, List<String> desc, ItemFlag itemflag) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(s);
		im.setLore(desc);
		im.addItemFlags(itemflag);
		is.setItemMeta(im);
		return is;
	}
}
