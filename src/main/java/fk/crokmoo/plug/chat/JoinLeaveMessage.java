package fk.crokmoo.plug.chat;

import fk.crokmoo.plug.Main;
import fk.crokmoo.plug.api.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class JoinLeaveMessage implements Listener {

    private Main plugin;

    public JoinLeaveMessage(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void JoinMessage(PlayerJoinEvent e) {
        Player p = e.getPlayer();
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
            if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.admin.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.staff.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "mvp")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.mvp.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.vip+.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.vip.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.default.before_pseudo") + p.getPlayer().getName());
            }
        }
        String joinMessage = IridiumColorAPI.process("<GRADIENT:86FB01>+</GRADIENT:1CFD00> ") + grade;
        e.setJoinMessage(null);
        Bukkit.broadcastMessage(joinMessage);
    }

    @EventHandler
    public void LeaveMessage(PlayerQuitEvent e) {
        Player p = e.getPlayer();
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
            if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.admin.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.staff.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "mvp")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.mvp.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.vip+.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.vip.before_pseudo") + p.getPlayer().getName());
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.default.before_pseudo") + p.getPlayer().getName());
            }
        }
        String quitMessage = IridiumColorAPI.process("<GRADIENT:FF360B>-</GRADIENT:F02F06> ") + grade;
        e.setQuitMessage(null);
        Bukkit.broadcastMessage(quitMessage);
    }
}
