package fk.dragon.fkplug;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

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
import fk.dragon.fkplug.events.NoFoodPb;

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
        pm.registerEvents(new NoFoodPb(), this);

		getCommand("lobby").setExecutor(new SpawnCommand(this));

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    
	}

	   // La méthode onPlayerJoin doit être définie comme une méthode de type void
	/**
	 * @param event
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setJoinMessage("§8[§2+§8] §7" + event.getPlayer().getName() + " §8(§2" + Bukkit.getServer().getOnlinePlayers().size() + "§6/§4" + Bukkit.getServer().getMaxPlayers() + "§8)");
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());
        player.setSaturation(20);
        player.setFoodLevel(20);
        Location spawnLocation = new Location(player.getWorld(), 0.5, 54.5, 0.5);
        spawnLocation.setYaw(90);
        player.teleport(spawnLocation);
        player.setGameMode(GameMode.ADVENTURE);
        
        ItemStack boussole = new ItemStack(Material.COMPASS, 1);
        ItemMeta metaC = boussole.getItemMeta();
        metaC.setDisplayName("§6Menu");
        metaC.addEnchant(Enchantment.DEPTH_STRIDER, 0,true);
        metaC.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        boussole.setItemMeta(metaC);
        player.getInventory().setItem(4, boussole);
        
        if (player.isOp()) {
            // Ajouter l'item dans le slot d'inventaire 7
            ItemStack item = new ItemStack(Material.MAGMA_CREAM, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6Cosmétiques");
            meta.addEnchant(Enchantment.DEPTH_STRIDER, 0, true);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            player.getInventory().setItem(6, item);
            
        }
        player.updateInventory();

	
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
	    Player player = event.getPlayer();

	    // Vérifier si l'interaction est un clic droit sur l'air ou sur un bloc
	    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	        // Vérifier si l'item est dans la main du joueur
	        ItemStack item = player.getInventory().getItemInHand();
	        if (item != null && item.getType() == Material.MAGMA_CREAM && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("§6Cosmétiques")) {
	            // Exécuter la commande pour le joueur
	            player.performCommand("ultracosmetics menu effects");
	        }
	    }
	    
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
        	ItemStack itemC = player.getInventory().getItemInHand();
        	if(itemC != null && itemC.getType() == Material.COMPASS && itemC.hasItemMeta() && itemC.getItemMeta().getDisplayName().equals("§6Menu")) {
                
                // Ouvrir l'inventaire pour le joueur
                Inventory inventory = Bukkit.createInventory(null, 9*5, "§6Menu");

                int[] glass_jaune = {3,5,11,15,28,34,38,42};

                for (int slot : glass_jaune) {
                    ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
                    ItemMeta glassMeta = glassPane.getItemMeta();
                    glassMeta.setDisplayName(" ");
                    glassPane.setItemMeta(glassMeta);
                    inventory.setItem(slot, glassPane);                
                }
                
                int[] glass_orange = {1,2,6,7,9,10,16,17,27,35,37,43};

                for (int slot : glass_orange) {                    
                    ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
                    ItemMeta glassMeta = glassPane.getItemMeta();
                    glassMeta.setDisplayName(" ");
                    glassPane.setItemMeta(glassMeta);
                    inventory.setItem(slot, glassPane);
                }
                
                int[] glass_rouge = {0,8,36,44};

                for (int slot : glass_rouge) {
                    ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                    ItemMeta glassMeta = glassPane.getItemMeta();
                    glassMeta.setDisplayName(" ");
                    glassPane.setItemMeta(glassMeta);
                    inventory.setItem(slot, glassPane);                
                }
                //UHC
                ItemStack UHC = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
                ItemMeta UHCm = UHC.getItemMeta();
                UHCm.setDisplayName("§6§o§lUHC");
                List<String> loreUHC = new ArrayList<>();
                loreUHC.add("§7§oCliquez ici");
                loreUHC.add("§7§opour être téléporté au serveur §6§oUHC§8§o...");
                UHCm.setLore(loreUHC);
                UHC.setItemMeta(UHCm);
                inventory.setItem(22, UHC);
                
                
                //PVP
                ItemStack Practice = new ItemStack(Material.DIAMOND_SWORD, 1);
                ItemMeta PraM = Practice.getItemMeta();
                PraM.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
                PraM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
                PraM.setDisplayName("§b§o§lPractice");
                List<String> lorePVP = new ArrayList<>();
                lorePVP.add("§7§oCliquez ici");
                lorePVP.add("§7§opour être téléporté au §b§oPractice§8§o...");
                PraM.setLore(lorePVP);
                Practice.setItemMeta(PraM);
                inventory.setItem(24, Practice);
                
                
                //Survie
                ItemStack Survie = new ItemStack(Material.GRASS, 1);
                ItemMeta Sm = Survie.getItemMeta();
                Sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
                Sm.setDisplayName("§2§o§lSurvie");
                List<String> loreS = new ArrayList<>();
                loreS.add("§7§oCliquez ici");
                loreS.add("§7§opour être téléporté au serveur §2§oSurvie§8§o...");
                Sm.setLore(loreS);
                Survie.setItemMeta(Sm);
                inventory.setItem(20, Survie);                
                
                
                //Mon Profil
                String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWU2MmEzMTdmMmU4ZjM0OWEyN2UyOTZjZTIyNWI5ZThiMTI3ZDg4YmU2MWFhZWJmMTY2MDRiZmEyYWQ4MTMwOCJ9fX0";
                GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                profile.getProperties().put("textures", new Property("textures", texture));

                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                meta.setDisplayName("§3Mon profil");
                meta.setOwner("pseudo");
                Field profileField = null;
                try {
                    profileField = meta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                    profileField.set(meta, profile);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                }
                skull.setItemMeta(meta);

                inventory.setItem(41, skull);
                
                //Site web
                String texture2 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzE1NmUzYzJlMjVhNzljZDk4YTBlMTM3MjQzYzVmZjVkMDE3OGNkYzAwZmM3M2QyZGIzNDc1NTdmOTFjY2NiNSJ9fX0";
                GameProfile profile2 = new GameProfile(UUID.randomUUID(), null);
                profile2.getProperties().put("textures", new Property("textures", texture2));

                ItemStack skull2 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta meta2 = (SkullMeta) skull2.getItemMeta();
                meta2.setDisplayName("§dSite web");
                meta2.setOwner("pseudo");
                Field profileField2 = null;
                try {
                    profileField2 = meta2.getClass().getDeclaredField("profile");
                    profileField2.setAccessible(true);
                    profileField2.set(meta2, profile2);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e2) {
                    e2.printStackTrace();
                }
                skull2.setItemMeta(meta2);

                inventory.setItem(18, skull2);
                
                //Discord
                String texture3 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmYzNzVlZTMxNWJlNDY1NWNjNmQ2OGE1MjkxZWUzN2U3YWVjMzA3MWJlNzJkMTAwMTQxZGUyNWIwZDE4OWIwNCJ9fX0";
                GameProfile profile3 = new GameProfile(UUID.randomUUID(), null);
                profile3.getProperties().put("textures", new Property("textures", texture3));

                ItemStack skull3 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta meta3 = (SkullMeta) skull3.getItemMeta();
                meta3.setDisplayName("§9Discord");
                meta3.setOwner("pseudo");
                Field profileField3 = null;
                try {
                    profileField3 = meta3.getClass().getDeclaredField("profile");
                    profileField3.setAccessible(true);
                    profileField3.set(meta3, profile3);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e3) {
                    e3.printStackTrace();
                }
                skull3.setItemMeta(meta3);

                inventory.setItem(39, skull3);
                
                //Moddé
                String texture4 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjYwNWE1MGRkNTllNTIyNzcwMWE1MTk3MGFiMDQ3OWVmNTdmZGFkOTA5OTA3MzUzOGZhNzEzYjI2Y2NlOWZjMyJ9fX0";
                GameProfile profile4 = new GameProfile(UUID.randomUUID(), null);
                profile4.getProperties().put("textures", new Property("textures", texture4));

                ItemStack skull4 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta meta4 = (SkullMeta) skull4.getItemMeta();
                meta4.setDisplayName("§6Moddé");
                meta4.setOwner("pseudo");
                Field profileField4 = null;
                try {
                    profileField4 = meta4.getClass().getDeclaredField("profile");
                    profileField4.setAccessible(true);
                    profileField4.set(meta4, profile4);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e4) {
                    e4.printStackTrace();
                }
                skull4.setItemMeta(meta4);

                inventory.setItem(26, skull4);
                
                //MOI
                String texture5 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjYwNWE1MGRkNTllNTIyNzcwMWE1MTk3MGFiMDQ3OWVmNTdmZGFkOTA5OTA3MzUzOGZhNzEzYjI2Y2NlOWZjMyJ9fX0";
                GameProfile profile5 = new GameProfile(UUID.randomUUID(), null);
                profile5.getProperties().put("textures", new Property("textures", texture5));

                ItemStack skull5 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                SkullMeta meta5 = (SkullMeta) skull5.getItemMeta();
                meta5.setDisplayName("§eDev par §6Crokmoo");
                meta5.setOwner("pseudo");
                Field profileField5 = null;
                try {
                    profileField5 = meta5.getClass().getDeclaredField("profile");
                    profileField5.setAccessible(true);
                    profileField5.set(meta5, profile5);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e5) {
                    e5.printStackTrace();
                }
                skull5.setItemMeta(meta5);

                inventory.setItem(4, skull5);
                
                
                
                event.getPlayer().openInventory(inventory);
        	}
        	        	
        }

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (lockinv) {
			Player p = (Player) event.getWhoClicked();
			if (p.getGameMode() == GameMode.ADVENTURE) {
				event.setCancelled(true);
			}
		}

		
		
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        Inventory clickedInventory = event.getClickedInventory();
        InventoryType inventoryType = clickedInventory.getType();

        if (inventoryType != InventoryType.CRAFTING && inventoryType != InventoryType.PLAYER) {
	        ItemMeta meta = clickedItem.getItemMeta();
	
			if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§9Discord")) {        	
				player.performCommand("discord");
			}
			
			if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§dSite web")) {        	
				player.sendMessage("");
				player.sendMessage("§6[§3FK§6]§8 » §7Vous pouvez retrouver notre site web via ce lien : §dfkteam.online§7.");
				player.sendMessage("");
			
			}
			
			if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§6Moddé")) {        	
				player.sendMessage("");
				player.sendMessage("§6[§3FK§6]§8 » §7Nous avons aussi un serveur §5Moddé§7 ! Vous pouvez télécharger le Modpack via ce lien : §6https://www.curseforge.com/minecraft/modpacks/dragonniums-pack§7.");
				player.sendMessage("");

			}
			
			if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§3Mon profil")) {      
				player.sendMessage("");
				player.sendMessage("§6[§3FK§6]§8 » §7Vous pouvez accéder à votre profil sur notre site web via ce lien §8:§3 https://fkteam.online/profil/" + player.getName() + "§7.");
				player.sendMessage("");

			}
			
	    	if (clickedItem != null && clickedItem.getType() == Material.DIAMOND_SWORD) {
	            Location PVP = new Location(player.getWorld(), 0, 52.5, 41.5);
	            PVP.setYaw(360);
	            player.teleport(PVP);
	    	}    
	    	
	    	if (clickedItem != null && clickedItem.getType() == Material.GRASS) {
	            ByteArrayOutputStream b = new ByteArrayOutputStream();
	            DataOutputStream out = new DataOutputStream(b);
	            try {
	                out.writeUTF("Connect");
	                out.writeUTF("XTREM");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	    	}
	    	
	    	if (clickedItem != null && clickedItem.getType() == Material.GOLDEN_APPLE) {
	            ByteArrayOutputStream b = new ByteArrayOutputStream();
	            DataOutputStream out = new DataOutputStream(b);
	            try {
	                out.writeUTF("Connect");
	                out.writeUTF("UHC");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	    	}
	            // Fermez l'inventaire
	        	
	            
	            
	            // Empêchez la propagation de l'événement
	            if (player.getGameMode() == GameMode.ADVENTURE) {
	            	if (clickedItem != null && clickedItem.getType() != Material.STAINED_GLASS_PANE) {
	            	player.closeInventory();
		            event.setCancelled(true);
	            }
	            }
	      }
	}


	
	
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
	        if (event.getEntity() instanceof Player) {
		        Player player = (Player) event.getEntity();

	        	if (player.getGameMode() == GameMode.ADVENTURE) {
		            event.setCancelled(true);
	        	}
	        }
    }


	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
	    ItemStack item = event.getItemDrop().getItemStack();
	    if (item != null && item.getType() == Material.MAGMA_CREAM && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("§6Cosmétiques") || item != null && item.getType() == Material.COMPASS && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("§6Menu") ) {
	        event.setCancelled(true); // Annuler la chute de l'item
	    }
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setQuitMessage("§8[§4-§8] §7" + event.getPlayer().getName() + " §8(§2" + (Bukkit.getServer().getOnlinePlayers().size() -1) + "§6/§4" + Bukkit.getServer().getMaxPlayers() + "§8)");
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
                sender.sendMessage("§6[§3FK§6]§8 » §7Height Limite §2activée");
            } else {
                sender.sendMessage("§6[§3FK§6]§8 » §7Height Limite §4désactivée");
            }
            return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("lockinv")) {
            lockinv = !lockinv;
            if (lockinv) {
                sender.sendMessage("§6[§3FK§6]§8 » §7Verrouillage d'inventaire §2activée");
            } else {
                sender.sendMessage("§6[§3FK§6]§8 » §7Verrouillage d'inventaire §4désactivée");
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
