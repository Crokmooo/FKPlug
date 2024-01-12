package fk.crokmoo.plug.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mojang.authlib.GameProfile;
import fk.crokmoo.plug.Main;
import fk.crokmoo.plug.api.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class openInvUHCCommand implements CommandExecutor {
    private final Main plugin;
    public openInvUHCCommand (Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = Bukkit.getPlayer(sender.getName());

            assert p != null;
            p.openInventory(openInv(p));
        }
        return true;
    }

    public Inventory openInv(Player p) {
        ConfigurationSection GConfig = plugin.getConfig().getConfigurationSection("server.uhc");

        String texture;
        ItemStack head;
        SkullMeta headMeta;
        GameProfile profile;
        ItemMeta headIMeta;
        Inventory inv = Bukkit.createInventory(null,6*9,"§7Serveur §6UHC");
        int[] orange_glass = {0,8,45,53};
        int[] yellow_glass = {1,9,7,17,36,46,52,44};

        for (int slot : orange_glass) {
            ItemStack glassPane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glassPane.getItemMeta();
            glassMeta.setDisplayName(" ");
            glassPane.setItemMeta(glassMeta);
            inv.setItem(slot, glassPane);
        }
        for (int slot : yellow_glass) {
            ItemStack glassPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glassPane.getItemMeta();
            glassMeta.setDisplayName(" ");
            glassPane.setItemMeta(glassMeta);
            inv.setItem(slot, glassPane);
        }

        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDE4YzIwZjhjNTg3MmViMWQ4MDgwMmY2NzMxMjJmYmUzMDg5MTNkMWI2YmE0MTc2YTQ0ZTlkNjczZDQ5OWU0In19fQ==");
        headIMeta = head.getItemMeta();
        assert headIMeta != null;
        headIMeta.setDisplayName("§7Partie en §aattente...");
        head.setItemMeta(headIMeta);
        inv.setItem(3,head);

        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNmNmQ2YzQ3OTlmY2VlM2MzN2UxYzMxNjg3ZjQ2ZDA1YTI5ZTI0MDFhM2IxNzRjYzAzMGZmNDRiYjI1ODcyMyJ9fX0=");
        headIMeta = head.getItemMeta();
        assert headIMeta != null;
        headIMeta.setDisplayName("§7Partie en §6cours...");
        head.setItemMeta(headIMeta);
        inv.setItem(5,head);

        int i = 0;
        assert GConfig != null;
        for(String s : GConfig.getKeys(false)) {
            Boolean check = CheckServer(plugin.getConfig().getString("server.uhc." + s + ".address"), plugin.getConfig().getInt("server.uhc." + s + ".port"), p,plugin.getConfig().getString("server.uhc." + s + ".name"));
            System.out.println(s + " : " + check);
        }
        List<String> lore;
        for(String s : GConfig.getKeys(false)) {
            if(!plugin.getConfig().getString("server.uhc." + s).isEmpty()) {
                if(CheckServer(plugin.getConfig().getString("server.uhc." + s + ".address"), plugin.getConfig().getInt("server.uhc." + s + ".port"), p,plugin.getConfig().getString("server.uhc." + s + ".name"))) {
                    if(plugin.getConfig().getString("server.uhc." + s + ".status").equalsIgnoreCase("Attente")) {

                        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg3Nzk4NDNmODA3NTdmN2Q3ZmM5ZjI4NzEzYTFjMzVmZDMxNTEwZWE1ZWNiMGI1OGQ2M2RmNDQ5ZWQ1NGFmIn19fQ==");
                        headIMeta = head.getItemMeta();
                        headIMeta.setDisplayName("§a" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                        lore = new ArrayList<>();
                        lore.add("§7Status §8: §a" + plugin.getConfig().getString("server.uhc." + s + ".status"));
                        headIMeta.setLore(lore);
                        head.setItemMeta(headIMeta);
                        inv.setItem(10 + i, head);

                    } else if (plugin.getConfig().getString("server.uhc." + s + ".status").equalsIgnoreCase("Game")) {

                        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRjZmM2Nzk1NDU0MTkyYWZhN2E4Mjg5MWVhMGZkZWQyNTEyOWY0YmRkYTEyNmQxNmZmMGE2YmMxYTI5OTk5MiJ9fX0=");
                        headIMeta = head.getItemMeta();
                        headIMeta.setDisplayName("§6" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                        lore = new ArrayList<>();
                        lore.add("§7Status §8: §6" + plugin.getConfig().getString("server.uhc." + s + ".status"));
                        headIMeta.setLore(lore);
                        head.setItemMeta(headIMeta);
                        inv.setItem(10 + i, head);

                    } else if (plugin.getConfig().getString("server.uhc." + s + ".status").equalsIgnoreCase("Fini")) {

                        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmIyYzk2YjY5NjFmZjViNTMwNzUxMTZhYTk0MzMxYzQ3MTg1MWRlNTAwOGJhMDU5NGMzODdiNDZhNDI5ZWJjNCJ9fX0=");
                        headIMeta = head.getItemMeta();
                        headIMeta.setDisplayName("§c" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                        lore = new ArrayList<>();
                        lore.add("§7Status §8: §c" + plugin.getConfig().getString("server.uhc." + s + ".status"));
                        headIMeta.setLore(lore);
                        head.setItemMeta(headIMeta);
                        inv.setItem(10 + i, head);

                    }
                } else {

                    head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmIyYzk2YjY5NjFmZjViNTMwNzUxMTZhYTk0MzMxYzQ3MTg1MWRlNTAwOGJhMDU5NGMzODdiNDZhNDI5ZWJjNCJ9fX0=");
                    headIMeta = head.getItemMeta();
                    headIMeta.setDisplayName("§c" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                    head.setItemMeta(headIMeta);
                    inv.setItem(10 + i,head);

                }
                i++;
            }
        }
        return inv;
    }

    public void openSimplyInv(Player p) {
        ConfigurationSection GConfig = plugin.getConfig().getConfigurationSection("server.uhc");

        ItemStack head;
        ItemMeta headIMeta;
        Inventory inv = Bukkit.createInventory(null,6*9,"§7Serveur §6UHC");
        int[] orange_glass = {0,8,44,53};
        int[] yellow_glass = {1,9,7,17,35,45,52,43};

        for (int slot : orange_glass) {
            ItemStack glassPane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glassPane.getItemMeta();
            glassMeta.setDisplayName(" ");
            glassPane.setItemMeta(glassMeta);
            inv.setItem(slot, glassPane);
        }
        for (int slot : yellow_glass) {
            ItemStack glassPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glassPane.getItemMeta();
            glassMeta.setDisplayName(" ");
            glassPane.setItemMeta(glassMeta);
            inv.setItem(slot, glassPane);
        }

        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDE4YzIwZjhjNTg3MmViMWQ4MDgwMmY2NzMxMjJmYmUzMDg5MTNkMWI2YmE0MTc2YTQ0ZTlkNjczZDQ5OWU0In19fQ==");
        headIMeta = head.getItemMeta();
        assert headIMeta != null;
        headIMeta.setDisplayName("§7Partie en §aattente...");
        head.setItemMeta(headIMeta);
        inv.setItem(3,head);

        head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNmNmQ2YzQ3OTlmY2VlM2MzN2UxYzMxNjg3ZjQ2ZDA1YTI5ZTI0MDFhM2IxNzRjYzAzMGZmNDRiYjI1ODcyMyJ9fX0=");
        headIMeta = head.getItemMeta();
        assert headIMeta != null;
        headIMeta.setDisplayName("§7Partie en §6cours...");
        head.setItemMeta(headIMeta);
        inv.setItem(5,head);

        int i = 0;
        List<String> lore;
        for(String s : GConfig.getKeys(false)) {
            if(!plugin.getConfig().getString("server.uhc." + s).isEmpty()) {
                if(plugin.getConfig().getString("server.uhc." + s + ".status").equalsIgnoreCase("Attente")) {
                    head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg3Nzk4NDNmODA3NTdmN2Q3ZmM5ZjI4NzEzYTFjMzVmZDMxNTEwZWE1ZWNiMGI1OGQ2M2RmNDQ5ZWQ1NGFmIn19fQ==");
                    headIMeta = head.getItemMeta();
                    headIMeta.setDisplayName("§a" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                    lore = new ArrayList<>();
                    lore.add("§7Status §8: §a" + plugin.getConfig().getString("server.uhc." + s + ".status"));
                    headIMeta.setLore(lore);
                    head.setItemMeta(headIMeta);
                    inv.setItem(10 + i, head);

                } else if (plugin.getConfig().getString("server.uhc." + s + ".status").equalsIgnoreCase("Game")) {

                    head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRjZmM2Nzk1NDU0MTkyYWZhN2E4Mjg5MWVhMGZkZWQyNTEyOWY0YmRkYTEyNmQxNmZmMGE2YmMxYTI5OTk5MiJ9fX0=");
                    headIMeta = head.getItemMeta();
                    headIMeta.setDisplayName("§6" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                    lore = new ArrayList<>();
                    lore.add("§7Status §8: §6" + plugin.getConfig().getString("server.uhc." + s + ".status"));
                    headIMeta.setLore(lore);
                    head.setItemMeta(headIMeta);
                    inv.setItem(10 + i, head);

                } else if (plugin.getConfig().getString("server.uhc." + s + ".status").equalsIgnoreCase("Fini")) {

                    head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmIyYzk2YjY5NjFmZjViNTMwNzUxMTZhYTk0MzMxYzQ3MTg1MWRlNTAwOGJhMDU5NGMzODdiNDZhNDI5ZWJjNCJ9fX0=");
                    headIMeta = head.getItemMeta();
                    headIMeta.setDisplayName("§c" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                    lore = new ArrayList<>();
                    lore.add("§7Status §8: §c" + plugin.getConfig().getString("server.uhc." + s + ".status"));
                    headIMeta.setLore(lore);
                    head.setItemMeta(headIMeta);
                    inv.setItem(10 + i, head);
                }
            } else {
                head = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmIyYzk2YjY5NjFmZjViNTMwNzUxMTZhYTk0MzMxYzQ3MTg1MWRlNTAwOGJhMDU5NGMzODdiNDZhNDI5ZWJjNCJ9fX0=");
                headIMeta = head.getItemMeta();
                headIMeta.setDisplayName("§c" + plugin.getConfig().getString("server.uhc." + s + ".name"));
                head.setItemMeta(headIMeta);
                inv.setItem(10 + i,head);
            }
            i++;
        }
        p.openInventory(inv);
    }

    public Boolean CheckServer(String server, int port, Player p, String serverName) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(server, port), 1000); // Timeout de 1000ms

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Forward"); // Commande BungeeCord
            out.writeUTF(serverName); // Serveur cible
            out.writeUTF("QuestionsChannel"); // Sous-canal personnalisé
            String message = "DemandeStatutJeu_" + serverName;
            // Encodage du message en bytes
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            out.writeShort(messageBytes.length);
            out.write(messageBytes);

            // Envoyer le message
            p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String getUHCConfigPath(String str) {
        ConfigurationSection GConfig = plugin.getConfig().getConfigurationSection("server.uhc");
        for(String s : GConfig.getKeys(false)) {
            if(!plugin.getConfig().getString("server.uhc." + s).isEmpty()) {
                if(plugin.getConfig().getString("server.uhc." + s + ".name").equals(str)) {
                    return s;
                }
            }
        }
        return null;
    }

}
