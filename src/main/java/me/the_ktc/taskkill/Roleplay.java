package me.the_ktc.taskkill;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Roleplay implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //for (World w : org.bukkit.Bukkit.getServer().getWorlds())
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("taskkill")) {
                    if (args.length == 0) {
                        player.sendMessage("and which task shall I kill?");
                    }
                    //if (args.length == 1) {
                    else if (args[1].equalsIgnoreCase("weather")) {
                        System.out.println("I´m not god!");
                        // if (args[2].equalsIgnoreCase("rain"))
                        //     if (args[3].equalsIgnoreCase("on")) {
                        //         w.setStorm(true);
                        //         //w.hasStorm(true);
                        //         //org.bukkit.event.weather.ThunderChangeEvent
                        //     }
                    // } else if (args[2].equalsIgnoreCase("storm"))
                    //     if (args[3].equalsIgnoreCase("on")) w.setThundering(true);

                } else sender.sendMessage("Pls nur /performance verwenden");
            } else {
                System.out.println("You don´t have the permissions to do that!");
                if (args.length == 0) {
                    return false;
                    //Regenswitch();
                } else sender.sendMessage("Pls nur /performance verwenden");
            }
        //else sender.sendMessage("Dieser Command geht leider nur als Player");
        }
        return false;
    }

}
