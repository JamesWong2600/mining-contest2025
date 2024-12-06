package org.project.mining_contest_plugin_2025.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025.status;

public class enablepvp implements CommandExecutor {
    private Mining_contest_plugin_2025 plugin;
    public enablepvp(Mining_contest_plugin_2025 plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
        if(status==1){
                Player p = (Player) sender;
                UUID UUid = p.getUniqueId();
                String uid = String.valueOf(UUid);
                String[] SQLDATA = SQLcollection.SQL();
                try(Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                    Statement stmt = conn.createStatement()
                ) {
                    String sqldata = "UPDATE datafile " +
                            "SET pvpmode = 1 WHERE UUID in ('"+uid+"')";
                    stmt.executeUpdate(sqldata);
                }
                catch (SQLException ed) {
                    ed.printStackTrace();
                }
              PlayerInventory inventory = p.getInventory();
              inventory.clear();
              p.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
              p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
              p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
              p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
              p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
              p.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
              p.getInventory().setItem(7, new ItemStack(Material.DIAMOND_AXE));
              p.clearActivePotionEffects();
              ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 10);
              p.getInventory().setItem(1, item);
              p.sendMessage(ChatColor.GREEN+"pvp已開啓，可以透過輸入指令 /disablepvp 來關閉pvp模式");
              p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
                }
        else{
            Player p = (Player) sender;
            p.sendMessage(ChatColor.GREEN+"比賽已開始，無法使用pvp");

        }
        }return false;}}


