package me.Sp3eex.MiniCrates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items
{
    Date now;
    SimpleDateFormat format;
    public int common;
    public int uncommon;
    public int rare;
    
    public Items() {
        this.now = new Date();
        this.format = new SimpleDateFormat("dd/MM/yyyy");
        this.common = 75;
        this.uncommon = 22;
        this.rare = 3;
    }
    
    public ItemStack ShinyItem() {
        final ItemStack item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        final ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.GRAY + "Rolling...");
        itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
        itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        item.setItemMeta(itemmeta);
        return item;
    }
    
    public ItemStack PermissionItem(final String permission) {
        final ItemStack item = new ItemStack(Material.NAME_TAG);
        final ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.GRAY + "Permission " + ChatColor.BLUE + "Key!");
        final ArrayList<String> itemLore = new ArrayList<String>();
        itemLore.add(ChatColor.WHITE + "Right click to claim it!");
        itemLore.add(ChatColor.GOLD + "Permission: " + permission);
        itemmeta.setLore(itemLore);
        itemmeta.addEnchant(Enchantment.DURABILITY, 10, true);
        item.setItemMeta(itemmeta);
        return item;
    }
    
    private int getRMoney() {
        final int[] ammounts = { 500, 1000, 2000, 5000, 10000 };
        return ammounts[new Random().nextInt(ammounts.length)];
    }
    
    public ItemStack MoneyItem(final String player) {
        final ItemStack item = new ItemStack(Material.PAPER);
        final ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.GREEN + "$" + this.getRMoney());
        itemmeta.addEnchant(Enchantment.DURABILITY, 10, true);
        itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + this.format.format(this.now) + " (" + player + ")");
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        return item;
    }
    
    public ItemStack CrateKey() {
        final ItemStack item = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.WHITE + "Voting " + ChatColor.AQUA + "Key!");
        final ArrayList<String> itemLore = new ArrayList<String>();
        itemLore.add(ChatColor.WHITE + "Go to the vote chest to open it and recieve a reward!");
        itemLore.add(ChatColor.AQUA + "Type /warp crate!");
        itemmeta.setLore(itemLore);
        itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS });
        itemmeta.addEnchant(Enchantment.DURABILITY, 10, true);
        item.setItemMeta(itemmeta);
        return item;
    }
    
    public ItemStack IronBarsWithChances(final int rare, final int uncommon, final int common) {
        final ItemStack item = new ItemStack(Material.SLIME_BALL);
        final ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.RED + "Chances");
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Rare: " + ChatColor.WHITE + rare + "% | 100%");
        lore.add(ChatColor.GRAY + "Uncommon: " + ChatColor.WHITE + uncommon + "% | 100%");
        lore.add(ChatColor.GRAY + "Common: " + ChatColor.WHITE + common + "% | 100%");
        itemmeta.setLore(lore);
        itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item.setItemMeta(itemmeta);
        return item;
    }
    
    public ItemStack Crate() {
        final ItemStack item = new ItemStack(Material.CHEST);
        final ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.RED + "Prize Crate");
        itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        item.setItemMeta(itemmeta);
        return item;
    }
    
    public Inventory GetFullPrizeInventory() {
        final Inventory crateInventory = Bukkit.createInventory((InventoryHolder)null, 45, "Prize");
        for (int i = 0; i <= crateInventory.getSize() - 1; ++i) {
            if (i <= 9 || i == 17 || i == 18 || i == 26 || i == 27 || i >= 35) {
                crateInventory.setItem(i, this.ShinyItem());
            }
            if (i >= 10 && i <= 16) {
                crateInventory.setItem(i, new ItemStack(Material.IRON_BARS));
            }
            if (i >= 28 && i <= 34) {
                crateInventory.setItem(i, new ItemStack(Material.IRON_BARS));
            }
        }
        crateInventory.setItem(26, this.IronBarsWithChances(3, 20, 65));
        crateInventory.setItem(25, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(24, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(23, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(22, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(21, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(20, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(19, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        crateInventory.setItem(18, this.IronBarsWithChances(3, 20, 65));
        return crateInventory;
    }
    
    public ItemStack Armor(final String name, final Material material, final int protection, final int durability, final int thorns, final int fireprotection, final int projectileprotection, final Player player) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta itemMeta = item.getItemMeta();
        if (protection != 0) {
            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protection, true);
        }
        if (fireprotection != 0) {
            itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, fireprotection, true);
        }
        if (projectileprotection != 0) {
            itemMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, projectileprotection, true);
        }
        if (durability != 0) {
            itemMeta.addEnchant(Enchantment.DURABILITY, durability, true);
        }
        if (thorns != 0) {
            itemMeta.addEnchant(Enchantment.THORNS, thorns, true);
        }
        if (name != "") {
            itemMeta.setDisplayName(name);
        }
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.GRAY).append(ChatColor.STRIKETHROUGH).append(StringUtils.repeat(" ", 15)).toString());
        lore.add(ChatColor.GREEN + this.format.format(this.now) + " (" + player.getName() + ")");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
    
    public ItemStack Tools(final String name, final Material material, final int digspeed, final int durability, final int fortune, final int looting, final int sliktouch, final int mending, final Player player) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta itemMeta = item.getItemMeta();
        if (digspeed != 0) {
            itemMeta.addEnchant(Enchantment.DIG_SPEED, digspeed, true);
        }
        if (durability != 0) {
            itemMeta.addEnchant(Enchantment.DURABILITY, durability, true);
        }
        if (fortune != 0) {
            itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, fortune, true);
        }
        if (looting != 0) {
            itemMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, looting, true);
        }
        if (sliktouch != 0) {
            itemMeta.addEnchant(Enchantment.SILK_TOUCH, sliktouch, true);
        }
        if (mending != 0) {
            itemMeta.addEnchant(Enchantment.MENDING, mending, true);
        }
        if (name != "") {
            itemMeta.setDisplayName(name);
        }
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.GRAY).append(ChatColor.STRIKETHROUGH).append(StringUtils.repeat(" ", 15)).toString());
        lore.add(ChatColor.GREEN + this.format.format(this.now) + " (" + player.getName() + ")");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
    
    public ItemStack KnockbackStick(final Player player) {
        final ItemStack item = new ItemStack(Material.STICK);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.KNOCKBACK, new Random().nextInt(5) + 1, true);
        itemMeta.addEnchant(Enchantment.DAMAGE_ALL, new Random().nextInt(5) + 1, true);
        itemMeta.setDisplayName(ChatColor.BLUE + "Knockback stick");
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(new StringBuilder().append(ChatColor.GRAY).append(ChatColor.STRIKETHROUGH).append(StringUtils.repeat(" ", 15)).toString());
        lore.add(ChatColor.GREEN + this.format.format(this.now) + " (" + player.getName() + ")");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
    
    public ChatColor getRandomColor() {
        final int pick = new Random().nextInt(7);
        if (pick == 0) {
            return ChatColor.AQUA;
        }
        if (pick == 1) {
            return ChatColor.DARK_AQUA;
        }
        if (pick == 2) {
            return ChatColor.DARK_GRAY;
        }
        if (pick == 3) {
            return ChatColor.GOLD;
        }
        if (pick == 4) {
            return ChatColor.GRAY;
        }
        if (pick == 5) {
            return ChatColor.GREEN;
        }
        if (pick == 6) {
            return ChatColor.LIGHT_PURPLE;
        }
        if (pick == 7) {
            return ChatColor.WHITE;
        }
        return null;
    }
    
    public ItemStack FireAxe(final Player player) {
        final ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        itemMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        itemMeta.setDisplayName(ChatColor.BLUE + "F" + ChatColor.RED + "i" + ChatColor.BLUE + "r" + ChatColor.RED + "e" + " " + ChatColor.BLUE + "A" + ChatColor.RED + "x" + ChatColor.BLUE + "e");
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + this.format.format(this.now) + " (" + player.getName() + ")");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
    
    public ItemStack CrateKeyMulti() {
        final ItemStack item = this.CrateKey();
        item.setAmount(new Random().nextInt(5) + 1);
        return item;
    }
    
    public ItemStack GetRandomPrize(final Player player) {
        final ItemStack[] Common = { new ItemStack(Material.STONE, 32), new ItemStack(Material.GRANITE, 32), new ItemStack(Material.GRANITE, 16), new ItemStack(Material.DIORITE, 32), new ItemStack(Material.DIORITE, 16), new ItemStack(Material.WHITE_WOOL, 16), new ItemStack(Material.WHITE_WOOL, 32), new ItemStack(Material.GRASS_BLOCK, 32), new ItemStack(Material.GRASS_BLOCK, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.DIRT, 16), new ItemStack(Material.COAL_ORE, 4), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.APPLE, 4), new ItemStack(Material.APPLE, 8), new ItemStack(Material.STONE, 32), new ItemStack(Material.STONE, 16), new ItemStack(Material.GRANITE, 32), new ItemStack(Material.GRANITE, 16), new ItemStack(Material.DIORITE, 32), new ItemStack(Material.DIORITE, 16), new ItemStack(Material.WHITE_WOOL, 16), new ItemStack(Material.WHITE_WOOL, 32), new ItemStack(Material.GRASS_BLOCK, 32), new ItemStack(Material.GRASS_BLOCK, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.DIRT, 16), new ItemStack(Material.COAL_ORE, 4), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.APPLE, 4), new ItemStack(Material.APPLE, 2), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 2), new ItemStack(Material.EXPERIENCE_BOTTLE, 32), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.ANVIL, 2), new ItemStack(Material.ANVIL, 4), new ItemStack(Material.COOKED_CHICKEN, 8), new ItemStack(Material.COOKED_CHICKEN, 16), new ItemStack(Material.CLAY, 32), new ItemStack(Material.CLAY, 16), new ItemStack(Material.COBBLESTONE, 32), new ItemStack(Material.COBBLESTONE, 16), new ItemStack(Material.CAKE, 1), new ItemStack(Material.HAY_BLOCK, 16), new ItemStack(Material.GRAVEL, 16), new ItemStack(Material.GRAVEL, 32), new ItemStack(Material.COAL_BLOCK, 2), new ItemStack(Material.COAL_BLOCK, 4), new ItemStack(Material.IRON_BOOTS, 1), new ItemStack(Material.IRON_CHESTPLATE, 1), new ItemStack(Material.IRON_HELMET, 1), new ItemStack(Material.IRON_LEGGINGS, 1), new ItemStack(Material.IRON_AXE, 1), new ItemStack(Material.IRON_PICKAXE, 1), new ItemStack(Material.IRON_SHOVEL, 1), new ItemStack(Material.IRON_SWORD, 1), new ItemStack(Material.STONE, 32), new ItemStack(Material.STONE, 16), new ItemStack(Material.GRANITE, 32), new ItemStack(Material.GRANITE, 16), new ItemStack(Material.DIORITE, 32), new ItemStack(Material.DIORITE, 16), new ItemStack(Material.WHITE_WOOL, 16), new ItemStack(Material.WHITE_WOOL, 32), new ItemStack(Material.GRASS_BLOCK, 32), new ItemStack(Material.GRASS_BLOCK, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.DIRT, 16), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 4), new ItemStack(Material.EXPERIENCE_BOTTLE, 32), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.ANVIL, 2), new ItemStack(Material.PACKED_ICE, 32), new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.LAPIS_ORE, 4), new ItemStack(Material.LAPIS_ORE, 8), new ItemStack(Material.LAPIS_ORE, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.IRON_ORE, 8), new ItemStack(Material.GLOWSTONE, 8), new ItemStack(Material.GLOWSTONE, 16), new ItemStack(Material.APPLE, 4), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.COOKED_CHICKEN, 16), new ItemStack(Material.DIAMOND_BOOTS, 1), new ItemStack(Material.DIAMOND_CHESTPLATE, 1), new ItemStack(Material.DIAMOND_LEGGINGS, 1), new ItemStack(Material.DIAMOND_AXE, 1), new ItemStack(Material.DIAMOND_PICKAXE, 1), new ItemStack(Material.DIAMOND_SWORD, 1), new ItemStack(Material.PACKED_ICE, 32), new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.LAPIS_ORE, 4), new ItemStack(Material.LAPIS_ORE, 8), new ItemStack(Material.LAPIS_ORE, 16), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.WHEAT, 32), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.IRON_ORE, 8), new ItemStack(Material.GLOWSTONE, 8), new ItemStack(Material.GLOWSTONE, 16), new ItemStack(Material.APPLE, 4), new ItemStack(Material.APPLE, 2), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.COOKED_CHICKEN, 16), new ItemStack(Material.DIAMOND_BOOTS, 1), new ItemStack(Material.DIAMOND_CHESTPLATE, 1), new ItemStack(Material.DIAMOND_LEGGINGS, 1), new ItemStack(Material.DIAMOND_AXE, 1), new ItemStack(Material.DIAMOND_PICKAXE, 1), new ItemStack(Material.DIAMOND_SWORD, 1), new ItemStack(Material.PACKED_ICE, 32), new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.LAPIS_ORE, 4), new ItemStack(Material.LAPIS_ORE, 8), new ItemStack(Material.LAPIS_ORE, 16), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.WHEAT, 32), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.MILK_BUCKET, 1), new ItemStack(Material.MILK_BUCKET, 2), new ItemStack(Material.COOKIE, 4), new ItemStack(Material.COOKIE, 8), new ItemStack(Material.LEATHER_BOOTS, 1), new ItemStack(Material.LEATHER_CHESTPLATE, 1), new ItemStack(Material.LEATHER_HELMET, 1), new ItemStack(Material.LEATHER_LEGGINGS, 1), new ItemStack(Material.WOODEN_AXE, 1), new ItemStack(Material.WOODEN_PICKAXE, 1), new ItemStack(Material.WOODEN_SHOVEL, 1), new ItemStack(Material.WOODEN_SWORD, 1), new ItemStack(Material.COOKED_BEEF, 2), new ItemStack(Material.COOKED_BEEF, 4), new ItemStack(Material.COOKED_COD, 2), new ItemStack(Material.COOKED_COD, 4), new ItemStack(Material.COOKED_MUTTON, 2), new ItemStack(Material.COOKED_MUTTON, 4), new ItemStack(Material.COOKED_PORKCHOP, 2), new ItemStack(Material.COOKED_PORKCHOP, 4), new ItemStack(Material.COOKED_SALMON, 4) };
        final ItemStack[] Uncommon = { new ItemStack(Material.STONE, 32), new ItemStack(Material.STONE, 16), new ItemStack(Material.GRANITE, 32), new ItemStack(Material.GRANITE, 16), new ItemStack(Material.DIORITE, 32), new ItemStack(Material.DIORITE, 16), new ItemStack(Material.WHITE_WOOL, 16), new ItemStack(Material.WHITE_WOOL, 32), new ItemStack(Material.GRASS_BLOCK, 32), new ItemStack(Material.GRASS_BLOCK, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.DIRT, 16), new ItemStack(Material.COAL_ORE, 4), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.IRON_ORE, 8), new ItemStack(Material.GLOWSTONE, 8), new ItemStack(Material.GLOWSTONE, 16), new ItemStack(Material.APPLE, 4), new ItemStack(Material.APPLE, 8), new ItemStack(Material.STONE, 32), new ItemStack(Material.STONE, 16), new ItemStack(Material.GRANITE, 32), new ItemStack(Material.GRANITE, 16), new ItemStack(Material.DIORITE, 32), new ItemStack(Material.DIORITE, 16), new ItemStack(Material.WHITE_WOOL, 16), new ItemStack(Material.WHITE_WOOL, 32), new ItemStack(Material.GRASS_BLOCK, 32), new ItemStack(Material.GRASS_BLOCK, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.DIRT, 16), new ItemStack(Material.COAL_ORE, 4), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.IRON_ORE, 8), new ItemStack(Material.GLOWSTONE, 8), new ItemStack(Material.GLOWSTONE, 16), new ItemStack(Material.APPLE, 4), new ItemStack(Material.APPLE, 8), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 2), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 4), new ItemStack(Material.EXPERIENCE_BOTTLE, 32), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.ANVIL, 2), new ItemStack(Material.ANVIL, 4), new ItemStack(Material.COOKED_CHICKEN, 8), new ItemStack(Material.COOKED_CHICKEN, 16), new ItemStack(Material.CLAY, 32), new ItemStack(Material.CLAY, 16), new ItemStack(Material.COBBLESTONE, 32), new ItemStack(Material.COBBLESTONE, 16), new ItemStack(Material.CAKE, 1), new ItemStack(Material.HAY_BLOCK, 16), new ItemStack(Material.HAY_BLOCK, 32), new ItemStack(Material.HAY_BLOCK, 16), new ItemStack(Material.EMERALD_BLOCK, 2), new ItemStack(Material.EMERALD_BLOCK, 4), new ItemStack(Material.GRAVEL, 32), new ItemStack(Material.GRAVEL, 16), new ItemStack(Material.COAL_BLOCK, 2), new ItemStack(Material.COAL_BLOCK, 4), new ItemStack(Material.GOLDEN_BOOTS, 1), new ItemStack(Material.GOLDEN_CHESTPLATE, 1), new ItemStack(Material.GOLDEN_HELMET, 1), new ItemStack(Material.GOLDEN_LEGGINGS, 1), new ItemStack(Material.STONE_AXE, 1), new ItemStack(Material.GOLDEN_PICKAXE, 1), new ItemStack(Material.GOLDEN_SHOVEL, 1), new ItemStack(Material.GOLDEN_SWORD, 1), new ItemStack(Material.STONE, 32), new ItemStack(Material.STONE, 16), new ItemStack(Material.GRANITE, 32), new ItemStack(Material.GRANITE, 16), new ItemStack(Material.DIORITE, 32), new ItemStack(Material.DIORITE, 16), new ItemStack(Material.WHITE_WOOL, 16), new ItemStack(Material.WHITE_WOOL, 32), new ItemStack(Material.GRASS_BLOCK, 32), new ItemStack(Material.GRASS_BLOCK, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.DIRT, 16), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 4), new ItemStack(Material.EXPERIENCE_BOTTLE, 8), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.ANVIL, 2), new ItemStack(Material.PACKED_ICE, 32), new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.LAPIS_ORE, 4), new ItemStack(Material.LAPIS_ORE, 8), new ItemStack(Material.LAPIS_ORE, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.IRON_ORE, 8),  new ItemStack(Material.GLOWSTONE, 4), new ItemStack(Material.APPLE, 2), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.COOKED_CHICKEN, 16), new ItemStack(Material.PACKED_ICE, 8), new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.IRON_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.LAPIS_ORE, 2), new ItemStack(Material.LAPIS_ORE, 4), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.WHEAT, 32), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.DIRT, 32), new ItemStack(Material.COAL_ORE, 8), new ItemStack(Material.IRON_ORE, 4), new ItemStack(Material.IRON_ORE, 8), new ItemStack(Material.GLOWSTONE, 8), new ItemStack(Material.APPLE, 4), new ItemStack(Material.APPLE, 2), new ItemStack(Material.EXPERIENCE_BOTTLE, 16), new ItemStack(Material.COOKED_CHICKEN, 16), new ItemStack(Material.DIAMOND_SWORD, 1), new ItemStack(Material.PACKED_ICE, 32), new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.LAPIS_ORE, 4), new ItemStack(Material.LAPIS_ORE, 8), new ItemStack(Material.LAPIS_ORE, 16), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.WHEAT, 32), new ItemStack(Material.WHEAT, 16), new ItemStack(Material.MILK_BUCKET, 1), new ItemStack(Material.MILK_BUCKET, 2), new ItemStack(Material.COOKIE, 4), new ItemStack(Material.COOKIE, 8), new ItemStack(Material.LEATHER_BOOTS, 1), new ItemStack(Material.LEATHER_CHESTPLATE, 1), new ItemStack(Material.LEATHER_HELMET, 1), new ItemStack(Material.LEATHER_LEGGINGS, 1), new ItemStack(Material.WOODEN_AXE, 1), new ItemStack(Material.WOODEN_PICKAXE, 1), new ItemStack(Material.WOODEN_SHOVEL, 1), new ItemStack(Material.WOODEN_SWORD, 1), new ItemStack(Material.COOKED_BEEF, 2), new ItemStack(Material.COOKED_BEEF, 4), new ItemStack(Material.COOKED_COD, 2), new ItemStack(Material.COOKED_COD, 4), new ItemStack(Material.COOKED_MUTTON, 2), new ItemStack(Material.COOKED_MUTTON, 4), new ItemStack(Material.COOKED_PORKCHOP, 2), new ItemStack(Material.COOKED_PORKCHOP, 4), new ItemStack(Material.COOKED_SALMON, 4) };
        final ItemStack[] Rare = { new ItemStack(Material.ELYTRA, 1), new ItemStack(Material.BEACON, 1), new ItemStack(Material.VILLAGER_SPAWN_EGG, 1), new ItemStack(Material.VILLAGER_SPAWN_EGG, 2), new ItemStack(Material.COW_SPAWN_EGG, 1), new ItemStack(Material.COW_SPAWN_EGG, 2), new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1), new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 2), new ItemStack(Material.WOLF_SPAWN_EGG, 1), new ItemStack(Material.SHEEP_SPAWN_EGG, 1), new ItemStack(Material.OCELOT_SPAWN_EGG, 1), this.Armor("", Material.DIAMOND_HELMET, 4, 3, 3, 4, 4, player), this.Armor("", Material.DIAMOND_CHESTPLATE, 4, 3, 3, 4, 4, player), this.Armor("", Material.DIAMOND_LEGGINGS, 4, 3, 3, 4, 4, player), this.Armor("", Material.DIAMOND_BOOTS, 4, 3, 3, 4, 4, player), this.FireAxe(player) };
        final int chance = new Random().nextInt(100);
        if (chance >= 97) {
            return Rare[new Random().nextInt(Rare.length)];
        }
        if (chance >= 75) {
            return Uncommon[new Random().nextInt(Uncommon.length)];
        }
        if (chance >= 25) {
            return Common[new Random().nextInt(Common.length)];
        }
        return Common[new Random().nextInt(Common.length)];
    }
}