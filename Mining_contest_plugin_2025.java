package org.project.mining_contest_plugin_2025;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.project.mining_contest_plugin_2025.EventListeners.*;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;
import org.project.mining_contest_plugin_2025.SQL.TableExist;
import org.project.mining_contest_plugin_2025.TASK.flow;
import org.project.mining_contest_plugin_2025.command.disablepvp;
import org.project.mining_contest_plugin_2025.command.enablepvp;
import org.project.mining_contest_plugin_2025.command.startCommand;

import java.util.Objects;

import static org.project.mining_contest_plugin_2025.TASK.CreateGame.Create;

public final class Mining_contest_plugin_2025 extends JavaPlugin {

    final BukkitRunnable runnable1 = new BukkitRunnable() {
        public void run() {
            //System.out.println("flow");
            flow.flow();
        }};
    final BukkitRunnable runnable2 = new BukkitRunnable() {
        public void run() {
            //System.out.println("flow");
            if(Mining_contest_plugin_2025.status==1) {
            for(Player all : Bukkit.getServer().getOnlinePlayers())
            {
                all.sendMessage(ChatColor.GREEN+"現階段處於等待階段，玩家可以使用 /enablepvp 指令進行PVP模式，最高得分者可獲得150mycard，PVP模式所得的分數與挖礦比賽所得的分數獨立分開，不想玩可以無需理會這條訊息");
                all.playSound(all.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
            }}
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
        Bukkit.getServer().getPluginManager().registerEvents(new itemdropwhenlobby(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Deadevent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new point(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NoDamage(), this);
        this.getCommand("start").setExecutor(new startCommand(this));
        this.getCommand("disablepvp").setExecutor(new disablepvp(this));
        this.getCommand("enablepvp").setExecutor(new enablepvp(this));
        runnable1.runTaskTimer(this, 0,20);
        //runnable2.runTaskTimer(this, 0,1500);
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
