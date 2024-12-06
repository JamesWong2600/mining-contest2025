package org.project.mining_contest_plugin_2025.SCOREBOARD;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;
import org.project.mining_contest_plugin_2025.SQL.FindMaxPlayer;
import org.project.mining_contest_plugin_2025.SQL.SQLcollection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class setscore {

    public static Map<Player, Scoreboard> PrepareBoard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Map<Player, Scoreboard> scoreboard = new HashMap<Player, Scoreboard>();
        //Scoreboard board = manager.getNewScoreboard();
        int tps = (int) Bukkit.getServer().getAverageTickTime();
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            scoreboard.put(all, manager.getNewScoreboard());
            Objective objective = scoreboard.get(all).registerNewObjective("test", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.RED + Mining_contest_plugin_2025.getMain().getConfig().getString("title"));
            String[] SQLDATA = SQLcollection.SQL();
            int playercount = 0;
            for (Player alls : Bukkit.getServer().getOnlinePlayers()) {
                if (alls.getGameMode().equals(GameMode.ADVENTURE)) {
                    playercount += 1;
                }
            }
            int ping = all.getPing();
            UUID uuid = all.getUniqueId();
            String uid = String.valueOf(uuid);
            int[] pvpmode = new int[1];
            int[] pvpoint = new int[1];
            String[] maxpointplayer = new String[1];
            int[] maxpoint = new int[1];;
            if(!all.isOp()) {
                try (Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                     Statement stmt = conn.createStatement();
                     Statement stmt2 = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("select * from datafile WHERE UUID in ('" + uid + "')");
                ) {
                    rs.next();
                    pvpmode[0] = rs.getInt("pvpmode");
                    pvpoint[0] = rs.getInt("pvppoint");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try (Connection conn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
                 Statement stmt = conn.createStatement();
                 Statement stmt2 = conn.createStatement();
                 ResultSet rss = stmt2.executeQuery("select * from datafile where pvppoint = (select max(pvppoint) from datafile)");
            ) {
                rss.next();
                maxpointplayer[0] = rss.getString("player");
                maxpoint[0] = rss.getInt("pvppoint");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Score score = objective.getScore("§" + "a" + ChatColor.WHITE + "目前狀態: lobby");
            score.setScore(8);
            Score score3 = objective.getScore("§" + "b" + ChatColor.WHITE + "玩家數量: " + ChatColor.GREEN + playercount);
            score3.setScore(8);
            Score score6 = objective.getScore("§" + "c" + ChatColor.WHITE + "請等待主辦方開始比賽");
            score6.setScore(8);
            Score score000 = objective.getScore("§" + "d" + ChatColor.WHITE + "tps: " + ChatColor.GREEN + tps+ ChatColor.GREEN + " 刻(tick)");
            score000.setScore(8);
            Score scoref = objective.getScore("§" + "e" + ChatColor.WHITE + "ping: " + ChatColor.GREEN + ping + ChatColor.GREEN + " 毫秒(ms)");
            scoref.setScore(8);
            Score scoreaa = objective.getScore("§" + "f" + ChatColor.WHITE + "PVP: ");
            scoreaa.setScore(8);
            if(!all.isOp()){
            if(pvpmode[0]==1){
            Score scoreb = objective.getScore("§" + "g" + ChatColor.WHITE + " 狀態: "+ ChatColor.GREEN +"已打開");
            scoreb.setScore(8);
            }else{
            Score scoreb = objective.getScore("§" + "h" + ChatColor.WHITE + " 狀態: "+ ChatColor.GREEN +"已關閉");
            scoreb.setScore(8);
            }
            Score scorec = objective.getScore("§" + "i" + ChatColor.WHITE + " 得分: " + ChatColor.GREEN + pvpoint[0]+ " " +ChatColor.GREEN + "分");
            scorec.setScore(8);
            }
            Score scored = objective.getScore("§" + "j" + ChatColor.WHITE + " 最高分: "+ChatColor.GREEN + maxpointplayer[0]);
            scored.setScore(8);
        }

    return scoreboard;
    }




    public static Map<Player, Scoreboard> ProcessingBoard()
    {
    String[] SQLDATA = SQLcollection.SQL();
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    //Scoreboard Scoreboard = manager.getNewScoreboard();
    Map<Player, Scoreboard> Scoreboard = new HashMap<Player, Scoreboard>();  ;
    Mining_contest_plugin_2025.timer-=1;
    int tps = (int) Bukkit.getServer().getAverageTickTime();
    String mark;
    for(Player all : Bukkit.getServer().getOnlinePlayers())
    {
        int ping = all.getPing();
        String[] ranking = FindMaxPlayer.MaxPlayer();
        if(all.getGameMode().equals(GameMode.SURVIVAL)){
        try(Connection connn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
         Statement stmt = connn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM datafile where UUID in ('"+all.getUniqueId().toString()+"')");
    ) {
         rs.next();
         mark = rs.getString("point");
         Scoreboard.put(all, manager.getNewScoreboard());
         Objective objective = Scoreboard.get(all).registerNewObjective("test", "dummy");
         objective.setDisplaySlot(DisplaySlot.SIDEBAR);
         objective.setDisplayName(ChatColor.RED + Mining_contest_plugin_2025.getMain().getConfig().getString("title"));
         int playercount = 0;
         for(Player alls : Bukkit.getServer().getOnlinePlayers())
         {
             if(alls.getGameMode().equals(GameMode.SURVIVAL)){
             playercount+=1;
             }
         }
      Score score = objective.getScore("§" + "a" +ChatColor.WHITE+"目前狀態: 正在比賽");
      score.setScore(8);
      Score score6 = objective.getScore("§" + "b" +ChatColor.WHITE+"比賽剩餘時間: " + ChatColor.GREEN + Mining_contest_plugin_2025.timer+ ChatColor.GREEN + " 秒");
      score6.setScore(8);
      int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
      Score score9 = objective.getScore("§" + "c" +ChatColor.WHITE+"邊界: "+ChatColor.GREEN+sz+ ChatColor.GREEN + " 格");
      score9.setScore(8);
      Score score0 = objective.getScore("§" + "d" +ChatColor.WHITE + "排行榜");
      score0.setScore(8);
        Score r1 = objective.getScore("§" + "e" +ChatColor.WHITE+ " "+"1st: " + ChatColor.GREEN + ranking[1] + ChatColor.GREEN + " 分");
        r1.setScore(8);
        Score r2 = objective.getScore("§" + "f" +ChatColor.WHITE+ " "+"2nd: " +ChatColor.GREEN + ranking[3]+ ChatColor.GREEN + " 分");
        r2.setScore(8);
        Score r3 = objective.getScore("§" + "g" +ChatColor.WHITE+ " "+"3rd: " + ChatColor.GREEN + ranking[5]+ ChatColor.GREEN + " 分");
        r3.setScore(8);
        Score r4 = objective.getScore("§" + "h" +ChatColor.WHITE+ " "+"4th: " + ChatColor.GREEN + ranking[7]+ ChatColor.GREEN + " 分");
        r4.setScore(8);
        Score r5 = objective.getScore("§" + "i" +ChatColor.WHITE + " "+"5th: " + ChatColor.GREEN + ranking[9]+ ChatColor.GREEN + " 分");
        r5.setScore(8);
        Score score7 = objective.getScore("§" + "j" +ChatColor.WHITE+"你的分數: " + ChatColor.GREEN + mark + ChatColor.GREEN + "  分");
        score7.setScore(8);
        Score score3 = objective.getScore("§" + "k" +ChatColor.WHITE+"玩家數量: " + ChatColor.GREEN + playercount+ ChatColor.GREEN + " 條");
        score3.setScore(8);
        Score score000 = objective.getScore("§" + "l" +ChatColor.WHITE+"tps: " + ChatColor.GREEN + tps + ChatColor.GREEN + " 刻(tick)");
        score000.setScore(8);
        Score scorem = objective.getScore("§" + "m" +ChatColor.WHITE+"ping: " + ChatColor.GREEN + ping + ChatColor.GREEN + " 毫秒(ms)");
        scorem.setScore(8);
        Score score00 = objective.getScore("§" + "n" +ChatColor.WHITE+"祝你游玩愉快");
        score00.setScore(8);
         } catch (SQLException e) {
        }
    }} return Scoreboard;
    }

    public static Scoreboard ProcessingBoardSPY()
    {
        String[] ranking = FindMaxPlayer.MaxPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + Mining_contest_plugin_2025.getMain().getConfig().getString("title"));
        int playercount = 0;
        int tps = (int) Bukkit.getServer().getAverageTickTime();
        for(Player alls : Bukkit.getServer().getOnlinePlayers())
        {
            if(alls.getGameMode().equals(GameMode.SURVIVAL)){
                playercount+=1;
            }
        }
        Score score = objective.getScore("§" + "a" + ChatColor.WHITE +"目前狀態: 正在比賽");
        score.setScore(8);
        Score score6 = objective.getScore("§" + "b" +ChatColor.WHITE +"比賽剩餘時間: " + ChatColor.GREEN + Mining_contest_plugin_2025.timer+ ChatColor.GREEN + " 秒");
        score6.setScore(8);
        int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
        Score score9 = objective.getScore("§" + "c" + ChatColor.WHITE + "邊界: "+ChatColor.GREEN+sz+ ChatColor.GREEN + " 格(block)");
        score9.setScore(8);
        Score score0 = objective.getScore("§" + "d" +ChatColor.WHITE+ "排行榜");
        score0.setScore(8);
        Score r1 = objective.getScore("§" + "e" +ChatColor.WHITE+" "+"1st: " + ChatColor.GREEN +ranking[0] +" "+ ChatColor.GREEN +ranking[1]+ ChatColor.GREEN + " 分");
        r1.setScore(8);
        Score r2 = objective.getScore("§" + "f" +ChatColor.WHITE+ " "+"2nd: " + ChatColor.GREEN +ranking[2] +" "+ChatColor.GREEN +ranking[3]+ ChatColor.GREEN + " 分");
        r2.setScore(8);
        Score r3 = objective.getScore("§" + "g" +ChatColor.WHITE+ " "+"3rd: " + ChatColor.GREEN +ranking[4] +" "+ ChatColor.GREEN + ranking[5]+ ChatColor.GREEN + " 分");
        r3.setScore(8);
        Score r4 = objective.getScore("§" + "h" +ChatColor.WHITE+ " "+"4th: " + ChatColor.GREEN +ranking[6] +" "+ ChatColor.GREEN + ranking[7]+ ChatColor.GREEN + " 分");
        r4.setScore(8);
        Score r5 = objective.getScore("§" + "i" +ChatColor.WHITE+ " "+"5th: " + ChatColor.GREEN +ranking[8] +" "+ ChatColor.GREEN + ranking[9]+ ChatColor.GREEN + " 分");
        r5.setScore(8);
        Score score3 = objective.getScore("§" + "j" +ChatColor.WHITE +"玩家數量: " + ChatColor.GREEN + playercount+ ChatColor.GREEN + " 條");
        score3.setScore(8);
        Score score000 = objective.getScore("§" + "k" +ChatColor.WHITE+"tps: " + ChatColor.GREEN + tps+ ChatColor.GREEN + " 刻(tick)");
        score000.setScore(8);
        Score score2 = objective.getScore("§" + "l" +ChatColor.WHITE +"旁觀者模式");
        score2.setScore(8);
        return board;
    }


    public static Scoreboard EndBoard()
    {
        String[] ranking = FindMaxPlayer.MaxPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + Mining_contest_plugin_2025.getMain().getConfig().getString("title"));
        Score score = objective.getScore("§" + "1" +ChatColor.WHITE +"目前狀態: 比賽結束");
        score.setScore(8);
        Score score0 = objective.getScore("§" + "2" +ChatColor.WHITE + "排行榜");
        score0.setScore(8);
        Score score1 = objective.getScore("§" + "3" +ChatColor.WHITE+ "  冠軍: " + ChatColor.GREEN +ranking[0] );
        score1.setScore(8);
        Score score2 = objective.getScore("§" + "4" +ChatColor.WHITE+ "  亞軍: " + ChatColor.GREEN +ranking[2]);
        score2.setScore(8);
        Score score3 = objective.getScore("§" + "5" +ChatColor.WHITE+ "  季軍: " + ChatColor.GREEN + ranking[4] );
        score3.setScore(8);
        Score score4 = objective.getScore("§" + "6" +ChatColor.WHITE+ "  第4名: " + ChatColor.GREEN +ranking[6]);
        score4.setScore(8);
        Score score8 = objective.getScore("§" + "7" +ChatColor.WHITE+ "  第5名: " + ChatColor.GREEN +ranking[8] );
        score8.setScore(8);
        Score score9 = objective.getScore("§" + "8" +ChatColor.WHITE+ "感謝參與");
        score9.setScore(8);

        return board;
    }

}
