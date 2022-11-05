package me.the_ktc.performance;
import org.bukkit.*;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.EventHandler;

public class Roleplay implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (World w : org.bukkit.Bukkit.getServer().getWorlds())
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("roleplay") || player.hasPermission("roleplay")) {
                    if (args.length == 0) {
                        player.sendMessage("Und was soll ich nun tun?");
                    }
                    //if (args.length == 1) {
                    else if (args[1].equalsIgnoreCase("weather")) {
                        if (args[2].equalsIgnoreCase("rain"))
                            if (args[3].equalsIgnoreCase("on")) {
                                w.setStorm(true);
                                //w.hasStorm(true);
                                //org.bukkit.event.weather.ThunderChangeEvent
                            }
                    } else if (args[2].equalsIgnoreCase("storm"))
                        if (args[3].equalsIgnoreCase("on")) w.setThundering(true);

                } else sender.sendMessage("Pls nur /performance verwenden");
            } else {
                if (args.length == 0) {
                    return false;
                    //Regenswitch();
                } else sender.sendMessage("Pls nur /performance verwenden");
            }
        //else sender.sendMessage("Dieser Command geht leider nur als Player");

        return false;
    }
/*    boolean Regen = false;
    void Regenswitch(){
        if (boolean Regen=false) {
            org.bukkit.Bukkit.getWorld("NULL").setStorm(true);
            org.bukkit.Bukkit.getWorld("").setThundering();
        }
        if (boolean Regen=true) {
            Test();
        }
    }
    void Test(WeatherChangeEvent e) {
            e.setCancelled(true);
        return false;
    } */
}