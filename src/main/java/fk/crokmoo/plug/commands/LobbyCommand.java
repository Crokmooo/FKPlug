package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LobbyCommand implements CommandExecutor {
    private final Main plugin;

    public LobbyCommand (Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = Bukkit.getPlayer(sender.getName());
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF(plugin.getConfig().getString("lobby.server"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
        }
        return true;
    }
}
