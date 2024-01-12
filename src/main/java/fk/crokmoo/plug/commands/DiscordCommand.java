package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import fk.crokmoo.plug.api.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class DiscordCommand implements CommandExecutor {
    private final Main plugin;

    public DiscordCommand (Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = Bukkit.getPlayer(commandSender.getName());
        assert p != null;

        if(strings.length == 0) {
            p.sendMessage(Main.Prefix + "§7Voici le lien de notre Discord : §" + IridiumColorAPI.process("<GRADIENT:9b00ff>" + plugin.getConfig().getString("discord") + "</GRADIENT:d800ff>") + "§7.");
            return true;
        } else {
            if(strings[1].equalsIgnoreCase("all") && p.isOp()) {
                Bukkit.broadcastMessage(Main.Prefix + "Le lien vers pour rejoindre notre Discord est : " + IridiumColorAPI.process("<GRADIENT:9b00ff>" + plugin.getConfig().getString("discord") + "</GRADIENT:d800ff>") + "§7.");
            }
        }

        return false;
    }
}
