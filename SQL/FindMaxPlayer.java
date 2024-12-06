package org.project.mining_contest_plugin_2025.SQL;

import java.sql.*;

public class FindMaxPlayer {
    public static String[] MaxPlayer()
    {
        String[] SQLDATA = SQLcollection.SQL();
        String[] ranking = new String[10];
        try(Connection connn = DriverManager.getConnection(SQLDATA[1], SQLDATA[2], SQLDATA[3]);
            Statement stmt1 = connn.createStatement();
            Statement stmt2 = connn.createStatement();
            Statement stmt3 = connn.createStatement();
            Statement stmt4 = connn.createStatement();
            Statement stmt5 = connn.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from datafile where point = (select max(point) from datafile)");
            ResultSet rs2 = stmt2.executeQuery("select * from datafile order by point desc limit 1 , 1");
           ResultSet rs3 = stmt3.executeQuery("select * from datafile order by point desc limit 2 , 1");
            ResultSet rs4 = stmt4.executeQuery("select * from datafile order by point desc limit 3 , 1");
            ResultSet rs5 = stmt5.executeQuery("select * from datafile order by point desc limit 4 , 1");
        ) {
            rs1.next();
            ranking[0] = rs1.getString("player");
            ranking[1] = rs1.getString("point");
            rs2.next();
            ranking[2] = rs2.getString("player");
            ranking[3] = rs2.getString("point");
            rs3.next();
            ranking[4] = rs3.getString("player");
            ranking[5] = rs3.getString("point");
            rs4.next();
            ranking[6] = rs4.getString("player");
            ranking[7] = rs4.getString("point");
            rs5.next();
            ranking[8] = rs5.getString("player");
            ranking[9] = rs5.getString("point");
            return ranking;

        } catch (SQLException e) {
            //CreateTable.SQLCreateTable();
        }
        return SQLDATA;
    }
}
