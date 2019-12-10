package StoreManager;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class StoreConnection {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

       Class.forName("com.mysql.jdbc.Driver"); // load driver
		
		
		Connection conn=null; // create connection object
		
		                //class.method            //("API:database://servername:portnumber/databasename","username","psw");
		conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Store","root","");  // create connection
		
		// checking connection
		if(conn!=null)
		{
			return conn;
		}
		else
		{
			return null;}
		
}
}
