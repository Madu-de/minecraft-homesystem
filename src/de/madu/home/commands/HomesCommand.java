package de.madu.home.commands;

import de.madu.home.main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;


public class HomesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player player) {

            if(args.length == 0) {
                String uuid = String.valueOf(player.getUniqueId());
                printHomes(uuid, player, "Du hast keine Homes!", false);
            } else if(args.length == 1) {
              if(player.hasPermission("*")) {
                  Player target = Bukkit.getPlayer(args[0]);
                  if(target != null) {
                      String uuid = String.valueOf(target.getUniqueId());
                      printHomes(uuid, player, "Dieser Spieler hat keine Homes!", true);
                  } else {
                      player.sendMessage("§c Dieser Spieler ist nicht online!");
                  }
              } else {
                  player.sendMessage("§cDazu hast du keine Rechte!");
              }
            } else {
                player.sendMessage("§cBitte benutze §6/homes§c!");
            }
        }
        return false;
    }

    public void printHomes(String uuid, Player player, String err, Boolean admin) {
        FileConfiguration config = Main.getPlugin().getConfig();
        try {
            TextComponent output = new TextComponent("Homes: ");
            boolean firstHome = true;

            for (String x : Objects.requireNonNull(config.getConfigurationSection("Homes." + uuid)).getKeys(false)) {
                TextComponent out;
                if(firstHome) {
                    out = new TextComponent("§a" + x);
                    firstHome = false;
                } else {
                    out = new TextComponent(", §a" + x);
                }
                if(!admin) {
                    out.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/home " + x));
                } else {
                    double xLocation = config.getDouble("Homes." + uuid + "." + x + ".X");
                    double yLocation = config.getDouble("Homes." + uuid + "." + x + ".Y");
                    double zLocation = config.getDouble("Homes." + uuid + "." + x + ".Z");
                    float yawLocation = (float) config.getDouble("Homes." + uuid + "." + x + ".Yaw");
                    float pitchLocation = (float) config.getDouble("Homes." + uuid + "." + x + ".Pitch");
                    out.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p " + xLocation + " " + yLocation + " " + zLocation + " " + yawLocation + " " + pitchLocation));
                }
                out.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Teleportiere dich zu " + x + "!")));
                output.addExtra(out);
            }

            player.spigot().sendMessage(output);
        } catch(Exception e) {
            player.sendMessage("§c" + err);
        }
    }
}
