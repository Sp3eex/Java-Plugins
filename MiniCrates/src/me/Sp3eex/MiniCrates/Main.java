package me.Sp3eex.MiniCrates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener
{
    public static Economy economy;
    Map<String, Integer> runnableMap;
    Items it;
    
    static {
        Main.economy = null;
    }
    
    public Main() {
        this.runnableMap = new HashMap<String, Integer>();
        this.it = new Items();
    }
    
    @Override
	public void onEnable() {
        this.saveDefaultConfig();
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(this, this);
        this.getCommand("crate").setExecutor(new Commands(this));
        this.setupEconomy();
        
        if (Main.this.GetCrates().size() == 0) {
        	getServer().getLogger().info("No crates are specified in the config.yml file.");
            return;
        }
        
        final BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (final String crate : Main.this.GetCrates()) {
                    final int loc_x = Main.this.getConfig().getInt("crates." + crate + ".x");
                    final int loc_y = Main.this.getConfig().getInt("crates." + crate + ".y");
                    final int loc_z = Main.this.getConfig().getInt("crates." + crate + ".z");
                    final String loc_world = Main.this.getConfig().getString("crates." + crate + ".world");
                    final Location l_in = new Location(Bukkit.getWorld(loc_world), loc_x, loc_y, loc_z);
                    final Location la = new Location(Bukkit.getWorld(loc_world), loc_x + new Random().nextDouble(), loc_y + new Random().nextDouble(), loc_z + new Random().nextDouble());
                    final Location la2 = new Location(Bukkit.getWorld(loc_world), loc_x + new Random().nextDouble(), loc_y + new Random().nextDouble(), loc_z + new Random().nextDouble());
                    final Location la3 = new Location(Bukkit.getWorld(loc_world), loc_x + new Random().nextDouble(), loc_y + new Random().nextDouble(), loc_z + new Random().nextDouble());
                    final Location la4 = new Location(Bukkit.getWorld(loc_world), loc_x + new Random().nextDouble(), loc_y + new Random().nextDouble(), loc_z + new Random().nextDouble());
                    final Location la5 = new Location(Bukkit.getWorld(loc_world), loc_x + new Random().nextDouble(), loc_y + new Random().nextDouble(), loc_z + new Random().nextDouble());
                    Bukkit.getWorld(loc_world).spawnParticle(Particle.SPELL_WITCH, la, 1);
                    Bukkit.getWorld(loc_world).spawnParticle(Particle.SPELL_WITCH, la2, 1);
                    Bukkit.getWorld(loc_world).spawnParticle(Particle.SPELL_WITCH, la3, 1);
                    Bukkit.getWorld(loc_world).spawnParticle(Particle.SPELL_WITCH, la4, 1);
                    Bukkit.getWorld(loc_world).spawnParticle(Particle.SPELL_WITCH, la5, 1);
                    Bukkit.getWorld(loc_world).spawnParticle(Particle.SPELL_INSTANT, la, 1);
                    Bukkit.getWorld(loc_world).playEffect(l_in, Effect.MOBSPAWNER_FLAMES, 10);
                }
            }
        }, 0L, 12L);
    }
    
    private boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            Main.economy = economyProvider.getProvider();
        }
        return Main.economy != null;
    }
    
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) throws Exception {
        try {
            final Inventory clickedInv = event.getClickedInventory();
            if (clickedInv.getItem(0).getType() == Material.LIGHT_BLUE_STAINED_GLASS_PANE && clickedInv.getItem(0).getItemMeta().hasEnchants()) {
                event.setCancelled(true);
            }
        }
        catch (Exception e) {}
    }
    
    public void CreateCrate(final Player player, final Location location) throws IOException {
        final int createID = new Random().nextInt(10000) + 10000;
        this.getConfig().set("crates." + createID + ".world", location.getWorld().getName());
        this.getConfig().set("crates." + createID + ".x", location.getBlockX());
        this.getConfig().set("crates." + createID + ".y", location.getBlockY());
        this.getConfig().set("crates." + createID + ".z", location.getBlockZ());
        this.saveConfig();
        this.reloadConfig();
    }
    
    public List<String> GetCrates() {
        final List<String> crates = new ArrayList<String>();
        final ConfigurationSection configsec = this.getConfig().getConfigurationSection("crates");
        if (configsec == null) {
            return crates;
        }
        for (final String crate : configsec.getKeys(false)) {
            crates.add(crate);
        }
        return crates;
    }
    
    public boolean isChestValid(final Location loc) {
        for (final String crateloc : this.GetCrates()) {
            final String world = this.getConfig().getString("crates." + crateloc + ".world");
            final int x = this.getConfig().getInt("crates." + crateloc + ".x");
            final int y = this.getConfig().getInt("crates." + crateloc + ".y");
            final int z = this.getConfig().getInt("crates." + crateloc + ".z");
            if (loc.getWorld().getName().equalsIgnoreCase(world) && loc.getBlockX() == x && loc.getBlockY() == y && loc.getBlockZ() == z) {
                return true;
            }
        }
        return false;
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        player.setInvulnerable(false);
        this.cancelRunnable(player);
    }
    
    public void OpenCrate(final Plugin plugin, final Player player) {
        if (!this.runnableMap.containsKey(player.getName())) {
            final Inventory invWithPrizes = this.it.GetFullPrizeInventory();
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            player.openInventory(invWithPrizes);
            player.setInvulnerable(true);
            final int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                int counter = 0;
                boolean m = false;
                boolean a = false;
                boolean b = false;
                boolean c = false;
                boolean d = false;
                boolean e = false;
                boolean f = false;
                @Override
                public void run() {
                    ++this.counter;
                    if (this.counter <= 49) {
                        player.playSound(player.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.2f, 0.4f);
                    }
                    if (this.counter < 7) {
                        invWithPrizes.setItem(25, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 7 && this.counter <= 14) {
                        invWithPrizes.setItem(24, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 14 && this.counter <= 21) {
                        invWithPrizes.setItem(23, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 21 && this.counter <= 28) {
                        invWithPrizes.setItem(22, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 28 && this.counter <= 35) {
                        invWithPrizes.setItem(21, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 35 && this.counter <= 42) {
                        invWithPrizes.setItem(20, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 42 && this.counter <= 49) {
                        invWithPrizes.setItem(19, Main.this.it.GetRandomPrize(player));
                    }
                    if (this.counter > 49 && this.counter <= 70) {
                        if (this.counter == 55) {
                            player.playSound(player.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.2f, 0.4f);
                            if (new Random().nextInt(100) > 80) {
                                invWithPrizes.setItem(25, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.m = true;
                            }
                            if (new Random().nextInt(100) > 80) {
                                invWithPrizes.setItem(24, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.a = true;
                            }
                            if (new Random().nextInt(100) > 80) {
                                invWithPrizes.setItem(23, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.b = true;
                            }
                            if (new Random().nextInt(100) > 80) {
                                invWithPrizes.setItem(22, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.c = true;
                            }
                            if (new Random().nextInt(100) > 80) {
                                invWithPrizes.setItem(21, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.d = true;
                            }
                            if (new Random().nextInt(100) > 60) {
                                invWithPrizes.setItem(20, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.e = true;
                            }
                            if (new Random().nextInt(100) > 60) {
                                invWithPrizes.setItem(19, new ItemStack(Material.RED_STAINED_GLASS_PANE));
                                this.f = true;
                            }
                        }
                        if (this.counter == 60) {
                            player.playSound(player.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.2f, 0.4f);
                            if (!this.m || !this.a || !this.b || !this.c || !this.d || !this.e || !this.f) {
                                if (!this.m && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(25, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.m = true;
                                }
                                if (!this.a && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(24, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.a = true;
                                }
                                if (!this.b && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(23, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.b = true;
                                }
                                if (!this.c && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(22, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.c = true;
                                }
                                if (!this.d && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(21, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.d = true;
                                }
                                if (!this.e && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(20, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.e = true;
                                }
                                if (!this.f && new Random().nextInt(100) > 65) {
                                    invWithPrizes.setItem(19, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                    this.f = true;
                                }
                            }
                        }
                        if (this.counter == 62) {
                            Main.this.Fire(player);
                        }
                        if (this.counter == 65) {
                            player.playSound(player.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.2f, 0.4f);
                            if (!this.m || !this.a || !this.b || !this.c || !this.d || !this.e || !this.f) {
                                if (!this.m && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(25, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.m = true;
                                }
                                if (!this.a && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(24, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.a = true;
                                }
                                if (!this.b && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(23, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.b = true;
                                }
                                if (!this.c && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(22, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.c = true;
                                }
                                if (!this.d && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(21, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.d = true;
                                }
                                if (!this.e && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(20, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.e = true;
                                }
                                if (!this.f && new Random().nextInt(100) > 45) {
                                    invWithPrizes.setItem(19, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                                    this.f = true;
                                }
                            }
                        }
                        if (this.counter == 67) {
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 0.4f);
                            Main.this.PlayCustomShapedEffect(player, Particle.HEART, false);
                            if (!this.m || !this.a || !this.b || !this.c || !this.d || !this.e || !this.f) {
                                if (!this.m) {
                                    final ItemStack item = invWithPrizes.getItem(25);
                                    Main.this.CheckItem(player, item);
                                }
                                if (!this.a) {
                                    final ItemStack item = invWithPrizes.getItem(24);
                                    Main.this.CheckItem(player, item);
                                }
                                if (!this.b) {
                                    final ItemStack item = invWithPrizes.getItem(23);
                                    Main.this.CheckItem(player, item);
                                }
                                if (!this.c) {
                                    final ItemStack item = invWithPrizes.getItem(22);
                                    Main.this.CheckItem(player, item);
                                }
                                if (!this.d) {
                                    final ItemStack item = invWithPrizes.getItem(21);
                                    Main.this.CheckItem(player, item);
                                }
                                if (!this.e) {
                                    final ItemStack item = invWithPrizes.getItem(20);
                                    Main.this.CheckItem(player, item);
                                }
                                if (!this.f) {
                                    final ItemStack item = invWithPrizes.getItem(19);
                                    Main.this.CheckItem(player, item);
                                }
                            }
                            else {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
                            }
                        }
                        if (this.counter == 70) {
                            Main.this.cancelRunnable(player);
                            player.closeInventory();
                            player.setInvulnerable(false);
                        }
                    }
                }
            }, 2L, 2L);
            this.runnableMap.put(player.getName(), taskID);
        }
    }
    
    String removeColors(final String input) {
        final String output = input.replace("&0", "").replace("&1", "").replace("&2", "").replace("&3", "").replace("&4", "").replace("&5", "").replace("&6", "").replace("&7", "").replace("&8", "").replace("&9", "").replace("&a", "").replace("&b", "").replace("&c", "").replace("&d", "").replace("&e", "").replace("&f", "").replace("&k", "").replace("&l", "").replace("&m", "").replace("&n", "").replace("&o", "").replace("&r", "");
        return output;
    }
    
    void PlaySpecialEffect(final Player player, final Particle particle) {
        final Location loc = player.getLocation();
        for (double y = 0.0; y <= 25.0; y += 0.4) {
            final double x = 1.0 * Math.cos(y);
            final double z = 1.0 * Math.sin(y);
            player.getWorld().spawnParticle(particle, new Location(player.getWorld(), (float)(loc.getX() + x), (float)(loc.getY() + y), (float)(loc.getZ() + z)), 1);
        }
    }
    
    void PlayCustomShapedEffect(final Player player, final Particle particle, final boolean v) {
        for (int i = 0; i <= 8; i += ((!v && i == 3) ? 2 : 1)) {
            player.getWorld().spawnParticle(particle, player.getLocation().add(0.0, 5.0, 0.0), i);
            player.getLocation().getWorld().playEffect(player.getLocation().add(0.0, 5.0, 0.0), Effect.SMOKE, i);
        }
    }
    
    public void CheckItem(final Player player, final ItemStack item) {
        if (item.getType() == Material.PAPER || item.getType() == Material.NAME_TAG) {
            if (item.getType() == Material.PAPER && item.getItemMeta().hasEnchants()) {
                final String removed_colors = this.removeColors(item.getItemMeta().getDisplayName().replace("$", "").replace("&a", ""));
                final int sp_d = Integer.parseInt(ChatColor.stripColor(removed_colors));
                Main.economy.depositPlayer(player, sp_d);
                Bukkit.broadcastMessage(ChatColor.BLUE + player.getName() + " won $" + sp_d + " money from the crate.");
                this.PlaySpecialEffect(player, Particle.DRIP_WATER);
            }
            if (item.getType() == Material.NAME_TAG && item.getItemMeta().hasEnchants()) {
                Bukkit.broadcastMessage(ChatColor.BLUE + player.getName() + " won a permission key from the crate.");
                this.PlaySpecialEffect(player, Particle.DRIP_WATER);
            }
        }
        else if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(new ItemStack[] { item });
        }
        else {
            player.getWorld().dropItem(player.getLocation(), item);
        }
        if (item.getType() == Material.BEACON) {
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " won a beacon from the crate.");
            this.PlaySpecialEffect(player, Particle.DRIP_WATER);
        }
        if (item.getType() == Material.VILLAGER_SPAWN_EGG) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " won " + item.getAmount() + " Villager spawn egg(s) from the crate.");
            this.PlaySpecialEffect(player, Particle.VILLAGER_HAPPY);
        }
        if (item.getType() == Material.WOLF_SPAWN_EGG) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " won " + item.getAmount() + " Wolf spawn egg(s) from the crate.");
            this.PlaySpecialEffect(player, Particle.HEART);
        }
        if (item.getType() == Material.SHEEP_SPAWN_EGG) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " won " + item.getAmount() + " Sheep spawn egg(s) from the crate.");
            this.PlaySpecialEffect(player, Particle.DRIP_WATER);
        }
        if (item.getType() == Material.MOOSHROOM_SPAWN_EGG) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " won " + item.getAmount() + " Mushroom spawn egg(s) from the crate.");
            this.PlaySpecialEffect(player, Particle.ENCHANTMENT_TABLE);
        }
        if (item.getType() == Material.COW_SPAWN_EGG) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " won " + item.getAmount() + " Cow spawn egg(s) from the crate.");
            this.PlaySpecialEffect(player, Particle.SPELL);
        }
        if (item.getType() == Material.OCELOT_SPAWN_EGG) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " won " + item.getAmount() + " Ocelot spawn egg(s) from the crate.");
            this.PlaySpecialEffect(player, Particle.SPELL_MOB);
        }
        if (item.getType() == Material.STICK && item.getItemMeta().hasEnchants()) {
            Bukkit.broadcastMessage(ChatColor.BLUE + player.getName() + " won a knockback stick from the crate.");
            this.PlaySpecialEffect(player, Particle.FLAME);
        }
        if (item.getType() == Material.DIAMOND_AXE && item.getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT)) {
            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " won a fire axe from the crate.");
            this.PlaySpecialEffect(player, Particle.VILLAGER_ANGRY);
        }
        if (item.getType() == Material.ELYTRA) {
            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " won elytra from the crate.");
            this.PlaySpecialEffect(player, Particle.NOTE);
        }
        if (item.getItemMeta().hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)) {
            if (item.getType() == Material.DIAMOND_HELMET) {
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + player.getName() + " won OP protection 4 helmet from the crate.");
            }
            if (item.getType() == Material.DIAMOND_CHESTPLATE) {
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + player.getName() + " won OP protection 4 chestplate from the crate.");
            }
            if (item.getType() == Material.DIAMOND_LEGGINGS) {
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + player.getName() + " won OP protection 4 leggings from the crate.");
            }
            if (item.getType() == Material.DIAMOND_BOOTS) {
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + player.getName() + " won OP protection 4 boots from the crate.");
            }
            this.PlaySpecialEffect(player, Particle.VILLAGER_HAPPY);
            this.PlaySpecialEffect(player, Particle.WATER_DROP);
        }
    }
    
    public void Fire(final Player player) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Firework firework = (Firework) player.getWorld().spawn(player.getLocation(), (Class) Firework.class);
        final FireworkEffect effect = FireworkEffect.builder().with(FireworkUtils.RandomFEffect()).withColor(new Color[] { FireworkUtils.RandomFColor(), FireworkUtils.RandomFColor(), FireworkUtils.RandomFColor() }).withFade(FireworkUtils.RandomFColor()).trail(new Random().nextBoolean()).build();
        final FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(effect);
        firework.setFireworkMeta(meta);
    }
    
    public void cancelRunnable(final Player player) {
        if (this.runnableMap.containsKey(player.getName())) {
            final int taskToCencel = this.runnableMap.get(player.getName());
            Bukkit.getScheduler().cancelTask(taskToCencel);
            this.runnableMap.remove(player.getName());
            player.setInvulnerable(false);
        }
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            final Player player = event.getPlayer();
            final ItemStack playerItem = player.getInventory().getItemInMainHand();
            final Location location = event.getClickedBlock().getLocation();
            if (this.isChestValid(location)) {
                if (playerItem.getType() == Material.BLAZE_ROD && playerItem.getItemMeta().hasEnchants()) {
                    this.OpenCrate(this, player);
                    event.setCancelled(true);
                }
                else {
                    player.setVelocity(player.getLocation().getDirection().multiply(-1.3).setY(0.4));
                    event.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Location location = event.getBlock().getLocation();
        final Player player = event.getPlayer();
        if (this.isChestValid(location)) {
            if (player.isOp()) {
                if (!player.isSneaking()) {
                    player.sendMessage(ChatColor.RED + "You must be sneaking to break crates.");
                    event.setCancelled(true);
                }
                else {
                    this.getConfig().set("0.x", 0);
                    this.getConfig().set("0.y", 0);
                    this.getConfig().set("0.z", 0);
                    this.saveConfig();
                    this.reloadConfig();
                    player.sendMessage(ChatColor.GREEN + "Crate removed on location: " + ChatColor.YELLOW + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
                }
            }
            else {
                event.setCancelled(true);
            }
        }
        else {
            final int chancebreak = new Random().nextInt(1000);
            if (chancebreak <= 9) {
                player.getInventory().addItem(new ItemStack[] { this.it.CrateKey() });
            }
            //if (chancebreak <= 750) {
            //    final int parsed = new Random().nextInt(90);
            //    final double fullparsed = Double.parseDouble("0." + parsed);
            //    Main.economy.depositPlayer(player, fullparsed);
            //}
        }
    }
}