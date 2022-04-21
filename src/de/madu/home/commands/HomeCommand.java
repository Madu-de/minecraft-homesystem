package de.madu.home.commands;

import de.madu.home.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player player) {
            if(args.length >= 1) {
                FileConfiguration config = Main.getPlugin().getConfig();
                String uuid = String.valueOf(player.getUniqueId());
                String name = Main.createName(args);
                String path = "Homes." + uuid + "." + name;

                try {
                    World world = Bukkit.getWorld(Objects.requireNonNull(config.getString(path + ".World")));
                    double x = config.getDouble(path + ".X");
                    double y = config.getDouble(path + ".Y");
                    double z = config.getDouble(path + ".Z");
                    float yaw = (float) config.getDouble(path + ".Yaw");
                    float pitch = (float) config.getDouble(path + ".Pitch");
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    player.teleport(location);
                    player.sendMessage("§aDu hast dich erfolgreich zu deinem Home §2" + name + " §ateleportiert!");
                } catch(Exception e) {
                    player.sendMessage("§cDieses Home wurde nicht gefunden!");
                }


            } else {
                player.sendMessage("§cBitte benutze §6/home <NAME>§c!");
            }
        }
        return false;
    }
}
