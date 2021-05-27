package plus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{
	
	@EventHandler
	public void onConnect(PlayerJoinEvent ev) {
		Player Joiner = ev.getPlayer();
		
		try {
			Floder_User.addUser(Joiner);
	
		} catch (Exception error) {
			
		}
	}
}