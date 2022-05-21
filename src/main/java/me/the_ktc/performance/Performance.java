package me.the_ktc.performance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Performance implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                DoClose();
                player.sendMessage("§aDer Performance-Modus ist nun aktiv!");
                } else sender.sendMessage("Error");
            } else sender.sendMessage("Dieser Command geht leider nur als Player");
        return false;
    }

    void DoClose() {
        try {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"tasklist |find /i \"chrome.exe\">NUL |Taskkill /IM chrome.exe /F & exit && exit\"");
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"tasklist |find /i \"firefox.exe\">NUL |Taskkill /IM firefox.exe /F & exit && exit\"");
            System.out.println("Firefox und Chrome wurden geschlossen");
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}

