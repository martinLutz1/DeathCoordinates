package xaronox.deathcoordinates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    
    private String getWorld(Player player) {
        Environment playerEnvironment = player.getWorld().getEnvironment();
        
        if (playerEnvironment == Environment.NORMAL) {
            return "World";
        }
        else if (playerEnvironment == Environment.NETHER) {
            return "Nether";
        }
        else if (playerEnvironment == Environment.THE_END) {
            return "End";
        }
        else {
            return "Unknown environment";
        }
    }
     
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        
        Location deadPlayerLocation = deadPlayer.getLocation();
        int x = deadPlayerLocation.getBlockX();
        int y = deadPlayerLocation.getBlockY();
        int z = deadPlayerLocation.getBlockZ();
        
        // Death message for the player.
        String deathMessagePlayer = 
                "You died at " + 
                ChatColor.AQUA + x + " " + y + " " + z + " " +
                ChatColor.AQUA + "(" + getWorld(deadPlayer) + ")";
        deadPlayer.sendMessage(deathMessagePlayer);
        
        // Death message for the console (logging).
        String deathMessageConsole = 
                deadPlayer.getName() + " died at " + 
                x + " " + y + " " + z + " " +
                "(" + getWorld(deadPlayer) + ")";
        getLogger().info(deathMessageConsole);
    }
    
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }
   
    @Override
    public void onDisable() {
        // Nothing to do :)
    }
}
