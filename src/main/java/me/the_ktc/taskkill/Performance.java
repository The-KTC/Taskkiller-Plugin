package me.the_ktc.taskkill;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Performance implements CommandExecutor {
    private String programName="Test-Program";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                DoClose(programName);
                player.sendMessage("§a"+programName+"was killed!");
                } else sender.sendMessage("Error");
            } else sender.sendMessage("This command sadly just works as a player!");
        return false;
    }

    void DoClose(String name) {
        try {
            System.out.println("DoClose was executed");
            //Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"tasklist |find /i \"chrome.exe\">NUL |Taskkill /IM chrome.exe /F & exit && exit\"");
            //Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"tasklist |find /i \"firefox.exe\">NUL |Taskkill /IM firefox.exe /F & exit && exit\"");
            //System.out.println("Firefox und Chrome wurden geschlossen");
        } catch (Exception e) {
            System.out.println("ERROR in Plugin Taskkill:");
            e.printStackTrace();
        }
    }
}

