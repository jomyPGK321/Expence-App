package StoreManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class StoreAdmin {

	public void AddProduct() throws ClassNotFoundException {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		int newq = 0, updateQuan = 0, flag1 = 0, flag2 = 0, flag3 = 0;
		StoreConnection conn = new StoreConnection();
		System.out.println("Welcome ----");
		System.out.println("Enter user name");
		String uname = s.next();
		System.out.println("Enter password");
		String pass = s.next();
		String query2 = "SELECT * FROM `adminlogin` WHERE `Username` =? AND `Password` =?";

		try {
			PreparedStatement pt = (PreparedStatement) conn.getConnection().prepareStatement(query2);

			pt.setString(1, uname);
			pt.setString(2, pass);

			ResultSet rs = pt.executeQuery();
			if (rs.next()) {
				System.out.println("Welcome " + uname);
				int op;
				do {
					System.out.println("1.Add Product\n2.View Products\n3.Remove\n4.Update\n5.Logout");
					System.out.println("Enter the Choice");
					op = s.nextInt();
					switch (op) {
					case 1:
						System.out.println("Welcome Admin");
						System.out.println("*****Add Products To your Store******");
						System.out.println("Enter the ID");
						int id = s.nextInt();
						System.out.println("Enter the Product Name");
						String name = s.next();
						System.out.println("Enter the Quantity");
						int quantity = s.nextInt();
						System.out.println("Enter the Price");
						int price = s.nextInt();
						PreparedStatement sql = (PreparedStatement) conn.getConnection()
								.prepareStatement("insert into addproduct(Id,Name,Quantity,Price) VALUES (?,?,?,?)");
						sql.setInt(1, id);
						sql.setString(2, name);
						sql.setInt(3, quantity);
						sql.setInt(4, price);
						sql.executeUpdate();
						System.out.println("Your Product Add Successfully");
						break;
					case 2:
						System.out.println("Welcome Admin");
						System.out.println("*****LIST OF PRODUCTS*******");
						Statement st = (Statement) conn.getConnection().createStatement();
						ResultSet rs3 = st.executeQuery("select * from addproduct");
						while (rs3.next()) {
							System.out.println("Product ID :" + " " + rs3.getString(1) + "\n" + "Product Name :" + " "
									+ rs3.getString(2) + "\n" + "Product Quantity :" + " " + rs3.getString(3) + "\n"
									+ "Product Price :" + " " + rs3.getString(4));
							System.out.println("\n*****************************");
						}
						break;
					case 3:
						System.out.println("Welcome Admin");
						System.out.println("Enter the id for deleting");
						int idd = s.nextInt();
						String query = "DELETE FROM `addproduct` WHERE ID=?";
						PreparedStatement Stmt = (PreparedStatement) conn.getConnection().prepareStatement(query);
						Stmt.setInt(1, idd);
						Stmt.executeUpdate();
						System.out.println("Product remove successfully");
						break;
					case 4:

						System.out.println("Welcome Admin");
						System.out.println("*****LIST OF PRODUCTS*******");

						Statement stdi = (Statement) conn.getConnection().createStatement();
						ResultSet rsd = stdi.executeQuery("select * from addproduct");
						while (rsd.next()) {
							System.out.println("Product ID :" + " " + rsd.getString(1) + "\n" + "Product Name :" + " "
									+ rsd.getString(2));
							System.out.println("\n*****************************");
						}

						System.out.println("Enter the id for updating");
						int upid = s.nextInt();
						System.out.println("Which field want you update");
						System.out.println("1.Price\n2.quantity\n3.Name");
						int item = s.nextInt();

						int up = 0, up2 = 0, up3 = 0;
						String up1 = null;
						Statement st5 = (Statement) conn.getConnection().createStatement();
						ResultSet rsp5 = st5.executeQuery("select * from addproduct");

						while (rsp5.next()) {
							up = rsp5.getInt(1);
							up1 = rsp5.getString(2);
							up2 = rsp5.getInt(3);
							up3 = rsp5.getInt(4);
							if (up == upid && item == 1) {

								flag1 = 1;

							}
							if (up == upid && item == 2) {
								
								newq = rsp5.getInt(3);

								
								flag2 = 1;

							}
							if (up == upid && item == 3) {

								flag3 = 1;

							}

						}

						if (flag2 == 1) {
							System.out.println("Enter the number of products");
							int addp = s.nextInt();
							updateQuan = newq + addp;
							String query1 = "update addproduct set Quantity = ? where ID = ?";
							PreparedStatement preparedStmt = (PreparedStatement) conn.getConnection()
									.prepareStatement(query1);
							preparedStmt.setInt(1, updateQuan);
							preparedStmt.setInt(2, upid);
							preparedStmt.executeUpdate();
							System.out.println("Add quantity Successfully");
							break;
						}

						if (flag3 == 1) {
							System.out.println("Enter the update name");
							String name3 = s.next();
							String query1 = "update addproduct set Name = ? where ID = ?";
							PreparedStatement preparedStmt = (PreparedStatement) conn.getConnection()
									.prepareStatement(query1);
							preparedStmt.setString(1, name3);
							preparedStmt.setInt(2, upid);
							preparedStmt.executeUpdate();
							System.out.println("Name update Successfully");
							break;
						}
						if (flag1 == 1) 
						{
							System.out.println("Enter the update price");
							int priceup = s.nextInt();
							String query1 = "update addproduct set Price = ? where ID = ?";
							PreparedStatement preparedStmt = (PreparedStatement) conn.getConnection()
									.prepareStatement(query1);
							preparedStmt.setInt(1, priceup);
							preparedStmt.setInt(2, upid);
							preparedStmt.executeUpdate();
							System.out.println("Update Price Successfully");
							break;
						}
						break;

					case 5:
						Home back = new Home();
						back.home();
						break;

					}
				} while (op == 1 || op == 2 || op == 3 || op == 4 || op == 5);
			}

			else {
				System.out.println("Incorrect Username Or Password");
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

}
