package jdbc;

import java.util.Calendar;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DB {
	private Connection db_connection = null;
	private Statement sql_stmt = null;
	private ResultSet rs = null;
    
	public static Connection getDBConnection() throws SQLException {
		Connection db_connection = null;
		String db_url = "jdbc:postgresql://localhost:5432/postgres";
		String db_usr = "postgres";
		String db_pswd = "password1";

		db_connection = DriverManager.getConnection(db_url, db_usr, db_pswd);
		return db_connection;
	}
    
    public void add(String username, String surname, String message, String login, String password, String lastEntry) throws SQLException {
		
		String insertTableSQL = "INSERT INTO userlist"
				+ "(username, surname, message, login, password, last_entry) "
				+ "VALUES" + "('" + username + "', '" + surname + "', '"
				+ message + "', '" + login + "', '" + password + "', '"
				+ lastEntry + "')";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		sql_stmt.executeUpdate(insertTableSQL);
        
    }
    
    public void addTestUsers() throws SQLException {
    	
		this.add("Arseniy", "Petrov", "На счастье!", "arsusha12", "asghjtrt",
				"2014-10-19 22:08:16");
		this.add("Alexey", "Ivanov", "message message long long", "alex",
				"qwerty", "2014-06-07 01:01:56");
		this.add("Volodya", "Gorkin", "love donuts", "21adas", "FS432fsad",
				"2014-07-07 06:17:49");
		this.add("Elena", "Zubova", "like", "lenka6",
				"dfhhdfg", "2014-12-02 11:12:11");
    	
    }
    
	public void printTable() throws SQLException {

		String selectTableSQL = "SELECT username, surname, message, login, password, last_entry from userlist";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		rs = sql_stmt.executeQuery(selectTableSQL);

		System.out
				.println("username     surname     message     login     password     last_entry");
		while (rs.next()) {
			String username = rs.getString("username");
			String surname = rs.getString("surname");
			String message = rs.getString("message");
			String login = rs.getString("login");
			String password = rs.getString("password");
			String last_entry = rs.getString("last_entry");

			System.out.println(username + "\t" + surname + "\t" + message
					+ "\t" + login + "\t" + password + "\t" + last_entry);
		}
		System.out.print("\n");

	}
    
    // 1
    public void printUsersMessages() throws SQLException{
        	
		String selectTableSQL = "SELECT message,login from userlist";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		rs = sql_stmt.executeQuery(selectTableSQL);

		System.out.println("login\tmessage");
		while (rs.next()) {
			String message = rs.getString("message");
			String login = rs.getString("login");
			System.out.println(login + "\t" + message);
		}
        
    }
    
    // 2
    public int UsersQuantity() throws SQLException{
        
		String selectTableSQL = "SELECT COUNT(*) as total FROM  userlist";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		rs = sql_stmt.executeQuery(selectTableSQL);
		int quantity = 0;
		while (rs.next()) {
			quantity = rs.getInt("total");
		}
		return quantity;
	        
    }
    
    // 3
    // 4
    public String topTen(String order) throws SQLException{
        
		String selectTableSQL = "SELECT * FROM userlist  ORDER BY  last_entry "
				+ order + " LIMIT 10 ";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		rs = sql_stmt.executeQuery(selectTableSQL);
		String username = "";
		String last_entry = "";
		String res = "";
		while (rs.next()) {
			username = rs.getString("username");
			last_entry = rs.getString("last_entry");
			res += username + "\t" + last_entry + "\n";
		}
		return res;

    }
    
    // 5
    public String login(String login, String password) throws SQLException{

		String selectTableSQL = "SELECT * FROM userlist WHERE login='" + login
				+ "' AND password='" + password + "'";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		rs = sql_stmt.executeQuery(selectTableSQL);
		String username = "";
		String message = "";
		if (rs.next()) {
			username = rs.getString("username");
			message = rs.getString("message");

			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			selectTableSQL = "UPDATE userlist SET last_entry = '"
					+ dateFormat.format(cal.getTime()) + "' WHERE login = '"
					+ login + "'";
			sql_stmt.execute(selectTableSQL);
			MainWindow.logged_in_user = login;
			return username + " вошел в систему. Сообщение: " + message;
		}
		return "";
   
    }
    
    // 7
    public void newMessage(String login, String message) throws SQLException{
    	
		String updateTableSQL = "UPDATE userlist SET message = '" + message
				+ "' WHERE login = '" + login + "'";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		sql_stmt.execute(updateTableSQL);

    }
    
    public void createTable() throws SQLException {
    	
		String createTableSQL = "CREATE TABLE userlist (\n"
				+ "	username varchar(80),\n	surname varchar(80),\n"
				+ "	message varchar(80),\n" + "	login varchar(80),\n"
				+ "	password varchar(80),\n" + "	last_entry timestamp\n" + ");";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		sql_stmt.execute(createTableSQL);
   
    }
    
    public void dropTable() throws SQLException {
    	
		String dropTableSQL = "DROP TABLE userlist;";
		db_connection = DB.getDBConnection();
		sql_stmt = db_connection.createStatement();
		sql_stmt.execute(dropTableSQL);

    }
}