package plus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class Sqlite {
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;

	public static void Conn() throws ClassNotFoundException, SQLException {
		   Class.forName("org.sqlite.JDBC");
		   
		   conn = DriverManager.getConnection("jdbc:sqlite:data.s3db");
		   statmt = conn.createStatement();
		   System.out.println("База данных подключена");
	}
	
	public static void CreateDB() throws ClassNotFoundException, SQLException {
		statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'world' text, 'x' int, 'y' int, 'z' int);");
	}

	public static void saveData(Player player, Location loc) throws SQLException {
		
		statmt.execute(String.format("DELETE FROM 'users' WHERE name = '%s' ", player.getName()));
		statmt.execute(String.format("INSERT INTO 'users' ('name', 'world', 'x', 'y', 'z') VALUES ('%s', '%s', '%s', '%s', '%s')", 		
				player.getName(), 
				loc.getWorld().getName(),
				String.valueOf(loc.getBlockX()),
				String.valueOf(loc.getBlockY()),
				String.valueOf(loc.getBlockZ())
				
				)
			);
	}
	
	public static void removeData(Player player, Location loc) throws SQLException {
		statmt.execute(String.format("DELETE FROM 'users' WHERE name = '%s' ", player.getName()));
	}
	
	public static Location getSavedHome(Player player) throws SQLException{
		
		ResultSet result = statmt.executeQuery(String.format("SELECT * FROM users WHERE name = '%s' ", player.getName()));
		
		Location loc = new Location(
				Bukkit.getServer().getWorld(result.getString("world")), 
				result.getInt("x"), 
				result.getInt("y"), 
				result.getInt("z")
				);
		
		return loc;
		
	}

	public static void CloseDB() throws ClassNotFoundException, SQLException{
		conn.close();
		statmt.close();
		resSet.close();
			
		System.out.println("База данных выключена");
	}
}