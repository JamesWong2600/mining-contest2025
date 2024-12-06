package org.project.mining_contest_plugin_2025.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class MiningPoint implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        UUID UUid = p.getUniqueId();
        Block block = event.getBlock();
        String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
        String sqldata = null;
        if(Mining_contest_plugin_2025.status==2){
        try(Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
            Statement stmt = conn.createStatement()
        ) {
            if (block.getType() == Material.DIAMOND_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 50 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "鑽石+50分");
            }

            if (block.getType() == Material.IRON_ORE || block.getType() == Material.DEEPSLATE_IRON_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 2 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "鐵+2分");
            }
            if (block.getType() == Material.COAL_ORE || block.getType() == Material.DEEPSLATE_COAL_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 1 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "煤+1分");
            }
            if (block.getType() == Material.REDSTONE_ORE || block.getType() == Material.DEEPSLATE_REDSTONE_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 1 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "紅石+1分");
            }
            if (block.getType() == Material.GOLD_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 5 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "紅石+5分");
            }
            if (block.getType() == Material.EMERALD_ORE || block.getType() == Material.DEEPSLATE_EMERALD_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 100 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "綠寶石+100分");
            }
            stmt.executeUpdate(sqldata);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
        }
        catch (SQLException ed) {
            ed.printStackTrace();
        }
        }}


    }
