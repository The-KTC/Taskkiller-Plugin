package the_ktc.taskkill;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Taskkill extends JavaPlugin {
final String prefix ="[Taskkill V1.0] ";
    @Override
    public void onEnable() {
        try{
            Performance perf = new Performance();
            Objects.requireNonNull(getCommand("taskkill")).setExecutor((CommandExecutor) perf);            //perf.testAusgabe("Test");
        } catch(Exception e){
            print("Taskkiller-Error: ");
            e.printStackTrace();

            }
        print("Performance Plugin geladen!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void print(String input){
        System.out.println(prefix +input);
    }
}








    class Performance implements CommandExecutor {

        private String programName = "Test-Program";

        /*
        Code:
print(sender.toString());
print(command.toString());
print(label);
print("args:");

        Ergebnis von der Konsole ausgehend:

[16:06:47] [Server thread/INFO]: org.bukkit.craftbukkit.v1_20_R1.command.ColouredConsoleSender@4bfb5a06
[16:06:47] [Server thread/INFO]: org.bukkit.command.PluginCommand(taskkill, Taskkill v1.0-Snapshot)
[16:06:47] [Server thread/INFO]: taskkill
[16:06:47] [Server thread/INFO]: args:
[16:06:47] [Server thread/INFO]: This command sadly just works as a player!
[16:06:47] [Server thread/INFO]: /taskkill <name of listed task>

        Output von einem Spieler:

[16:08:32] [Server thread/INFO]: CraftPlayer{name=The_KTC}
[16:08:32] [Server thread/INFO]: org.bukkit.command.PluginCommand(taskkill, Taskkill v1.0-Snapshot)
[16:08:32] [Server thread/INFO]: taskkill
[16:08:32] [Server thread/INFO]: args:
[16:08:32] [Server thread/INFO]: DoClose was executed
         */

        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            print(sender.toString());
            print(command.toString());
            print(label);
            print("args:");
            if(!label.equals("taskkill"))
                throw new RuntimeException("Wrong Command - it must be taskkill!");
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    DoClose(programName);
                    player.sendMessage("§4Wrong usage: /taskkill <name of listed task to kill>");
                    return true;
                }
                if (args[0].equals("test")){
                    player.sendMessage("§4YESSSSSS");
                    return true;
                }

            } else sender.sendMessage("This command sadly just works as a player!");
            return false;
        }

        private void DoClose(String name) {
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

        void print(String a){
            System.out.println(a);
        }
    }

