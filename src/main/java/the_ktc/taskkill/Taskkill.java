package the_ktc.taskkill;

import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class Taskkill extends JavaPlugin {
    final String prefix ="[Taskkill V1.0] ";
    private File configFile;
    private FileConfiguration config;
    @Override
    public void onEnable() {
        try{
            Performance perf = new Performance();
            Objects.requireNonNull(getCommand("taskkill")).setExecutor((CommandExecutor) perf);

            checkPluginFolder();
            checkConfigFile();
            List programs=getServices();

            //perf.testAusgabe("Test");
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









    private void checkPluginFolder() {
        // Überprüfe, ob der Plugin-Ordner existiert, wenn nicht, erstelle ihn
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
            getLogger().info("Plugin-Ordner erstellt");
        }
    }

    private void checkConfigFile() {
        // Überprüfe, ob die config.yml existiert, wenn nicht, erstelle sie
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
            getLogger().info("config.yml erstellt");
        }
        // Lade die Config-Datei
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    // Methode, um auf die Config-Datei zuzugreifen
    public List<String> getServices() {
        //List<> output=new List;
        if (config.contains("listedPrograms")) {
            // Hole die Liste der aufgelisteten Programme
            List<String> aufgelisteteProgramme = config.getStringList("listedPrograms");

            // Durchlaufe die Liste und gib die Programme aus
            for (String programm : aufgelisteteProgramme) {
                System.out.println(programm);
            }
        } else {
            // Wenn der Schlüssel nicht vorhanden ist, gib eine Fehlermeldung aus
            System.out.println("Der Schlüssel 'listedPrograms' fehlt in der Konfigurationsdatei.");
        }
        return null;
    }

    // Methode, um Änderungen in der Config-Datei zu speichern
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            getLogger().severe("Fehler beim Speichern der config.yml: " + e.getMessage());
        }
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
//            print(sender.toString());
//            print(command.toString());
//            print(label);
//            print("args:");
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

