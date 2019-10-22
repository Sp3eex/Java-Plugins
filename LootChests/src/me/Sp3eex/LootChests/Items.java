package me.Sp3eex.LootChests;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	private static ItemStack Armor(String name, Material material, int protection, int durability, int thorns, int fireprotection, int projectileprotection) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		if (protection != 0) { itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protection, true); }
		if (fireprotection != 0) { itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, fireprotection, true); }
		if (projectileprotection != 0) { itemMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, projectileprotection, true); }
		if (durability != 0) { itemMeta.addEnchant(Enchantment.DURABILITY, durability, true); }
		if (thorns != 0) { itemMeta.addEnchant(Enchantment.THORNS, thorns, true); }
		if (name != "") { itemMeta.setDisplayName(name); }
		item.setItemMeta(itemMeta);
		return item;
	}
	
	private static ItemStack Tools(String name, Material material, int sharpness, int fire, int digspeed, int durability, int fortune, int looting, int sliktouch) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		if (sharpness != 0) { itemMeta.addEnchant(Enchantment.DAMAGE_ALL, sharpness, true); }
		if (fire != 0) { itemMeta.addEnchant(Enchantment.FIRE_ASPECT, fire, true); }
		if (digspeed != 0) { itemMeta.addEnchant(Enchantment.DIG_SPEED, digspeed, true); }
		if (durability != 0) { itemMeta.addEnchant(Enchantment.DURABILITY, durability, true); }
		if (fortune != 0) { itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, fortune, true); }
		if (looting != 0) { itemMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, looting, true); }
		if (sliktouch != 0) { itemMeta.addEnchant(Enchantment.SILK_TOUCH, sliktouch, true); }
		if (name != "") { itemMeta.setDisplayName(name); }
		item.setItemMeta(itemMeta);
		return item;
	}
	
	private static ItemStack KnockbackStick() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.addEnchant(Enchantment.KNOCKBACK, (new Random().nextInt(2) + 1), true);
		itemMeta.addEnchant(Enchantment.DAMAGE_ALL, (new Random().nextInt(2) + 1), true);
		itemMeta.setDisplayName(ChatColor.BLUE + "Knockback stick");
		item.setItemMeta(itemMeta);
		return item;
	}
	
	static ItemStack GetRandomItem() {
		ItemStack[] items = {
			Armor("", Material.DIAMOND_HELMET, new Random().nextInt(5), new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_CHESTPLATE, new Random().nextInt(5), new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_LEGGINGS, new Random().nextInt(5), new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_BOOTS, new Random().nextInt(5), new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.IRON_HELMET, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_CHESTPLATE, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_LEGGINGS, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_BOOTS, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_HELMET, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_CHESTPLATE, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_LEGGINGS, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_BOOTS, new Random().nextInt(4), 0, 0, 0, 0),
			Armor("", Material.IRON_HELMET, new Random().nextInt(4)+1, new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.IRON_CHESTPLATE, new Random().nextInt(4)+1, new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.IRON_LEGGINGS, new Random().nextInt(4)+1, new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.IRON_BOOTS, new Random().nextInt(4)+1, new Random().nextInt(4)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_HELMET, new Random().nextInt(3)+1, new Random().nextInt(3)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_CHESTPLATE, new Random().nextInt(3)+1, new Random().nextInt(3)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_LEGGINGS, new Random().nextInt(3)+1, new Random().nextInt(3)+1, 0, 0, 0),
			Armor("", Material.DIAMOND_BOOTS, new Random().nextInt(3)+1, new Random().nextInt(3)+1, 0, 0, 0),
			Tools("", Material.IRON_SWORD, new Random().nextInt(1)+1, new Random().nextInt(1)+1, 0, new Random().nextInt(1), 0, new Random().nextInt(1), 0),
			Tools("", Material.IRON_AXE, new Random().nextInt(1)+2, new Random().nextInt(2)+1, 0, new Random().nextInt(1), 0, new Random().nextInt(1), 0),
			Tools("", Material.IRON_AXE, new Random().nextInt(1)+2, new Random().nextInt(3)+1, 0, new Random().nextInt(1), 0, new Random().nextInt(1), 0),
			Tools("", Material.IRON_AXE, new Random().nextInt(1)+1, new Random().nextInt(1)+1, 0, new Random().nextInt(1), 0, new Random().nextInt(1), 0),
			Tools("", Material.DIAMOND_SWORD, new Random().nextInt(4), 0, 0, new Random().nextInt(1)+1, 0, 0, 0),
			Tools("", Material.DIAMOND_AXE, new Random().nextInt(5), new Random().nextInt(2), 0, new Random().nextInt(2)+1, 0, 0, 0),
			Tools("", Material.DIAMOND_SWORD, new Random().nextInt(4)+1, 0, 0, new Random().nextInt(1)+1, 0, 0, 0),
			KnockbackStick(),
			new ItemStack(Material.LEATHER_BOOTS),
			new ItemStack(Material.LEATHER_CHESTPLATE),
			new ItemStack(Material.LEATHER_HELMET), 
			new ItemStack(Material.LEATHER_LEGGINGS),
			new ItemStack(Material.STONE_AXE),
			new ItemStack(Material.STONE_SWORD),
			new ItemStack(Material.IRON_AXE), 
			new ItemStack(Material.IRON_SWORD),
			new ItemStack(Material.DIAMOND_AXE),
			new ItemStack(Material.DIAMOND_SWORD), 
			new ItemStack(Material.EMERALD),
			new ItemStack(Material.EMERALD, 2),
			new ItemStack(Material.EMERALD, 4),
			new ItemStack(Material.DIAMOND), 
			new ItemStack(Material.DIAMOND, 2), 
			new ItemStack(Material.IRON_INGOT),
			new ItemStack(Material.IRON_INGOT, 2),
			new ItemStack(Material.IRON_INGOT, 4),
			new ItemStack(Material.GOLD_INGOT),
			new ItemStack(Material.GOLD_INGOT, 2),
			new ItemStack(Material.GOLD_INGOT, 2),
			new ItemStack(Material.GOLD_INGOT, 4),
			new ItemStack(Material.STONE, 8), 
			new ItemStack(Material.STONE, 16),
			new ItemStack(Material.STONE, 32), 
			new ItemStack(Material.STONE, 64), 
			new ItemStack(Material.STONE, 8), 
			new ItemStack(Material.STONE, 16),
			new ItemStack(Material.STONE, 32), 
			new ItemStack(Material.STONE, 64), 
			new ItemStack(Material.EMERALD),
			new ItemStack(Material.EMERALD, 2),
			new ItemStack(Material.EMERALD, 4),
			new ItemStack(Material.DIAMOND), 
			new ItemStack(Material.DIAMOND, 2), 
			new ItemStack(Material.IRON_INGOT),
			new ItemStack(Material.IRON_INGOT, 2),
			new ItemStack(Material.IRON_INGOT, 4),
			new ItemStack(Material.GOLD_INGOT),
			new ItemStack(Material.GOLD_INGOT, 2),
			new ItemStack(Material.GOLD_INGOT, 2),
			new ItemStack(Material.GOLD_INGOT, 4),
			new ItemStack(Material.STONE, 8), 
			new ItemStack(Material.STONE, 16),
			new ItemStack(Material.STONE, 32), 
			new ItemStack(Material.STONE, 64), 
			new ItemStack(Material.STONE, 8), 
			new ItemStack(Material.STONE, 16),
			new ItemStack(Material.STONE, 32), 
			new ItemStack(Material.STONE, 64), 
			new ItemStack(Material.OBSIDIAN, 1), 
			new ItemStack(Material.OBSIDIAN, 2), 
			new ItemStack(Material.APPLE, 2),
			new ItemStack(Material.GOLDEN_APPLE),
			new ItemStack(Material.BOW),
			new ItemStack(Material.BOW),
			new ItemStack(Material.BOW),
			new ItemStack(Material.ARROW, 4),
			new ItemStack(Material.ARROW, 8),
			new ItemStack(Material.COOKED_BEEF, 2), 
			new ItemStack(Material.COOKED_BEEF, 4),
			new ItemStack(Material.COOKED_BEEF, 8), 
			new ItemStack(Material.LAVA_BUCKET), 
			new ItemStack(Material.WATER_BUCKET), 
			new ItemStack(Material.COOKED_MUTTON, 2), 
			new ItemStack(Material.COOKED_MUTTON, 4), 
			new ItemStack(Material.TORCH, 2),
			new ItemStack(Material.TORCH, 4),
			new ItemStack(Material.COOKIE, 2), 
			new ItemStack(Material.COOKIE, 4), 
			new ItemStack(Material.PUMPKIN_PIE, 2), 
			new ItemStack(Material.PUMPKIN_PIE, 4), 
			new ItemStack(Material.BREAD, 2), 
			new ItemStack(Material.BREAD, 4), 
			new ItemStack(Material.BREAD, 8), 
			new ItemStack(Material.ARROW, 8),
			new ItemStack(Material.COOKED_BEEF, 2), 
			new ItemStack(Material.COOKED_BEEF, 4),
			new ItemStack(Material.COOKED_BEEF, 8), 
			new ItemStack(Material.LAVA_BUCKET), 
			new ItemStack(Material.WATER_BUCKET), 
			new ItemStack(Material.COOKED_MUTTON, 2), 
			new ItemStack(Material.COOKED_MUTTON, 4), 
			new ItemStack(Material.TORCH, 2),
			new ItemStack(Material.TORCH, 4),
			new ItemStack(Material.COOKIE, 2), 
			new ItemStack(Material.COOKIE, 4), 
			new ItemStack(Material.PUMPKIN_PIE, 2), 
			new ItemStack(Material.PUMPKIN_PIE, 4), 
			new ItemStack(Material.BREAD, 2), 
			new ItemStack(Material.BREAD, 4), 
			new ItemStack(Material.BREAD, 8), 
			new ItemStack(Material.ARROW, 8), 
			new ItemStack(Material.ARROW, 8), 
			new ItemStack(Material.ARROW, 8), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR), 
			new ItemStack(Material.AIR),
		};
		return items[new Random().nextInt(items.length)];
	}
}
