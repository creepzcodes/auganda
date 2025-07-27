package de.auganda.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AutoRegister
public final class TogglepvpCommand extends SimpleCommand {

    private static final Map<UUID, Boolean> pvpStatus = new HashMap<>();  // Here we store for each player (UUID) whether PvP is enabled

    public TogglepvpCommand() {
        super("togglepvp");
    }

    @Override
    protected void onCommand() {
        checkConsole(); // Only players can execute this command

        Player player = getPlayer();
        UUID uuid = player.getUniqueId();
        boolean enabled = pvpStatus.getOrDefault(uuid, false); //first join = PvP not active

        enabled = !enabled; // Toggle status: enabled -> disabled, or vice versa
        pvpStatus.put(uuid, enabled); // Update PvP status in the map

        String message = enabled ? "§a§lPvP wurde aktiviert." : "§c§lPvP wurde deaktiviert.";
        player.sendMessage(message);
    }

    // Returns whether a player has PvP enabled.
    public static boolean isPvPEnabled(Player player) {
        return pvpStatus.getOrDefault(player.getUniqueId(), true);
    }
}
