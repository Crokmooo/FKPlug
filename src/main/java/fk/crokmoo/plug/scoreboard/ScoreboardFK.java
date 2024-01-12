package fk.crokmoo.plug.scoreboard;

import com.viaversion.viaversion.api.Via;
import fk.crokmoo.plug.api.IridiumAPI.IridiumColorAPI;
import fk.crokmoo.plug.api.FastBoard;
import fk.crokmoo.plug.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ScoreboardFK implements Listener {
    private Main plugin;


    public ScoreboardFK(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FastBoard board = new FastBoard(player);
        plugin.boards.put(player.getUniqueId(), board);
        if(Via.getAPI().getPlayerVersion(player.getUniqueId()) <= 47) {
            board.updateTitle(IridiumColorAPI.process("§dFK §5- §6Lobby"));
        }
        if(Via.getAPI().getPlayerVersion(player.getUniqueId()) > 47) {
            board.updateTitle(IridiumColorAPI.process("<GRADIENT:FB00BC>FK - Lobby</GRADIENT:FD6300>"));
        }
        updateBoard(board, player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        FastBoard board = plugin.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    public void updateBoard(FastBoard board, Player p) {
        String grade = null;
        String ping = null;

        Collection<String> collection =  new ArrayList<String>() ;
        System.out.println(Via.getAPI().getPlayerVersion(p.getUniqueId()));
        // ajout d'éléments à cette collection
        collection.add("admin") ;
        collection.add("staff") ;
        collection.add("vip+") ;
        collection.add("vip") ;
        collection.add("default") ;

        if(Via.getAPI().getPlayerVersion(p.getUniqueId()) <= 47) {
            if(Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                grade = "§cAdmin";
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                grade = "§bStaff";
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                grade = "§5VIP+";
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                grade = "§dVIP";
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                grade = "§7Joueur";
            }

            if(p.getPing() < 75) {
                ping = "§a" + p.getPing() + "ms";
            } else if (p.getPing() < 150 && p.getPing() > 76) {
                ping = "§2" + p.getPing() + "ms";
            } else if (p.getPing() < 300 && p.getPing() > 151) {
                ping = "§6" + p.getPing() + "ms";
            } else {
                ping = "§c" + p.getPing() + "ms";
            }

            board.updateLines(
                    "§8 » " + grade,
                    " ",
                    "§7Ping §8: " + ping,
                    "§6play.fkcorp.fr"
            );
        }

        if(Via.getAPI().getPlayerVersion(p.getUniqueId()) > 47) {
            if(Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                grade = IridiumColorAPI.process("<GRADIENT:BC0000>Admin</GRADIENT:FD2C2C>");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                grade = IridiumColorAPI.process("<GRADIENT:2273FD>Staff</GRADIENT:00FBF3>");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                grade = IridiumColorAPI.process("<GRADIENT:9400FB>VIP+</GRADIENT:A700BC>");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                grade = IridiumColorAPI.process("<GRADIENT:C02EFB>VIP</GRADIENT:E142EA>");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                grade = IridiumColorAPI.process("<GRADIENT:FBFBFB>Joueur</GRADIENT:6D6D6D>");
            }

            if(p.getPing() < 75) {
                ping = IridiumColorAPI.process("<GRADIENT:86FB01>"+ p.getPing() +"ms</GRADIENT:1CFD00>");
            } else if (p.getPing() < 150 && p.getPing() > 76) {
                ping = IridiumColorAPI.process("<GRADIENT:60B401>"+ p.getPing() +"ms</GRADIENT:109000>");
            } else if (p.getPing() < 300 && p.getPing() > 151) {
                ping = IridiumColorAPI.process("<GRADIENT:FFDE00>"+ p.getPing() +"ms</GRADIENT:FF8200>");
            } else {
                ping = IridiumColorAPI.process("<GRADIENT:FF4500>"+ p.getPing() +"ms</GRADIENT:FF0000>");
            }
            board.updateLines(
                    IridiumColorAPI.process("<GRADIENT:797979>Grade : </GRADIENT:C8C8C8>") + grade,
                    " ",
                    IridiumColorAPI.process("<GRADIENT:797979>Ping : </GRADIENT:C8C8C8>") + ping,
                    IridiumColorAPI.process("<GRADIENT:FB6200>play.fkcorp.fr</GRADIENT:FDFA00>")
            );
        }
    }
}
