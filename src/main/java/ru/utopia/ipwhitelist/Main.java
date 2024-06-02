package ru.utopia.ipwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    //За говно код не бейте.

    public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        getCommand("ipwhitelist").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
                if (sender.hasPermission("ipwhitelist.use")) {
                    if (args.length == 3) {
                        OfflinePlayer playerToAdd = Bukkit.getOfflinePlayer(args[1]);
                        if (args[0].equalsIgnoreCase("add")) {
                            if (playerToAdd != null) {
                                String ip = args[2];
                                WhiteListAPI.addIPWithName(sender, ip, playerToAdd);
                            }
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            if (playerToAdd != null) {
                                WhiteListAPI.removeIPWithName(sender, playerToAdd);
                            }
                        }
                    }
                }
                return true;
            }
        });

        Bukkit.getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    public static Main getPlugin() {
        return plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String ipJoined = event.getPlayer().getAddress().getHostString();
        if (getConfig().getString("whitelist." + event.getPlayer().getName()) != null && getConfig().getString("whitelist." + event.getPlayer().getName()).equalsIgnoreCase(ipJoined)) {

        } else {
            event.getPlayer().kickPlayer("Вас нет в белом списке.");
        }
    }
}
