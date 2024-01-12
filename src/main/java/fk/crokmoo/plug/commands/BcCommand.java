package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import fk.crokmoo.plug.api.IridiumAPI.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            Collection<String> collection =  new ArrayList<String>() ;

            // ajout d'éléments à cette collection
            collection.add("admin") ;
            collection.add("staff") ;
            collection.add("mvp") ;
            collection.add("vip+") ;
            collection.add("vip") ;
            collection.add("default") ;
            String grade = "";
            String message = args[1];
            Player p = Bukkit.getPlayer(sender.getName());

            if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
                grade = IridiumColorAPI.process("§8[<GRADIENT:BC0000>Admin</GRADIENT:FD2C2C>§8] <GRADIENT:BC0000>" + p.getPlayer().getName() + "</GRADIENT:FD2C2C> §7» §c");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
                grade = IridiumColorAPI.process("§8[<GRADIENT:2273FD>Staff</GRADIENT:00FBF3>§8] <GRADIENT:2273FD>" + p.getPlayer().getName() + "</GRADIENT:00FBF3> §7» §3");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "mvp")) {
                grade = IridiumColorAPI.process("§8[<GRADIENT:9400FB>MVP</GRADIENT:A700BC>§8] <GRADIENT:9400FB>" + p.getPlayer().getName() + "</GRADIENT:A700BC> §7» §d");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
                grade = IridiumColorAPI.process("§8[<GRADIENT:9400FB>VIP+</GRADIENT:A700BC>§8] <GRADIENT:9400FB>" + p.getPlayer().getName() + "</GRADIENT:A700BC> §7» §d");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
                grade = IridiumColorAPI.process("§8[<GRADIENT:C02EFB>VIP</GRADIENT:E142EA>§8] <GRADIENT:C02EFB>" + p.getPlayer().getName() + "</GRADIENT:E142EA> §7» ");
            } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
                grade = IridiumColorAPI.process("§8[<GRADIENT:FBFBFB>Joueur</GRADIENT:6D6D6D>§8] <GRADIENT:FBFBFB>" + p.getPlayer().getName() + "</GRADIENT:6D6D6D> §7» ");
            }

            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("§e§l[§6§lANNONCE§e§l] " + grade + message);
            Bukkit.broadcastMessage(" ");

        } else {
            sender.sendMessage(Main.Prefix + "§cVous devez faire partie du Staff pour exécuter cette commande.");
        }
        return true;
    }
}
