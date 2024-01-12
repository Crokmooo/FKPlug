package fk.crokmoo.plug.chat;

import com.viaversion.viaversion.api.Via;
import fk.crokmoo.plug.Main;
import org.bukkit.Bukkit;
import fk.crokmoo.plug.api.IridiumAPI.IridiumColorAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ChatModifier implements Listener {
    private Main plugin;


    public ChatModifier(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Join(PlayerJoinEvent e) {
        plugin.setTab(e.getPlayer());
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // Récupérer le joueur qui a envoyé le message
        event.setCancelled(true);
        Player p = event.getPlayer();

        // Récupérer le message envoyé par le joueur
        String message = event.getMessage();
        String grade = "";

        Collection<String> collection =  new ArrayList<String>() ;

        // ajout d'éléments à cette collection
        collection.add("admin") ;
        collection.add("staff") ;
        collection.add("mvp") ;
        collection.add("vip+") ;
        collection.add("vip") ;
        collection.add("default") ;

        // Modifier le message envoyé
        for(Player pl : Bukkit.getOnlinePlayers()) {
            if(Via.getAPI().getPlayerVersion(pl.getUniqueId()) > 47) {
                if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:BC0000>Admin</GRADIENT:FD2C2C>§8] <GRADIENT:BC0000>" + p.getPlayer().getName() + "</GRADIENT:FD2C2C> §7» §c");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:2273FD>Staff</GRADIENT:00FBF3>§8] <GRADIENT:2273FD>" + p.getPlayer().getName() + "</GRADIENT:00FBF3> §7» §3");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:9400FB>VIP+</GRADIENT:A700BC>§8] <GRADIENT:9400FB>" + p.getPlayer().getName() + "</GRADIENT:A700BC> §7» §d");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:C02EFB>VIP</GRADIENT:E142EA>§8] <GRADIENT:C02EFB>" + p.getPlayer().getName() + "</GRADIENT:E142EA> §7» ");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:FBFBFB>Joueur</GRADIENT:6D6D6D>§8] <GRADIENT:FBFBFB>" + p.getPlayer().getName() + "</GRADIENT:6D6D6D> §7» ");
                }
                String modifiedMessage = grade + message;

                pl.sendMessage(modifiedMessage);
            }
            if(Via.getAPI().getPlayerVersion(pl.getUniqueId()) <= 47) {
                if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:BC0000>Admin</GRADIENT:FD2C2C>§8] §c" + p.getPlayer().getName() + " §7» §c");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:2273FD>Staff</GRADIENT:00FBF3>§8] §b" + p.getPlayer().getName() + " §7» §3");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:9400FB>VIP+</GRADIENT:A700BC>§8] §d" + p.getPlayer().getName() + " §7» §d");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:C02EFB>VIP</GRADIENT:E142EA>§8] §d" + p.getPlayer().getName() + " §7» ");
                } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                    grade = IridiumColorAPI.process("§8[<GRADIENT:FBFBFB>Joueur</GRADIENT:6D6D6D>§8] §7" + p.getPlayer().getName() + " §7» ");
                }
                String modifiedMessage = grade + message;

                pl.sendMessage(modifiedMessage);
            }
        }
    }

}
