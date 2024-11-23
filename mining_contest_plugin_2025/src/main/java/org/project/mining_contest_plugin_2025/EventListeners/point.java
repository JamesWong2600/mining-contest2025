package org.project.mining_contest_plugin_2025.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
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
import java.util.Random;
import java.util.UUID;

public class point implements Listener {
    int x= 0;
    static final String mineral = "SELECT id, iron, coal, diamond FROM mineral";


    Random random = new Random();
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        UUID UUid = p.getUniqueId();
        String uid = String.valueOf(UUid);
        Random random = new Random();
        Block block = event.getBlock();
        String q3 = p.getName();
        String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
        String sqldata = null;
        if(p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
            p.sendMessage(ChatColor.RED + "此比賽不允許使用絲綢之觸的工具");
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
        }
        else{
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
                        "SET point = point + 10 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "黃金+10分");
            }
            if (block.getType() == Material.EMERALD_ORE || block.getType() == Material.DEEPSLATE_EMERALD_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 100 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "綠寶石+100分");
            }
            if (block.getType() == Material.COPPER_ORE || block.getType() == Material.DEEPSLATE_COPPER_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 1 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "銅礦+1分");
            }
            if (block.getType() == Material.LAPIS_ORE || block.getType() == Material.DEEPSLATE_LAPIS_ORE) {
                sqldata = "UPDATE datafile " +
                        "SET point = point + 1 WHERE UUID in ('"+UUid+"')";
                p.sendMessage(ChatColor.GREEN + "青金石+3分");
            }
            stmt.executeUpdate(sqldata);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
        }
        catch (SQLException ed) {
            ed.printStackTrace();
        }
        }
        }
    }
}