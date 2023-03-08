package fk.dragon.fkplug.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


public class TradCommand implements CommandExecutor, TabCompleter {
	@Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();
        
        if (args.length == 1) {
            options.add("chien_loup");
            options.add("maitre_demon");
            options.add("gardien");
            options.add("barbare");
            options.add("berserker");
            options.add("maitre_archer");
            options.add("gardien_obscure");
            options.add("survivant");
            options.add("plongeur");
            options.add("chasseur");
            options.add("chap_rouge");
            options.add("villageoisbis");
            options.add("mage");
            options.add("mage_gentil");
            options.add("mage_mechant");
            options.add("soignant");
            options.add("esprit");
            options.add("templier");
            options.add("oracle");
            
        }
        
        return options;
    }

	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage : /trad [mot]");
            return false;
        }

        String word = args[0];
        
        player.sendMessage("");
        
        if (word.equalsIgnoreCase("chien_loup")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §4Chien Loup§7, vous faites parti du Camp des Loups-Garous, votre objectif est donc d'éliminer tout les joueurs n'étant pas dans votre camp. Pour cela vous disposez d'un effet de §bvitesse§7 le jour et d'un effet de §4force§7 la nuit.");
        } 
        
        if (word.equalsIgnoreCase("maitre_demon")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §6Maitre Démon§7, vous devez gagner la partie en étant tout seul. Votre objectif est donc d'éliminer tout les joueurs de la partie. Pour cela vous diposez d'un effet de §4force§7 constant. Malgré ce pouvoir, §41❤§7 a été soustrait à votre barre de vie.");
        } 
        
        if (word.equalsIgnoreCase("gardien")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Gardien§7, vous faites parti du Village, votre objectif est donc d'éliminer le camp des Loups-Garous. Pour cela, vous disposez d'un effet de §2résistance§7 constant. Vous devez aussi proteger un villageois car si celui-ci vint à mourir vous disposerez d'un retrait de §42❤§7 de façon permanantes jusqu'à la fin de la partie.");
        } 

        if (word.equalsIgnoreCase("barbare")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §4Barbare§7, vous faites parti du Camp des Loups-Garous, votre objectif est donc d'éliminer tout les joueurs n'étant pas dans votre camp. Pour vous disposez d'un effet de §4force§7 constant et de votre §6Hache Barbarienne§7. Malgré votre immense force, votre résistance n'a pas su suivre, vous êtes donc bloqué à §48❤§7 de façon permanante.");
        } 
        
        if (word.equalsIgnoreCase("berserker")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §4Berserker§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous.");
        } 
        
        if (word.equalsIgnoreCase("maitre_archer")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Maitre Archer§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Pour cela, vous disposez d'un effet de §bvitesse§7 de façon constante. En tant que le Maitre des Archers, vous possédez un livre §6Puissance§4 4§7 vous permettant d'améliorer votre arc comme il se doit.");
        } 
        
        if (word.equalsIgnoreCase("gardien_obscure")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §6Gardien Obscure§7, vous devez gagner la partie en étant tout seul. Votre objectif est donc d'éliminer tout les joueurs de la partie. Pour cela vous diposez d'un effet de §2résistance§7 constant et de §42❤§7 coeurs en plus. Avec un équipement d'une tel ampleur, vous avez du mal à vous déplaçez rapidement. Un effet de §8lenteur§7 vous est donc infligé.");
        } 
        
        if (word.equalsIgnoreCase("survivant")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Survivant§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Pour cela vous disposez d'une seconde §cvie§7. Lors de l'utilisation de votre seconde §cvie§7, selon le camp de la personne qui vous a tué vos slots de coeurs viendront à changer./n Pour une personne appartenant au camp des §2Villageois§7, votre vie sera réduite à §49❤§7 coeurs. Pour une personne appartenant au camp des §4Loups-Garous§7, votre vie subira un ajout d'§41❤§7 coeur. Ce qui vous fera un total de §411❤§7. Pour finir, si votre assaillant est §6neutre§7, votre vie obtiendra un ajout de §42❤§7 de façon permanante. Ce qui vous fera un total de §412❤§7.");
        } 
        
        if (word.equalsIgnoreCase("plongeur")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Plongeur§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Pour cela vous disposez d'un §csaumon §6enchanté §9Recul §b3§7 et d'un effet de §1Wather Breathing§7 de façon constante. Pour finir, nous vous avons mis à disposition un livre contenant l'enchantement §9Depth Rider §b3§7.");
        } 
        
        if (word.equalsIgnoreCase("chasseur")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Chasseur§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Pour cela vous disposez d'un livre qui contient l'enchantement §ePower §63 et §aPunch §21. Lors de votre mort, votre assaillant perdera la §45❤§7 non permanants.");
        } 
        
        if (word.equalsIgnoreCase("chap_rouge")) {
            player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Chaperon Rouge§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. A AJOUTER");
        } 
        
        if (word.equalsIgnoreCase("villageoisbis")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Villageois Villageois§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Vous ne servez à rien mais tout le monde connait votre rôle ce qui vous innocente.");
        }
        
        if (word.equalsIgnoreCase("mage")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Mage§7, en tant que Mage vous avez le choix entre 2 formes : §ble Mage §b§lBienveillant§7 ou §4le Mage §4§lDémoniaque§7. Si vous choisissez la première forme, chaque §4Loup§7 mourrant de votre main vous fera bénéficier d'un ajout de §40.5❤§7 permanant. La même chose pour les roles §6neutres§7 mais ceux-ci vous feront bénéficier d'§41❤§7 de façon permanante. Cependant, lors de la mort d'un §2Villageois§7 de votre part, un retrait de §40.5❤§7 permanant sera effectuer. De plus, vous commencerez la partie avec §49❤§7. Si vous choisissez la seconde forme, vous devrez gagner §6seul§7. Chaques kills vous ajouteront §40.5❤§7 de façon permanante sauf pour les roles §6Neutres§7 qui vous donneront §41❤§7 permanant. De plus, vous commencez la partie avec 13 coeurs./n §7Choisissez votre forme à l'aide de §b§l/ww bienveillant §7et §4§l/ww demoniaque§7.");
        }
        
        if (word.equalsIgnoreCase("mage_gentil")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Mage Bienveillant§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Chaque §4Loup§7 mourrant de votre main vous fera bénéficier d'un ajout de §40.5❤§7 permanant. La même chose pour les roles §6neutres§7 mais ceux-ci vous feront bénéficier d'§41❤§7 de façon permanante. Cependant, lors de la mort d'un §2Villageois§7 de votre part, un retrait de §40.5❤§7 permanant sera effectuer. De plus, vous commencerez la partie avec §49❤§7.");
        }
        
        if (word.equalsIgnoreCase("mage_mechant")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §6Mage Démoniaque§7, vous devez gagner la partie en étant tout seul. Votre objectif est donc d'éliminer tout les joueurs de la partie. Chaques kills vous ajouteront §40.5❤§7 de façon permanante sauf pour les roles §6Neutres§7 qui vous donneront §41❤§7 permanant. De plus, vous commencez la partie avec §413❤§7.");
        }        
        
        if (word.equalsIgnoreCase("soignant")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §2Soignant§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. A chaque debut de jour vous pouvez effectuer la commande §a/ww heal [Pseudo]§7. La personne sur laquel vous affecterez cette commande récuperera sa §cvie §7entierement.");
        	player.sendMessage("");
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous pouvez utiliser votre commande §a/ww heal [Pseudo]§7 sur un joueur ! ");
        	player.sendMessage("");
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous avez bien utiliser votre pouvoir sur");
        	player.sendMessage("");
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous venez d'obtenir les soins du §aSoignant§7. Celui-ci vous a restorez votre vie entierement !");       
        
        }
        
        if (word.equalsIgnoreCase("esprit")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes l'§6Esprit§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. A chaque debut de jour vous pouvez effectuer la commande §a/ww heal [Pseudo]§7. La personne sur laquel vous affecterez cette commande récuperera sa vie entierement.");
        }
        
        if (word.equalsIgnoreCase("templier")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes §6Templier§7, vous devez gagner la partie en étant seul. Votre objectif est donc d'éliminer tout les joueurs de la partie. Pour cela vous diposez d'un effet de §4force§7 et de §2résistance§7 pendant 5 minutes après avoir fait un kill.");
        }
        
        if (word.equalsIgnoreCase("oracle")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous êtes l'§6Oracle§7, vous faites parti du Village, votre objectif est d'éliminer le camp des Loups-Garous. Chaque jour vous pouvez effectuer la commande §a/ww visionner [Pseudo]§7. Celle-ci vous donnera un effet relatif au camp de la personne durant une journée entière. L'effet relatif au Camp des Villageois est §bVitesse§7, celui relatif au Camp des Loups-Garous est §4Force §7et celui pour les personnes Neutre est §2Résistance§7.");
        }
        
        if (word.equalsIgnoreCase("a")) {
        	player.sendMessage("§3[§o§6LG-UHC§3] §6➽§7 Vous pouvez desormais utiliser votre pouvoir du vent en utilisant §a/ww speed [Pseudo]§7.\"");
        }

        
        
        else {
            player.sendMessage("");
        }


        return true;
    }
}