package ExpenseApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import StoreManager.Home;

public class ExpenseAdmin {

	public void ExAdmin() throws ClassNotFoundException {
		// TODO Auto-generated method stub

		ExpenseConnection con = new ExpenseConnection();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the UserName");
		String adminUsername = s.next();
		System.out.println("Enter the UserName");
		String adminPassword = s.next();

		// checking the admin credentials
		
		String query2 = "SELECT * FROM `admintable` WHERE `USER_NAME` =? AND `PASSWORD` =? AND `STATUS`=?";

		try {
			PreparedStatement pt = (PreparedStatement) con.getConnection().prepareStatement(query2);

			pt.setString(1, adminUsername);
			pt.setString(2, adminPassword);
			pt.setInt(3, 1);

			ResultSet rs = pt.executeQuery();
			if (rs.next()) {
				System.out.println("Welcome " + adminUsername);

				
				//after the validation display above options 
				
				int op;
				do {
					System.out.println(
							"1.Add Expense Categories\n2.View Categories\n3.Remove Categories\n4.Update\n5 User Counts\n6.Logout");
					System.out.println("Enter the Choice");
					op = s.nextInt();
					switch (op) {
					case 1:
						System.out.println("Welcome Admin");
						System.out.println("*****Add Categories to our ExpenseApp******");
						System.out.println("Enter the ID");
						int id = s.nextInt();
						System.out.println("Enter the category Name");
						String name = s.next();

						//insert category and details to the options table
						
						PreparedStatement sql = (PreparedStatement) con.getConnection()
								.prepareStatement("insert into options(Id,NAME) VALUES (?,?)");
						sql.setInt(1, id);
						sql.setString(2, name);

						sql.executeUpdate();
						System.out.println("New Category Add Successfully");
						break;
					case 2:
						System.out.println("Welcome Admin");
						System.out.println("*****LIST OF PRODUCTS*******");
						
						//view categories, read from options table
						
						Statement st = (Statement) con.getConnection().createStatement();
						ResultSet rs3 = st.executeQuery("select * from options");
						while (rs3.next()) {
							System.out.println("category ID :" + " " + rs3.getString(1) + "\n" + "category Name :" + " "
									+ rs3.getString(2));
							System.out.println("\n*****************************");
						}
						break;
					case 3:
						System.out.println("Welcome Admin");
						System.out.println("Enter the id for deleting category");
						int idd = s.nextInt();
						
						//deleting any category from table
						
						String query = "DELETE FROM `options` WHERE ID=?";
						PreparedStatement Stmt = (PreparedStatement) con.getConnection().prepareStatement(query);
						Stmt.setInt(1, idd);
						Stmt.executeUpdate();
						System.out.println("category remove successfully");
						break;
					case 4:

						System.out.println("Welcome Admin");
						System.out.println("*****LIST OF CATEGORIES*******");

						//updating old category names
						
						
						Statement stdi = (Statement) con.getConnection().createStatement();
						ResultSet rsd = stdi.executeQuery("select * from options");
						while (rsd.next()) {
							System.out.println("Category ID :" + " " + rsd.getString(1) + "\n" + "category Name :" + " "
									+ rsd.getString(2));
							System.out.println("\n*****************************");
						}

						System.out.println("Enter the id for updating");
						int upid = s.nextInt();

						int up = 0, flag1 = 0, up2 = 0, up3 = 0;
						String up1 = null;
						Statement st5 = (Statement) con.getConnection().createStatement();
						ResultSet rsp5 = st5.executeQuery("select * from options");

						while (rsp5.next()) {
							up = rsp5.getInt(1);
							up1 = rsp5.getString(2);

							if (up == upid) {

								flag1 = 1;

							}

						}

						if (flag1 == 1) {
							System.out.println("Enter the update category");
							String newcategory = s.next();
							String query1 = "update options set NAME = ? where ID = ?";
							PreparedStatement preparedStmt = (PreparedStatement) con.getConnection()
									.prepareStatement(query1);
							preparedStmt.setString(1, newcategory);
							preparedStmt.setInt(2, upid);
							preparedStmt.executeUpdate();
							System.out.println("Update Category Successfully");

							break;
						}
						break;

					case 5:
						int count=0;
						Statement st07 = (Statement) con.getConnection().createStatement();
            			ResultSet rs07 = st07.executeQuery("select * from admintable");
						while (rs07.next()) {
							count=rs07.getInt(1);
						}
							System.out.println("The Active Members of ExpenseApp :"+(count-1));
						break;
					case 6:
						
						//go back to the main menu
						
						ExpenseAppHome back = new ExpenseAppHome();
						back.Home();
						break;

					}
				} while (op == 1 || op == 2 || op == 3 || op == 4 || op == 5);

			} else {
				System.out.println("Incorrect Username Or Password");
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

}
