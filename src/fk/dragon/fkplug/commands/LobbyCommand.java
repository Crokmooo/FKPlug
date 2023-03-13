package fk.dragon.fkplug.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public LobbyCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage("§6[§3FK§6]§8 » §7Redirection vers le serveur §6Lobby§7...");

        // Redirection du joueur vers le serveur "Lobby" via l'API BungeeCord
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Spawn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());

        return true;
    }
}
