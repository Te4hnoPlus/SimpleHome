package plus;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Floder_User {
private static HashMap<Player, User> data = new HashMap<>();
	
	public static void addUser(Player player) {
		
		if(data.containsKey(player)) {
			return;
		}
		
		User user = new User(player);
		user.loadData();
		
		data.put(player, user);
		
	}
	
	public static User getUser(Player player) {
		
		return data.get(player);
	}
	public static void saveAllData() {
		System.out.println("Сохранение даты");
		for (User user : data.values()) {
		    user.saveData();
		}
	}
	
}