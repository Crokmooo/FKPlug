package fk.crokmoo.plug.events;

import fk.crokmoo.plug.Main;
import fk.crokmoo.plug.api.IridiumAPI.IridiumColorAPI;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinLeaveEvents implements Listener {
    private Main plugin;
    public JoinLeaveEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player pl = e.getPlayer();
        plugin.setTab(e.getPlayer());
        for(Player p : Bukkit.getOnlinePlayers()) {
            //plugin.UpdateTab(p);
        }
        String header = IridiumColorAPI.process("<GRADIENT:424242>§m             </GRADIENT:999999><GRADIENT:ff5900>&l FK Corp </GRADIENT:ffa600><GRADIENT:999999>§m             </GRADIENT:424242>\n");
        String footer = "\n" + IridiumColorAPI.process("<GRADIENT:969696>Rejoignez nous : </GRADIENT:e1e1e1><GRADIENT:9b00ff>/discord</GRADIENT:d800ff>\n<GRADIENT:424242>&m                    </GRADIENT:999999><GRADIENT:999999>&m                    </GRADIENT:424242>");

        pl.setPlayerListHeaderFooter(header,footer);
    }
}
