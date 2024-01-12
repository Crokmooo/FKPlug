package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendToCommand implements CommandExecutor {

    private final Main plugin;

    public SendToCommand (Main plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            Player target = Bukkit.getPlayer(args[0]);
            String serverName = args[1];

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF(serverName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert target != null;
            if(target.isOnline()) {
                target.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            }
        } else {
            sender.sendMessage(Main.Prefix + "§cVous devez faire partie du Staff pour exécuter cette commande.");
        }
        return true;
    }
}
