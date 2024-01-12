package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RTPCommand implements CommandExecutor {
    private final Main plugin;

    public RTPCommand (Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            for (Map.Entry<String, BukkitRunnable> str : plugin.rtpList.entrySet()) {
                if(str.getKey().equalsIgnoreCase(sender.getName())) {
                    sender.sendMessage(Main.Prefix + "§cErreur : Vous êtes toujours sous cooldown pour à nouveau utiliser la commande §6/tpfar§c.");
                }
            }
            Player p = Bukkit.getPlayer(sender.getName());
            Random rand = new Random();
            int randX = rand.nextInt(10001) - 5000;
            int randZ = rand.nextInt(10001) - 5000;
            int randY = 100;
            for (int i = 320; i > -50; i++) {
                assert p != null;
                if(p.getWorld().getBlockAt(randX,i,randZ).getType() != Material.AIR) {
                    randY = p.getWorld().getBlockAt(randX,i,randZ).getLocation().getBlockY();
                    break;
                }
            }
            Location currentLoc = new Location(p.getWorld(),randX,randY,randZ);
            p.teleport(currentLoc.add(0.5,2,0.5));
            p.sendMessage(Main.Prefix + "§7Vous avez été téléportés aléatoirement en X§8: §6 " +
                currentLoc.getX() + " §7Y§8: §6" + currentLoc.getY() + " §7Z§8: §6" + currentLoc.getZ() + "§7.");
            BukkitRunnable runnable = new BukkitRunnable() {
                int count = assignCount(p);
                @Override
                public void run() {
                    if (count > 1) {
                        count--;
                    } else {
                        if (p.isOnline()) {
                            p.sendMessage(Main.Prefix + "§7 Vous pouvez à nouveau utiliser la commande §6/tpfar§7.");
                        }
                        cancel(); // Arrête le compteur lorsque le compte à rebours est terminé
                    }
                }
            };

            runnable.runTaskTimer(plugin, 0, 20);

            plugin.rtpList.put(p.getName(), runnable);
        }
        return false;
    }

    private int assignCount(Player p) {
        Collection<String> collection =  new ArrayList<String>() ;
        int i = 240;
        // ajout d'éléments à cette collection
        collection.add("admin") ;
        collection.add("staff") ;
        collection.add("mvp") ;
        collection.add("vip+") ;
        collection.add("vip") ;
        collection.add("default") ;

        if (Objects.equals(Main.getPlayerGroup(p, collection), "admin")) {
            i = plugin.getConfig().getInt("admin.cooldownrtp");
        } else if (Objects.equals(Main.getPlayerGroup(p, collection), "staff")) {
            i = plugin.getConfig().getInt("staff.cooldownrtp");
        } else if (Objects.equals(Main.getPlayerGroup(p, collection), "mvp")) {
            i = plugin.getConfig().getInt("mvp.cooldownrtp");
        } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip+")) {
            i = plugin.getConfig().getInt("vip+.cooldownrtp");
        } else if (Objects.equals(Main.getPlayerGroup(p, collection), "vip")) {
            i = plugin.getConfig().getInt("vip.cooldownrtp");
        } else if (Objects.equals(Main.getPlayerGroup(p, collection), "default")) {
            i = plugin.getConfig().getInt("default.cooldownrtp");
        }

        return i;
    }
}
