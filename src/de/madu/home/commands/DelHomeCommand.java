package de.madu.home.commands;

import de.madu.home.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player player) {
            if(args.length >= 1) {
                FileConfiguration config = Main.getPlugin().getConfig();
                String uuid = String.valueOf(player.getUniqueId());
                String name = Main.createName(args);
                String path = "Homes." + uuid + "." + name;
                config.set(path, null);
                Main.getPlugin().saveConfig();
                player.sendMessage("§aDein Home wurde erfolgreich gelöscht!");
            } else {
                player.sendMessage("§cBitte benutze §6/delhome <NAME>§c!");
            }
        }
        return false;
    }
}
