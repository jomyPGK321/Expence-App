package ExpenseApp;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ExpenseConnection {

	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

       Class.forName("com.mysql.jdbc.Driver"); // load driver
		
		
		Connection con=null; // create connection object
		
		                //class.method            //("API:database://servername:portnumber/databasename","username","psw");
		con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ExpenseApp","root","");  // create connection
		
		// checking connection
		if(con!=null)
		{
			return con;
		}
		else
		{
			return null;}
		
	}

}
