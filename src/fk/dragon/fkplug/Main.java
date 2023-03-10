package fk.dragon.fkplug;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fk.dragon.fkplug.commands.DiscordAllCommand;
import fk.dragon.fkplug.commands.DiscordCommand;
import fk.dragon.fkplug.commands.GMCommand;
import fk.dragon.fkplug.commands.HealCommand;
import fk.dragon.fkplug.commands.MumbleAllCommand;
import fk.dragon.fkplug.commands.MumbleCommand;
import fk.dragon.fkplug.commands.PluginCommand;
import fk.dragon.fkplug.commands.SpawnCommand;
import fk.dragon.fkplug.commands.TradCommand;
import fk.dragon.fkplug.events.NoDrowningDamage;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		System.out.println("Salut la console !");
		getCommand("trad").setExecutor(new TradCommand());
		getCommand("discord").setExecutor(new DiscordCommand());
		getCommand("discordall").setExecutor(new DiscordAllCommand());
		getCommand("mumble").setExecutor(new MumbleCommand());
		getCommand("mumbleall").setExecutor(new MumbleAllCommand());
		//getCommand("fkrl").setExecutor(new ReloadCommand(this));
		getCommand("gm").setExecutor(new GMCommand());
		getCommand("heal").setExecutor(new HealCommand());

		
	    PluginCommand pluginCommand = new PluginCommand();
	    getCommand("plugin").setExecutor(pluginCommand);
	    Bukkit.getServer().getPluginManager().registerEvents(this, this);
	    
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new NoDrowningDamage(), this);
        
		getCommand("lobby").setExecutor(new SpawnCommand(this));
		getCommand("hub").setExecutor(new SpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    
	}

	   // La m??thode onPlayerJoin doit ??tre d??finie comme une m??thode de type void
	/**
	 * @param event
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setJoinMessage("??8[??2+??8] ??7" + event.getPlayer().getName() + " ??8(??2" + Bukkit.getServer().getOnlinePlayers().size() + "??6/??4" + Bukkit.getServer().getMaxPlayers() + "??8)");
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());
        player.setSaturation(20);
        player.setFoodLevel(20);
        Location spawnLocation = new Location(player.getWorld(), 0.5, 54.5, 0.5);
        spawnLocation.setYaw(270);
        player.teleport(spawnLocation);
        
	
        
        if (player.isOp()) {
            // Ajouter l'item dans le slot d'inventaire 7
            ItemStack item = new ItemStack(Material.MAGMA_CREAM, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("??6Cosm??tiques");
            meta.addEnchant(Enchantment.DEPTH_STRIDER, 0, true);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            player.getInventory().setItem(6, item);
            
            player.updateInventory();
        }
	
	
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (lockinv) {
			event.setCancelled(true);
		}
	    /*Inventory clickedInventory = event.getClickedInventory();
	    if (clickedInventory != null && event.getWhoClicked() instanceof Player) {
	        Player player = (Player) event.getWhoClicked();
	        InventoryType inventoryType = clickedInventory.getType();

	        // V??rifier si le joueur a cliqu?? dans son propre inventaire
	        if (inventoryType == InventoryType.CRAFTING || inventoryType == InventoryType.PLAYER) {
	            // R??cup??re l'item sur lequel le joueur a cliqu??
	            ItemStack clickedItem = event.getCurrentItem();
	            if (clickedItem != null) {
	                String itemName = clickedItem.getItemMeta().getDisplayName();
	                if (itemName.equals("??6Cosm??tiques")) {
	                    if (event.getSlot() == 6 && event.getAction() == InventoryAction.SWAP_WITH_CURSOR ) {
	                         event.setCancelled(true); // Annuler le d??placement de l'item du slot 7
	                    } else {
	                         event.setCancelled(true); // Annuler le clic du joueur
	                    }
	                
	                }
	            }
	        }
	    }*/
	}



	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
	    ItemStack item = event.getItemDrop().getItemStack();
	    if (item != null && item.getType() == Material.MAGMA_CREAM && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("??6Cosm??tiques")) {
	        event.setCancelled(true); // Annuler la chute de l'item
	    }
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setQuitMessage("??8[??4-??8] ??7" + event.getPlayer().getName() + " ??8(??2" + (Bukkit.getServer().getOnlinePlayers().size() -1) + "??6/??4" + Bukkit.getServer().getMaxPlayers() + "??8)");
	   }
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
	    Player player = event.getPlayer();

	    // V??rifier si l'interaction est un clic droit sur l'air ou sur un bloc
	    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	        // V??rifier si l'item est dans la main du joueur
	        ItemStack item = player.getInventory().getItemInHand();
	        if (item != null && item.getType() == Material.MAGMA_CREAM && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("??6Cosm??tiques")) {
	            // Ex??cuter la commande pour le joueur
	            player.performCommand("ultracosmetics menu effects");
	        }
	    }
	}

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        int y = location.getBlockY();
        if (y < -40 && teleportEnabled) {
            Location destination = new Location(player.getWorld(), 0.5, 54.5, 0.5);
            destination.setYaw(270);
            player.teleport(destination);
            player.setFallDistance(0);
            
        }
    }

    private boolean teleportEnabled = true;
    private boolean lockinv = false;


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("toggleTeleport")) {
            teleportEnabled = !teleportEnabled;
            if (teleportEnabled) {
                sender.sendMessage("??6[??3FK??6]??8 ?? ??7Height Limite ??2activ??e");
            } else {
                sender.sendMessage("??6[??3FK??6]??8 ?? ??7Height Limite ??4d??sactiv??e");
            }
            return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("lockinv")) {
            lockinv = !lockinv;
            if (lockinv) {
                sender.sendMessage("??6[??3FK??6]??8 ?? ??7Verrouillage d'inventaire ??2activ??e");
            } else {
                sender.sendMessage("??6[??3FK??6]??8 ?? ??7Verrouillage d'inventaire ??4d??sactiv??e");
            }
            return true;
        }
        return false;
    }
    
    


	@Override
	public void onDisable() {
		System.out.println("Au revoir la console !");
	}
}
