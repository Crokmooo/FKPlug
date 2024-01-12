package fk.crokmoo.plug.chat;

import fk.crokmoo.plug.Main;
import fk.crokmoo.plug.api.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ChatModifier implements Listener {
    private Main plugin;

    public ChatModifier(Main plugin) {
        this.plugin = plugin;
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
            if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.admin.before_pseudo") + p.getPlayer().getName() + plugin.getConfig().getString("chat.prefix.admin.after_pseudo"));
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.staff.before_pseudo") + p.getPlayer().getName() + plugin.getConfig().getString("chat.prefix.staff.after_pseudo"));
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "mvp")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.mvp.before_pseudo") + p.getPlayer().getName() + plugin.getConfig().getString("chat.prefix.mvp.after_pseudo"));
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.vip+.before_pseudo") + p.getPlayer().getName() + plugin.getConfig().getString("chat.prefix.vip+.after_pseudo"));
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.vip.before_pseudo") + p.getPlayer().getName() + plugin.getConfig().getString("chat.prefix.vip.after_pseudo"));
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                grade = IridiumColorAPI.process(plugin.getConfig().getString("chat.prefix.default.before_pseudo") + p.getPlayer().getName() + plugin.getConfig().getString("chat.prefix.default.after_pseudo"));
            }
            String modifiedMessage = grade + message;

            pl.sendMessage(modifiedMessage);
        }
    }
}
