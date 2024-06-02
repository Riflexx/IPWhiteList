package ru.utopia.ipwhitelist;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class WhiteListAPI {
    public static void addIPWithName(CommandSender sender, String ip, OfflinePlayer player) {

            Main.getPlugin().getConfig().set("whitelist", player.getName());
            Main.getPlugin().getConfig().set("whitelist." + player.getName(), ip);
            Main.getPlugin().saveConfig();
            Main.getPlugin().reloadConfig();
            sender.sendMessage("Белый список обновлён.\nНовые данные: Ник - " + player.getName() + ", IP - " + ip);

    }

    public static void removeIPWithName(CommandSender sender, OfflinePlayer player) {

            Main.getPlugin().getConfig().set("whitelist." + player.getName(), null);
            Main.getPlugin().saveConfig();
            Main.getPlugin().reloadConfig();
            sender.sendMessage("Белый список обновлён.\nУдалён - " + player.getName());

    }
}
