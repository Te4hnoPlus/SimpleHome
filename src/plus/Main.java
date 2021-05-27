package plus;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		try {
			Sqlite.Conn();
			Sqlite.CreateDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		
		getCommand("home").setExecutor(this);
		getCommand("sethome").setExecutor(this);
		getCommand("delhome").setExecutor(this);
		
	}
	public void onDisable() {
		Floder_User.saveAllData();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		User user = Floder_User.getUser(player);
		
		if(label.equals("home")) {
			if(user.HasHome()) {
				player.teleport(user.getHome());
			}
		} else if(label.equals("sethome")) {
			user.setHome(player.getLocation());
		} else if(label.equals("delhome")) {
			user.removeHome();
		}
		
		return true;
		
	}
}
