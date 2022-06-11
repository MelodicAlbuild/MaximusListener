package net.melodicalbuild.maximuslistener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.logging.Logger;

public final class MaximusListener extends JavaPlugin implements PluginMessageListener {

    private Logger log = Bukkit.getLogger();

    @Override
    public void onEnable() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "melodicalbuild:return", this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));

        try {
            String sub = in.readUTF(); // Sub-Channel
            if (sub.equals("command")) { // As in bungee part we gave the sub-channel name "command", here we're checking it sub-channel really is "command", if it is we do the rest of code.
                String cmd = in.readUTF(); // Command we gave in Bungee part.
                log.info("[Maximus Listener] Received a command message from BungeeCord, executing it.");
                getServer().dispatchCommand(getServer().getConsoleSender(), cmd); // Executing the command!!

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
