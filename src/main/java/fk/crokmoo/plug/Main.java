package fk.crokmoo.plug;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.viaversion.viaversion.api.Via;
import fk.crokmoo.plug.api.IridiumAPI.IridiumColorAPI;
import fk.crokmoo.plug.api.FastBoard;
import fk.crokmoo.plug.chat.ChatModifier;
import fk.crokmoo.plug.commands.*;
import fk.crokmoo.plug.commands.TabCompleter.SendToCompleter;
import fk.crokmoo.plug.events.*;
import fk.crokmoo.plug.scoreboard.ScoreboardFK;
import net.luckperms.api.LuckPerms;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Team;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {

    public static Main instance;

    public final Map<UUID, FastBoard> boards = new HashMap<>();
    public int counter = 0;
    private List<String> serverList = new ArrayList<>();
    public static String Prefix = "§6[§3FK§6]§8 » ";
    public int min = 0;
    public boolean teleportEnabled = true;
    public boolean lockinv = true;
    public int sec = 0;
    Collection<Map<String,String>> collection = new ArrayList<>() ;
    public FileConfiguration config;

    public void onEnable() {
        ScoreboardFK scoreboard = new ScoreboardFK(this);
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = (LuckPerms) ((RegisteredServiceProvider<?>) provider).getProvider();

        }
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("mumble").setExecutor(new DiscordCommand());
        getCommand("sendto").setExecutor(new SendToCommand(this));
        getCommand("bc").setExecutor(new BcCommand());
        getCommand("toggleteleport").setExecutor(new ToggleTeleport(this));
        getCommand("lockinv").setExecutor(new LockInvCommand(this));
        getCommand("openInvUHC").setExecutor(new openInvUHCCommand(this));
        getCommand("gm").setExecutor(new GMCommand());
        getCommand("sendto").setTabCompleter(new SendToCompleter(this));

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        saveDefaultConfig();
        config = getConfig();


        // ajout d'éléments à cette collection
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type","admin");
        hashMap.put("nb","1");
        collection.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","staff");
        hashMap.put("nb","2");
        collection.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","mvp");
        hashMap.put("nb","3");
        collection.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","vip+");
        hashMap.put("nb","4");
        collection.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","vip");
        hashMap.put("nb","5");
        collection.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","default");
        hashMap.put("nb","6");
        collection.add(hashMap);


        instance = this;

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinLeaveEvents(this),this);
        pm.registerEvents(new ChatModifier(this),this);
        pm.registerEvents(new HeightLimit(this),this);
        pm.registerEvents(new ScoreboardFK(this),this);
        pm.registerEvents(new JoinServUHC(this),this);
        pm.registerEvents(new CancelInvMoove(),this);
        pm.registerEvents(new NoDrowningDamage(), this);
        pm.registerEvents(new NoFoodPb(), this);

        for (Player p : Bukkit.getOnlinePlayers()) {
            FastBoard boarddd = this.boards.remove(p.getUniqueId());
            if (boarddd != null) {
                boarddd.delete();
            }
            FastBoard boardd = new FastBoard(p);
            this.boards.put(p.getUniqueId(), boardd);
            boardd.updateTitle("§dFK §5- §6Lobby");
            scoreboard.updateBoard(boardd,p);
        }
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                FastBoard board = boards.get(p.getUniqueId());
                String ping = null;
                if(Via.getAPI().getPlayerVersion(p.getUniqueId()) <= 47) {
                    if (p.getPing() < 75) {
                        ping = "§a" + p.getPing() + "ms";
                    } else if (p.getPing() < 150 && p.getPing() > 76) {
                        ping = "§2" + p.getPing() + "ms";
                    } else if (p.getPing() < 300 && p.getPing() > 151) {
                        ping = "§6" + p.getPing() + "ms";
                    } else if(p.getPing() < 1000 && p.getPing() > 301){
                        ping = "§c" + p.getPing() + "ms";
                    } else {
                        ping = "§c999ms";
                    }
                    board.updateLine(2,"§7Ping : " + ping);
                }
                if(Via.getAPI().getPlayerVersion(p.getUniqueId()) > 47) {
                    if(p.getPing() < 75) {
                        ping = IridiumColorAPI.process("<GRADIENT:86FB01>"+ p.getPing() +"ms</GRADIENT:1CFD00>");
                    } else if (p.getPing() < 150 && p.getPing() > 76) {
                        ping = IridiumColorAPI.process("<GRADIENT:60B401>"+ p.getPing() +"ms</GRADIENT:109000>");
                    } else if (p.getPing() < 300 && p.getPing() > 151) {
                        ping = IridiumColorAPI.process("<GRADIENT:FFDE00>"+ p.getPing() +"ms</GRADIENT:FF8200>");
                    } else {
                        ping = IridiumColorAPI.process("<GRADIENT:FF4500>"+ p.getPing() +"ms</GRADIENT:FF0000>");
                    }
                    board.updateLine(2,IridiumColorAPI.process("<GRADIENT:797979>Ping : </GRADIENT:C8C8C8>") + ping);
                }
            }
            counter++;
        }, 0, 20);

        System.out.println("Le plugin viens de s'allumer ");
        System.out.println("--------------------");
        System.out.println("FKUHC");
        System.out.println("Plugin State : Enable");
        System.out.println("--------------------");
        System.out.println(" ");
    }


    public void onLoad() {
        System.out.println(" ");
        System.out.println("--------------------");
        System.out.println("FKPlugs");
        System.out.println("Plugin State : Loading");
        System.out.println("--------------------");
        System.out.println(" ");
    }

    public void onDisable() {
        System.out.println("Le plugin viens de s'eteindre ");
        System.out.println("--------------------");
        System.out.println("FKPlugs");
        System.out.println("Plugin State : Disable");
        System.out.println("--------------------");
        System.out.println(" ");
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
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
    public void setTab(Player p) {
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();
        board.resetScores(p);
        String gr = null;
        for (Team te : board.getTeams()) {
            te.unregister();
        }
        for(Map<String,String> str : collection) {
            String nb = StringUtils.leftPad(str.get("nb"), 6, "0");
            Team test = board.registerNewTeam(nb);
            String name = str.get("type");
            if (name.equals("admin")) {
                gr = IridiumColorAPI.process("<GRADIENT:BC0000>Admin</GRADIENT:FD2C2C> ");
                test.setColor(ChatColor.RED);
            } else if (name.equals("staff")) {
                gr = IridiumColorAPI.process("<GRADIENT:2273FD>Staff</GRADIENT:00FBF3> ");
                test.setColor(ChatColor.AQUA);
            } else if (name.equals("vip+")) {
                gr = IridiumColorAPI.process("<GRADIENT:9400FB>VIP+</GRADIENT:A700BC> ");
                test.setColor(ChatColor.LIGHT_PURPLE);
            } else if (name.equals("vip")) {
                gr = IridiumColorAPI.process("<GRADIENT:C02EFB>VIP</GRADIENT:E142EA> ");
                test.setColor(ChatColor.GRAY);
            } else if (name.equals("default")) {
                gr = IridiumColorAPI.process("<GRADIENT:FBFBFB>Joueur</GRADIENT:6D6D6D> ");
                test.setColor(ChatColor.GRAY);
            }
            assignTab(gr, test, name);
        }
    }

    public void UpdateTab(Player p){
    String gr = null;
    for(Map<String, String> str : collection){
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();
        board.resetScores(p);
        String nb = StringUtils.leftPad(str.get("nb"), 6, "0");
        String name = str.get("type");
        Team test = board.getTeam(nb);
        assert test != null;

        if (name.equals("admin")) {
            gr = IridiumColorAPI.process("<GRADIENT:BC0000>Admin</GRADIENT:FD2C2C> ");
            test.setColor(ChatColor.YELLOW);
        } else if (name.equals("staff")) {
            gr = IridiumColorAPI.process("<GRADIENT:2273FD>Staff</GRADIENT:00FBF3> ");
            test.setColor(ChatColor.AQUA);
        } else if (name.equals("vip+")) {
            gr = IridiumColorAPI.process("<GRADIENT:9400FB>VIP+</GRADIENT:A700BC> ");
            test.setColor(ChatColor.LIGHT_PURPLE);
        } else if (name.equals("vip")) {
            gr = IridiumColorAPI.process("<GRADIENT:C02EFB>VIP</GRADIENT:E142EA> ");
            test.setColor(ChatColor.GRAY);
        } else if (name.equals("default")) {
            gr = IridiumColorAPI.process("<GRADIENT:FBFBFB>Joueur</GRADIENT:6D6D6D> ");
            test.setColor(ChatColor.GRAY);
        }
        assignTab(gr, test, name);
    }
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

    public void onPluginMessageReceived(String channel, Player player, byte[] msg) {
        openInvUHCCommand openInvUHCCommand = new openInvUHCCommand(this);
        if (!channel.equals("BungeeCord")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(msg);
        String subchannel = in.readUTF();

        if (subchannel.startsWith("AnswerStatus")) {
            short len = in.readShort();
            byte[] statusBytes = new byte[len];
            in.readFully(statusBytes);
            String message = new String(statusBytes, StandardCharsets.UTF_8);
            String[] splitedMessage = message.split("_");
            String status = splitedMessage[0];
            String configPath = openInvUHCCommand.getUHCConfigPath(splitedMessage[1]);
            getConfig().set("server.uhc." + configPath + ".status", status);

            //Bukkit.broadcastMessage(message);

            saveConfig();
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.getOpenInventory().getTitle().equals("§7Serveur §6UHC")) {
                    openInvUHCCommand.openSimplyInv(p);
                }
            }
        }

        DataInputStream inn = new DataInputStream(new ByteArrayInputStream(msg));
        try {
            String subchannell = inn.readUTF();
            if (subchannell.equals("GetServers")) {
                String[] serverArray = inn.readUTF().split(", ");
                serverList = Arrays.asList(serverArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getServerList() {
        return serverList;
    }

    private void assignTab(String gr, Team test, String name) {
        test.setPrefix(gr);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            Collection<String> coll = new ArrayList<>();
            for(Map<String, String> stre : collection) {
                coll.add(stre.get("type"));
            }
            if (Objects.equals(Main.getPlayerGroup(pl, coll), name)) {
                test.addEntry(pl.getName());
            }
        }
    }
}

