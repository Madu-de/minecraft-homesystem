package de.madu.home.commands;

import de.madu.home.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelAllHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 0) {
                FileConfiguration config = Main.getPlugin().getConfig();
                String uuid = String.valueOf(player.getUniqueId());
                String path = "Homes." + uuid;
                config.set(path, null);
                Main.getPlugin().saveConfig();
                player.sendMessage("§aDeine Homes wurden erfolgreich gelöscht!");
            } else {
                player.sendMessage("§cBitte benutze §6/delallhomes§c!");
            }
        }
        return false;
    }
}
