package org.project.mining_contest_plugin_2025;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.project.mining_contest_plugin_2025.EventListeners.Deadevent;
import org.project.mining_contest_plugin_2025.EventListeners.NoDamage;
import org.project.mining_contest_plugin_2025.EventListeners.lobbyGetPlayerInf;
import org.project.mining_contest_plugin_2025.EventListeners.point;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;
import org.project.mining_contest_plugin_2025.SQL.TableExist;
import org.project.mining_contest_plugin_2025.TASK.flow;
import org.project.mining_contest_plugin_2025.command.startCommand;

import java.util.Objects;

import static org.project.mining_contest_plugin_2025.TASK.CreateGame.Create;

public final class Mining_contest_plugin_2025 extends JavaPlugin {

    final BukkitRunnable runnable1 = new BukkitRunnable() {
        public void run() {
            //System.out.println("flow");
            flow.flow();
        }};
    private static Mining_contest_plugin_2025 main;
    public static int status = 1;
    public static int end = 0;
    public static int timer = 3600;
    @Override
    public void onEnable() {
        main = this;
        SQLcollection.SQLCONNECT();
        TableExist.Tableexist();
        Create();
        timer = Integer.valueOf(SQLcollection.time());
        Bukkit.getServer().getPluginManager().registerEvents(new lobbyGetPlayerInf(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Deadevent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new point(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NoDamage(), this);
        this.getCommand("start").setExecutor(new startCommand(this));
        runnable1.runTaskTimer(this, 0,20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Mining_contest_plugin_2025 getMain() {
        return main;
    }

    public static Mining_contest_plugin_2025 getPlugin() {
        return new Mining_contest_plugin_2025();
    }
}
