package org.project.mining_contest_plugin_2025.EventListeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;

import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class Deadevent implements Listener {

    private Mining_contest_plugin_2025 plugin;
    public Deadevent(Mining_contest_plugin_2025 plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        Player p = event.getPlayer();
        UUID UUid = p.getUniqueId();
        String uid = String.valueOf(UUid);
        String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
        String sqldata = null;
        if(Mining_contest_plugin_2025.status==2) {
            try (Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                 Statement stmt = conn.createStatement();
                 Statement stmt2 = conn.createStatement();
                 Statement stmt3 = conn.createStatement();
                 ResultSet rs = stmt3.executeQuery("select * from datafile WHERE UUID in ('"+uid+"')");
            ) {
                rs.next();
                int tp = rs.getInt("tp");
                System.out.println(tp);
                if(tp==1){
                int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
                sqldata = "UPDATE datafile " +
                        "SET point = point - 120 WHERE UUID in ('" + uid + "')";
                    String finalSqldata = sqldata;
                    stmt.executeUpdate(finalSqldata);
                    new BukkitRunnable(){
                    public void run(){
                        p.sendMessage(ChatColor.RED + "死亡 -120分");
                        p.spigot().respawn();
                        p.setGameMode(GameMode.SURVIVAL);
                        Random random = new Random();
                        int X = random.nextInt(-sz,sz);
                        int Z = random.nextInt(-sz,sz);
                        World world = Bukkit.getServer().getWorld("world");
                        int Y = world.getHighestBlockYAt(X,Z);
                        Location location = new Location(world,X,Y,Z);
                        location.getChunk();
                        p.teleport(location);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
                    }
                }.runTaskLater(plugin, 1L);

                }else{
                    new BukkitRunnable(){
                        public void run(){
                            int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
                            p.sendMessage(ChatColor.GREEN + "檢測到由於斷綫無法傳送，已爲你復活，此次死亡不扣分");
                            p.spigot().respawn();
                            p.setGameMode(GameMode.SURVIVAL);
                            Random random = new Random();
                            int X = random.nextInt(-sz,sz);
                            int Z = random.nextInt(-sz,sz);
                            World world = Bukkit.getServer().getWorld("world");
                            int Y = world.getHighestBlockYAt(X,Z);
                            Location location = new Location(world,X,Y,Z);
                            location.getChunk();
                            p.teleport(location);
                            ItemStack item1 = new ItemStack(Material.IRON_PICKAXE);
                            ItemStack item2 = new ItemStack(Material.BIRCH_BOAT);
                            p.getInventory().setItem(0, item1);
                            p.getInventory().setItem(1, item2);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
                            String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
                            try(Connection connn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                                Statement stmt4 = connn.createStatement()
                            ) {
                            UUID UUid = p.getUniqueId();
                            String sqldata = "UPDATE datafile " +
                                    "SET tp = 1 WHERE UUID in ('"+UUid+"')";
                            stmt4.executeUpdate(sqldata);
                            }
                            catch (SQLException ed) {
                                ed.printStackTrace();
                            }

                        }
                    }.runTaskLater(plugin, 1L);

                }
            } catch (SQLException ed) {
                ed.printStackTrace();
            }
        }
    }
}