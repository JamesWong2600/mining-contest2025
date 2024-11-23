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

public class setscore {

    public static Scoreboard PrepareBoard()
    {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    Objective objective = board.registerNewObjective("test", "dummy");
    int playercount = 0;
    for(Player all : Bukkit.getServer().getOnlinePlayers())
    {
        if(all.getGameMode().equals(GameMode.ADVENTURE)){
        playercount+=1;
        }

    }
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.RED + "挖礦大賽2025");
    Score score = objective.getScore("目前狀態: lobby");
    score.setScore(10);
    Score score3 = objective.getScore("玩家: " + ChatColor.GREEN + playercount);
    score3.setScore(7);
    Score score6 = objective.getScore("請等待主辦方開始比賽");
    score6.setScore(8);
    return board;
    }




    public static Map<Player, Scoreboard> ProcessingBoard()
    {
    String[] SQLDATA = org.project.mining_contest_plugin_2025.SQL.SQLcollection.SQL();
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    //Scoreboard Scoreboard = manager.getNewScoreboard();
    Map<Player, Scoreboard> Scoreboard = new HashMap<Player, Scoreboard>();  ;
    Mining_contest_plugin_2025.timer-=1;
    String mark;
    for(Player all : Bukkit.getServer().getOnlinePlayers())
    {
        String[] ranking = org.project.mining_contest_plugin_2025.SQL.FindMaxPlayer.MaxPlayer();
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
         objective.setDisplayName(ChatColor.RED + "挖礦大賽2025");
         int playercount = 0;
         for(Player alls : Bukkit.getServer().getOnlinePlayers())
         {
             if(alls.getGameMode().equals(GameMode.SURVIVAL)){
             playercount+=1;
             }
         }
      Score score = objective.getScore("§" + "a" +ChatColor.WHITE+"目前狀態: 正在比賽");
      score.setScore(10);
      Score score6 = objective.getScore("§" + "b" +ChatColor.WHITE+"比賽剩餘時間: " + ChatColor.GREEN + Mining_contest_plugin_2025.timer);
      score6.setScore(8);
      int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
      Score score9 = objective.getScore("§" + "c" +ChatColor.WHITE+"邊界: "+ChatColor.GREEN+sz);
      score9.setScore(8);
      Score score0 = objective.getScore("§" + "d" +ChatColor.WHITE + "排行榜");
      score0.setScore(8);
        Score r1 = objective.getScore("§" + "e" +ChatColor.WHITE+ " "+"第1名: " + ChatColor.RED + ranking[0] +" "+ ChatColor.RED + ranking[1]);
        r1.setScore(8);
        Score r2 = objective.getScore("§" + "f" +ChatColor.WHITE+ " "+"第2名: " + ChatColor.YELLOW + ranking[2] +" "+ ChatColor.YELLOW + ranking[3]);
        r2.setScore(8);
        Score r3 = objective.getScore("§" + "g" +ChatColor.WHITE+ " "+"第3名: " + ChatColor.GREEN + ranking[4] +" "+ ChatColor.GREEN + ranking[5]);
        r3.setScore(8);
        Score r4 = objective.getScore("§" + "h" +ChatColor.WHITE+ " "+"第4名: " + ChatColor.BLUE + ranking[6] +" "+ ChatColor.BLUE + ranking[7]);
        r4.setScore(8);
        Score r5 = objective.getScore("§" + "i" +ChatColor.WHITE + " "+"第5名: " + ChatColor.LIGHT_PURPLE + ranking[8] +" "+ ChatColor.LIGHT_PURPLE + ranking[9]);
        r5.setScore(8);
        Score score7 = objective.getScore("§" + "j" +ChatColor.WHITE+"你的分數: " + ChatColor.GREEN + mark);
        score7.setScore(7);
        Score score3 = objective.getScore("§" + "k" +ChatColor.WHITE+"玩家: " + ChatColor.GREEN + playercount);
        score3.setScore(7);
        Score score00 = objective.getScore("§" + "l" +ChatColor.WHITE+"祝你游玩愉快");
        score00.setScore(7);
         } catch (SQLException e) {
        }
    }} return Scoreboard;
    }

    public static Scoreboard ProcessingBoardSPY()
    {
        String[] ranking = org.project.mining_contest_plugin_2025.SQL.FindMaxPlayer.MaxPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + "挖礦大賽2025");
        int playercount = 0;
        for(Player alls : Bukkit.getServer().getOnlinePlayers())
        {
            if(alls.getGameMode().equals(GameMode.SURVIVAL)){
                playercount+=1;
            }
        }
        Score score = objective.getScore("§" + "a" + ChatColor.WHITE +"目前狀態: 正在比賽");
        score.setScore(10);
        Score score6 = objective.getScore("§" + "b" +ChatColor.WHITE +"比賽剩餘時間: " + ChatColor.GREEN + Mining_contest_plugin_2025.timer);
        score6.setScore(8);
        int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
        Score score9 = objective.getScore("§" + "c" + ChatColor.WHITE + "邊界: "+ChatColor.GREEN+sz);
        score9.setScore(8);
        Score score0 = objective.getScore("§" + "d" +ChatColor.WHITE+ "排行榜");
        score0.setScore(8);
        Score r1 = objective.getScore("§" + "e" +ChatColor.WHITE+" "+"第1名: " + ChatColor.RED + ranking[0] +" "+ ChatColor.RED + ranking[1]);
        r1.setScore(8);
        Score r2 = objective.getScore("§" + "f" +ChatColor.WHITE+ " "+"第2名: " + ChatColor.YELLOW + ranking[2] +" "+ ChatColor.YELLOW + ranking[3]);
        r2.setScore(8);
        Score r3 = objective.getScore("§" + "g" +ChatColor.WHITE+ " "+"第3名: " + ChatColor.GREEN + ranking[4] +" "+ ChatColor.GREEN + ranking[5]);
        r3.setScore(8);
        Score r4 = objective.getScore("§" + "h" +ChatColor.WHITE+ " "+"第4名: " + ChatColor.BLUE + ranking[6] +" "+ ChatColor.BLUE + ranking[7]);
        r4.setScore(8);
        Score r5 = objective.getScore("§" + "i" +ChatColor.WHITE+ " "+"第5名: " + ChatColor.LIGHT_PURPLE + ranking[8] +" "+ ChatColor.LIGHT_PURPLE + ranking[9]);
        r5.setScore(8);
        Score score3 = objective.getScore("§" + "j" +ChatColor.WHITE +"玩家: " + ChatColor.GREEN + playercount);
        score3.setScore(7);
        Score score2 = objective.getScore("§" + "k" +ChatColor.WHITE +"旁觀者模式");
        score2.setScore(7);
        Score score00 = objective.getScore("§" + "l" +ChatColor.WHITE+"祝你游玩愉快");
        score00.setScore(7);
        return board;
    }


    public static Scoreboard EndBoard()
    {
        String[] ranking = org.project.mining_contest_plugin_2025.SQL.FindMaxPlayer.MaxPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + "挖礦大賽2025");
        Score score = objective.getScore("§" + "1" +ChatColor.WHITE +"目前狀態: 比賽結束");
        score.setScore(8);
        Score score0 = objective.getScore("§" + "2" +ChatColor.WHITE + "排行榜");
        score0.setScore(8);
        Score score1 = objective.getScore("§" + "3" +ChatColor.WHITE+ "  第1名: " + ChatColor.RED + ranking[0] +" "+ ChatColor.RED + ranking[1]);
        score1.setScore(8);
        Score score2 = objective.getScore("§" + "4" +ChatColor.WHITE+ "  第2名: " + ChatColor.YELLOW + ranking[2] +" "+ ChatColor.YELLOW + ranking[3]);
        score2.setScore(8);
        Score score3 = objective.getScore("§" + "5" +ChatColor.WHITE+ "  第3名: " + ChatColor.GREEN + ranking[4] +" "+ ChatColor.GREEN + ranking[5]);
        score3.setScore(8);
        Score score4 = objective.getScore("§" + "6" +ChatColor.WHITE+ "  第4名: " + ChatColor.BLUE + ranking[6] +" "+ ChatColor.BLUE + ranking[7]);
        score4.setScore(8);
        Score score8 = objective.getScore("§" + "7" +ChatColor.WHITE+ "  第5名: " + ChatColor.LIGHT_PURPLE + ranking[8] +" "+ ChatColor.LIGHT_PURPLE + ranking[9]);
        score8.setScore(8);
        Score score9 = objective.getScore("§" + "8" +ChatColor.WHITE+ "感謝參與");
        score9.setScore(8);

        return board;
    }

}
