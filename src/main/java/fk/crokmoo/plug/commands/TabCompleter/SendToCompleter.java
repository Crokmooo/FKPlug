package fk.crokmoo.plug.commands.TabCompleter;

import fk.crokmoo.plug.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SendToCompleter implements TabCompleter {
    private final Main plugin;

    public SendToCompleter(Main plugin){
        this.plugin = plugin;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                String str = p.getName();
                completions.add(str);
            }
        } else if (args.length == 2) {
            completions.addAll(plugin.getServerList());
        }

        List<String> matchedCompletions = new ArrayList<>();
        for (String completion : completions) {
            if (completion.startsWith(args[args.length - 1])) {
                matchedCompletions.add(completion);
            }
        }

        return matchedCompletions;
    }
}
