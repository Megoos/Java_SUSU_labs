package jdbc;

import java.sql.SQLException;

import junit.framework.TestCase;

public class Tests extends TestCase {
	
	DB database = new DB();

	protected void init() throws Exception {
		super.setUp();
		database.dropTable();
		database.createTable();
		database.addTestUsers();
	}
	
	public void usersdidntlogin() throws SQLException{
		assertEquals("Alexey	2014-06-07 01:01:56" + "\nVolodya	2014-07-07 06:17:49" + "\nArseniy	2014-10-19 22:08:16" + "\nElena	2014-12-02 11:12:11\n", database.topTen("asc"));
	}
	
	public void loginmsg() throws SQLException	{
		assertEquals("Alexey вошел в систему. Сообщение: message message long long", database.login("alex", "qwerty"));
	}
	
	public void numbofusers() throws SQLException {		
		assertEquals(4, database.UsersQuantity());
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}