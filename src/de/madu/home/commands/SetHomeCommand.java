package de.madu.home.commands;

import de.madu.home.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player player) {
            if(args.length >= 1) {
                FileConfiguration config = Main.getPlugin().getConfig();
                String uuid = String.valueOf(player.getUniqueId());
                String name = Main.createName(args);
                if(name.contains("...")) {
                    player.sendMessage("§cDieser Name ist nicht valide.");
                    return false;
                }
                String world = player.getWorld().getName();
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                float yaw = player.getLocation().getYaw();
                float pitch = player.getLocation().getPitch();

                String path = "Homes." + uuid + "." + name;

                config.set(path + ".World", world);
                config.set(path + ".X", x);
                config.set(path + ".Y", y);
                config.set(path + ".Z", z);
                config.set(path + ".Yaw", yaw);
                config.set(path + ".Pitch", pitch);
                Main.getPlugin().saveConfig();
                player.sendMessage("§aHome wurde gesetzt!");
            } else {
                player.sendMessage("§cBitte benutze §6/sethome <NAME>§c!");
            }
        }
        return false;
    }
}
