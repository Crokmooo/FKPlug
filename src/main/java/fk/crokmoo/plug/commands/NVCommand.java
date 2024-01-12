package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NVCommand implements CommandExecutor {
    private final Main plugin;

    public NVCommand (Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = Bukkit.getPlayer(sender.getName());

            assert p != null;
            if(!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2100000000, 0, true, false));
                p.sendMessage(Main.Prefix + "§7Vous §aobtenez l'effet §9Night Vision§7.");
            } else {
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                p.sendMessage(Main.Prefix + "§7Vous n'avez plus l'effet de §9Night Vision§7.");
            }

        }
        return true;
    }
}
