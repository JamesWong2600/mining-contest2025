package org.project.mining_contest_plugin_2025.TASK;

import org.bukkit.*;
import org.project.mining_contest_plugin_2025.Mining_contest_plugin_2025;

public class CreateGame {

    public static void Create() {
        int sz = Mining_contest_plugin_2025.getMain().getConfig().getInt("border.size");
        World world = Bukkit.getServer().getWorld("world");
        world.getWorldBorder().setSize(sz*2, 1);
        Thread thread = new Thread() {
            public void run() {
                Chunk chunk = null;
                for (int x = -(Math.round(sz/16)); x < (Math.round(sz/16)); x++) // whole chunk
                {
                    for (int z = -(Math.round(sz/16)); z < (Math.round(sz/16)); z++) // whole chunk
                    {
                        Location loc = new Location(world,x*16,0,z*16);
                        chunk = loc.getChunk();
                        System.out.println(chunk);
                    }
                }
            }
        };
       //thread.start();

        // }
        for (int x = -20; x < 20; x++) // whole chunk
        {
            for (int z = -20; z < 20; z++) // whole chunk
            {
                for (int y = 250; y < 265; y++) // any level between 1 - 40
                {
                    Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
                    loc.getBlock().setType(Material.GLASS);
                }
            }
        }
        for (int x = -19; x < 19; x++) // whole chunk
        {
            for (int z = -19; z < 19; z++) // whole chunk
            {
                for (int y = 251; y < 264; y++) // any level between 1 - 40
                {
                    Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
                    loc.getBlock().setType(Material.AIR);
                }
            }
        }
        for (int x = -25; x < 25; x++) // whole chunk
        {
            for (int z = -25; z < 25; z++) // whole chunk
            {

                    Location loc = new Location(Bukkit.getWorld("world"), x, 120, z);
                    loc.getBlock().setType(Material.GLASS);
            }
        }
        System.out.println("avaliabe to join");
    }
}
