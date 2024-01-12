package fk.crokmoo.plug;

import com.google.common.collect.Iterables;
import fk.crokmoo.plug.chat.ChatModifier;
import fk.crokmoo.plug.commands.*;
import fk.crokmoo.plug.commands.TabCompleter.SendToCompleter;
import fk.crokmoo.plug.events.denyMobSpawnerChange;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.*;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {
    Collection<String> collection =  new ArrayList<String>() ;
    public static Main instance;
    public static String Prefix = "§6[§3FK§6]§8 » ";
    private List<String> serverList = new ArrayList<>();
    public Map<String, BukkitRunnable> rtpList = new HashMap<>();

    public FileConfiguration config;
    public void onEnable() {

        saveDefaultConfig();
        config = getConfig();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = (LuckPerms) ((RegisteredServiceProvider<?>) provider).getProvider();

        }
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new ChatModifier(this),this);
        pm.registerEvents(new denyMobSpawnerChange(),this);

        getCommand("sendto").setExecutor(new SendToCommand(this));
        getCommand("lobby").setExecutor(new LobbyCommand(this));
        getCommand("discord").setExecutor(new DiscordCommand(this));
        //getCommand("tpfar").setExecutor(new RTPCommand(this));
        getCommand("nv").setExecutor(new NVCommand(this));

        getCommand("sendto").setTabCompleter(new SendToCompleter(this));

        collection.add("admin") ;
        collection.add("staff") ;
        collection.add("mvp") ;
        collection.add("vip+") ;
        collection.add("vip") ;
        collection.add("default") ;
        instance = this;

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        System.out.println("Le plugin viens de s'allumer ");
        System.out.println("--------------------");
        System.out.println("FKPlug Survie");
        System.out.println("Plugin State : Enable");
        System.out.println("--------------------");
        System.out.println(" ");
    }

    public void onLoad() {
        System.out.println(" ");
        System.out.println("--------------------");
        System.out.println("FKPlug Survie");
        System.out.println("Plugin State : Loading");
        System.out.println("--------------------");
        System.out.println(" ");
    }

    public void onDisable() {
        System.out.println("Le plugin viens de s'eteindre ");
        System.out.println("--------------------");
        System.out.println("FKPlug Survie");
        System.out.println("Plugin State : Disable");
        System.out.println("--------------------");
        System.out.println(" ");
    }

    public static String getPlayerGroup(Player player, Collection<String> possibleGroups) {
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }

    public static Main getInstance() {
        return instance;
    }

    public void requestServerList() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("GetServers");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Envoyez la demande. Vous pouvez envoyer à n'importe quel joueur, car cela sert simplement de transport.
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null) {
            player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
        }
    }
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));

        try {
            String subchannel = in.readUTF();
            if (subchannel.equals("GetServers")) {
                String[] serverArray = in.readUTF().split(", ");
                serverList = Arrays.asList(serverArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getServerList() {
        return serverList;
    }
}