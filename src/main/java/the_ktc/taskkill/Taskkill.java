package the_ktc.taskkill;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Taskkill extends JavaPlugin implements CommandExecutor {

    final String prefix = "[Taskkill V1.1] ";
    private File configFile;
    private FileConfiguration config;
    private List<String> tasks;


    @Override
    public void onEnable() {
        try {
            Objects.requireNonNull(getCommand("taskkill")).setExecutor((CommandExecutor) this);
            // Ob Ordner vorhanden ist
            checkFolderOfPlugin();
            // ob Config vorhanden ist
            tasks=checkConfigFile();
            print("Performance Plugin geladen!");
        } catch (Exception e) {
            print("Taskkiller-Error: " + e);
            try {
            	// logfile schreiben
            	try (PrintWriter writer = new PrintWriter(new FileWriter(this.getFile().getPath()+"/log.txt", false))) {
                    writer.println(e); // Füge den Fehler hinzu
                    print(e.toString()); // Ausgabe zur Bestätigung
                } catch (IOException e1) {
                    print("Error while logging the error into logfile: " + e1.getMessage()); // Fehlerausgabe, falls ein Fehler beim Loggen auftritt
                }
            } catch (Exception ex) {
                print(ex.toString());
            }
        }

    }
        
    // Methode, um Änderungen in der Config-Datei zu speichern
    @Override
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            getLogger().severe("Fehler beim Speichern der config.yml: " + e.getMessage());
        }
    }



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

    
    /*
     * Diese Methode behandelt die Command-Eingabe vom Spieler
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//            print(sender.toString());
//            print(command.toString());
//            print(label);
//            print("args:");
        if (!label.equals("taskkill"))
            throw new RuntimeException("Wrong Command - it must be taskkill!");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("§4Wrong usage: /taskkill <name of listed task to kill>");
                return true;
            }
//            if (args[0].equals("writeLog") && args.length>2) {
//            	writeLog(args[1]);
//            	player.sendMessage("Log written");
//            	return true;
//            }
            if (args.length>1) return false;
            if (args[0].equals("reload")) {
            	checkFolderOfPlugin();
                tasks=checkConfigFile();
            	player.sendMessage("Taskkiller reloaded!");
            	return true;
            }
            if (tasks.isEmpty()){
                player.sendMessage(args[0]+" is not in the config!");
                return true;
            }
            if (tasks.contains(args[0])) {
                DoClose(args[0]);
                player.sendMessage("§4"+args[0]+" was killed");
                return true;
            }
            player.sendMessage(args[0]+" is not in the config!");
            player.sendMessage("These are the available tasks in the config: "+tasks);
            return true;
        } else sender.sendMessage("This command sadly just works as a player!");
        return false;
    }
    
    public void print(String input) {
        System.out.println(prefix + input);
    }

    
    public void writeLog(String errorMessage) {
        String filePath = getDataFolder().toPath() + "/log.txt";
        
        // Versuche, die Datei zu öffnen und den Fehler hinzuzufügen
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println(errorMessage); // Füge den Fehler hinzu
            print("Error logged successfully."); // Ausgabe zur Bestätigung
        } catch (IOException e) {
            print("Error logging: " + e.getMessage()); // Fehlerausgabe, falls ein Fehler beim Loggen auftritt
        }
    }
    
    
    
    // Überprüfe, ob der Plugin-Ordner existiert, wenn nicht, erstelle ihn
    private void checkFolderOfPlugin() {
        if (!getDataFolder().exists()) {
            if (getDataFolder().mkdirs())
                print("Plugin-Ordner erstellt");
            else print("Plugin-Ordner konnte nicht erstellt werden!");
        }
    }


    /**
     * Überprüft, ob die Config vorhanden ist -> wenn nicht, wird diese generiert.
     * Zusätzlich dazu werden die Programme der Config gespeichert.
     */
    private List<String> checkConfigFile() {
        List<String> output=new ArrayList<>();
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
            print("config.yml erstellt");
        }
        // Lade die Config-Datei
        config = YamlConfiguration.loadConfiguration(configFile);

        // Überprüfe, ob der Schlüssel "listedPrograms" vorhanden ist
        if (config.contains("listedPrograms")){
            output = config.getStringList("listedPrograms");
            print("Der output ist:"+output.toString());}
        else print("The key \"listedPrograms\" is not in the config.yml!");
        return output;
    }





    private void DoClose(String name) {
        try {
            print("Kill Task was executed for task "+name);
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"tasklist |find /i \""+name+"\">NUL |Taskkill /IM "+name+" /F & exit && exit\"");
            //Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"tasklist |find /i \"firefox.exe\">NUL |Taskkill /IM firefox.exe /F & exit && exit\"");
            //System.out.println("Firefox und Chrome wurden geschlossen");
        } catch (Exception e) {
            print("ERROR in Plugin Taskkill:");
            e.printStackTrace();
        }
    }

}