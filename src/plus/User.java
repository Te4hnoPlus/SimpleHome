package plus;

import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User {
	private Player player;
	private Location location;
	private boolean hashome;
	
	public User(Player player) {
		this.player = player;
		hashome = false;
	}

	public Location getHome() {
		return location;
	}

	public void setHome(Location location) {
		
		this.location = location;
		
		if(!hashome) {
			hashome = true;
		}
	}
	
	public boolean HasHome() {
		return hashome;
	}
	
	public void removeHome() {
		hashome = false;
	}
	
	public void loadData() {
		
		try {
			location = Sqlite.getSavedHome(player);
			hashome = true;
		} catch (SQLException e) {
			hashome = false;
		}
	}
	
	public void saveData() {
		try {
			if(hashome) {
				Sqlite.saveData(player, location);
			} else {
				Sqlite.removeData(player, location);
			}
			
		} catch (SQLException e) {
		}
		
	}
}
