package org.project.mining_contest_plugin_2025.EventListeners;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

public class lobbyGetPlayerInf implements Listener{

    private Mining_contest_plugin_2025 plugin;

    public lobbyGetPlayerInf(Mining_contest_plugin_2025 plugin) {
        this.plugin = plugin;
    }



      @EventHandler
      public void FirstJoinID(PlayerJoinEvent e) {
          Player p = e.getPlayer();
          String name = p.getName();
          UUID UUid = p.getUniqueId();
          String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
          final BukkitRunnable runnable1 = new BukkitRunnable() {
              public void run() {
                  p.setGameMode(GameMode.ADVENTURE);
                  p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
              }};
          final BukkitRunnable runnable2 = new BukkitRunnable() {
              public void run() {
                  p.setGameMode(GameMode.SPECTATOR);
                  p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
                  System.out.println("other");
              }};
      if(Mining_contest_plugin_2025.status==1){
          if(!p.hasPlayedBefore()){
              if(!p.isOp()) {
                  try (Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                       Statement stmt = conn.createStatement();
                  ) {
                      String creditrecord = "INSERT INTO " +
                              "datafile(id, player, UUID, point, tp, pvppoint, pvpmode) " +
                              "SELECT MAX(id) + 1 ,'" + name + "', '" + UUid + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "' FROM datafile";
                      stmt.executeUpdate(creditrecord);
                  } catch (SQLException e1) {
                      try (Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                           Statement stmt = conn.createStatement();
                      ) {
                          String creditrecord = "INSERT INTO datafile VALUES (1, '" + name + "', '" + UUid + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "')";
                          stmt.executeUpdate(creditrecord);
                      } catch (SQLException e2) {
                          e2.printStackTrace();
                      }
                  }

                  p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 10));
                  //p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 10));
                  Random random = new Random();
                  World world = Bukkit.getServer().getWorld("world");
                  Location location = new Location(world, random.nextInt(-18, 18), 253, random.nextInt(-18, 18));
                  p.teleport(location);
                  p.sendMessage(ChatColor.GREEN+"現階段處於等待階段，玩家可以使用 /enablepvp 指令進行PVP模式，最高得分者可獲得150mycard，PVP模式所得的分數與挖礦比賽所得的分數獨立分開，不想玩可以無需理會這條訊息");
                  p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 1);
                  runnable1.runTaskLater(plugin, 8);
              }else{
                  runnable2.runTaskLater(plugin, 5);
              }

      }}
        else{
          if(Mining_contest_plugin_2025.status==2){
            if(!p.hasPlayedBefore()) {
                runnable2.runTaskLater(plugin, 5);
            }
            }
          if(Mining_contest_plugin_2025.status==3){
                  runnable2.runTaskLater(plugin, 5);
          }}
    }}

