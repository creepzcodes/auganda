package de.auganda.listener;

import de.auganda.command.TogglepvpCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mineacademy.fo.annotation.AutoRegister;

@AutoRegister
public final class ToggleListener implements Listener {

    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player target)) return; // Check if the victim is a player
        if (!(e.getDamager() instanceof Player attacker)) return; // Check if the attacker is a player (e.g. not a mob)

        // Get PvP status from attacker and victim
        boolean aPvP = TogglepvpCommand.isPvPEnabled(attacker);
        boolean tPvP = TogglepvpCommand.isPvPEnabled(target);


        if (!aPvP || !tPvP) { //Only allows PvP if both have it enabled
            e.setCancelled(true);
            if (!aPvP) {
                attacker.sendMessage("§cDu hast PvP deaktiviert und kannst niemanden angreifen.");
            } else if (!tPvP) {
                attacker.sendMessage("§cDer Spieler hat PvP deaktiviert und kann nicht angegriffen werden.");
            }
        }
    }
}
