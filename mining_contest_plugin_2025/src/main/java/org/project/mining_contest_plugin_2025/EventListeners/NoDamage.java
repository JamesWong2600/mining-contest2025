package org.project.mining_contest_plugin_2025.EventListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NoDamage implements Listener {


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
       // if(!(Mine2024.status==2)){
       //     if (event.getDamager() instanceof Player) {
       //         Player victim = (Player) event.getEntity();
        //        victim.setHealth(20);
      //      }
      //  }
      //  else{
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            event.setCancelled(true);
            damager.sendMessage("本次比賽不允許傷害其他玩家");
      //   }
               }
    }
}