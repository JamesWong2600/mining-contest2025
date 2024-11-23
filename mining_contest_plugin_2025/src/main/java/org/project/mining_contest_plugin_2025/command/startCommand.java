package org.project.mining_contest_plugin_2025.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

import static org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025.status;

public class startCommand implements CommandExecutor {
    private Mining_contest_plugin_2025 plugin;
    public static int[] countdown = {10};
    public startCommand(Mining_contest_plugin_2025 plugin) {
        this.plugin = plugin;
    }
    int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");

    public static void start()
    {
        for(Player all : Bukkit.getServer().getOnlinePlayers())
        {
            if(!all.isOp()){
            for (int x = -20; x < 20; x++) // whole chunk
            {
                for (int z = -20; z < 20; z++) // whole chunk
                {
                    for (int y = 250; y < 265; y++) // any level between 1 - 40
                    {
                        Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
                        loc.getBlock().setType(Material.AIR);
                    }
                }
            }
            int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
           String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
            try(Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                Statement stmt = conn.createStatement()
            ) {
                UUID UUid = all.getUniqueId();
                String sqldata = "UPDATE datafile " +
                        "SET tp = 1 WHERE UUID in ('"+UUid+"')";
                stmt.executeUpdate(sqldata);
            }
            catch (SQLException ed) {
                ed.printStackTrace();
            }

            final int[] countdown = {10};
            Random random = new Random();
            int X = random.nextInt(-sz,sz);
            int Z = random.nextInt(-sz,sz);
            World world = Bukkit.getServer().getWorld("world");
            world.setTime(0);
            int Y = world.getHighestBlockYAt(X,Z);
            Location location = new Location(world,X,Y+1,Z);
            location.getChunk();
            all.teleport(location);
            status = 2;
            all.setGameMode(GameMode.SURVIVAL);
            all.clearActivePotionEffects();
            PlayerInventory inventory = all.getInventory();
            all.sendTitle(ChatColor.GREEN+"比賽正式開始", "", 4, 20, 4);
            inventory.clear();
            ItemStack item1 = new ItemStack(Material.IRON_PICKAXE);
            ItemStack item2 = new ItemStack(Material.BIRCH_BOAT);
            all.getInventory().setItem(0, item1);
            all.getInventory().setItem(1, item2);
            all.playSound(all.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
        }else{
                all.setGameMode(GameMode.SPECTATOR);
                all.sendTitle(ChatColor.GREEN+"比賽正式開始", "", 4, 20, 4);
                all.playSound(all.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
            }
        }
    }

    public static void countingdown()
    {
        for(Player all : Bukkit.getServer().getOnlinePlayers())
        {
            all.sendTitle(ChatColor.GREEN+String.valueOf(countdown[0]), "", 4, 20, 4);
            all.playSound(all.getLocation(), Sound.UI_BUTTON_CLICK, 20, 1);
        }
    }

    public static void task()
    {
        for(Player all : Bukkit.getServer().getOnlinePlayers())
        {
            all.sendTitle(ChatColor.GREEN+String.valueOf(countdown[0]), "", 4, 20, 4);
            all.playSound(all.getLocation(), Sound.UI_BUTTON_CLICK, 20, 1);
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(status==1){
        if(sender instanceof Player){
          Player p = (Player) sender;
        if(!(p.isOp())){
            p.sendMessage("你目前還沒有那個權力去執行這條指令");
        }else{
                new BukkitRunnable() {
                    public void run() {
                        countdown[0] -= 1;
                        if (countdown[0] > 0) {
                            countingdown();
                        }
                        if (countdown[0] == 0) {
                            start();
                            cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 20);
        }}}return false;}}









