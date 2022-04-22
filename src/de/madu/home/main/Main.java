package de.madu.home.main;

import de.madu.home.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    private static Main plugin;

    public void onEnable() {
        plugin = this;

        System.out.println("""
                  
                                                     GNU Affero General Public License v3.0\s
                  _    _                                     _                    _              __  __           _      \s
                 | |  | |                                   | |                  | |            |  \\/  |         | |     \s
                 | |__| | ___  _ __ ___   ___  ___ _   _ ___| |_ ___ _ __ ___    | |__  _   _   | \\  / | __ _  __| |_   _\s
                 |  __  |/ _ \\| '_ ` _ \\ / _ \\/ __| | | / __| __/ _ \\ '_ ` _ \\   | '_ \\| | | |  | |\\/| |/ _` |/ _` | | | |
                 | |  | | (_) | | | | | |  __/\\__ \\ |_| \\__ \\ ||  __/ | | | | |  | |_) | |_| |  | |  | | (_| | (_| | |_| |
                 |_|  |_|\\___/|_| |_| |_|\\___||___/\\__, |___/\\__\\___|_| |_| |_|  |_.__/ \\__, |  |_|  |_|\\__,_|\\__,_|\\__,_|
                                                    __/ |                                __/ |                           \s
                                                   |___/                                |___/                            \s
                                                                /help Homesystem
                                                                      v1.5
                 """);

        Objects.requireNonNull(getCommand("sethome")).setExecutor(new SetHomeCommand());
        Objects.requireNonNull(getCommand("homes")).setExecutor(new HomesCommand());
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("delhome")).setExecutor(new DelHomeCommand());
        Objects.requireNonNull(getCommand("delallhomes")).setExecutor(new DelAllHomeCommand());
    }

    public static Main getPlugin() { return plugin; }

    public static String createName(String[] args) {
        StringBuilder name = new StringBuilder();
        for (String arg : args) {
            name.append(arg).append(" ");
        }
        name = new StringBuilder(name.toString().toLowerCase());
        name = new StringBuilder(name.substring(0, name.length() - 1));
        return name.toString();
    }
}
