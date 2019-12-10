package StoreManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class StoreAgent {

	public void Agent() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		StoreConnection conn = new StoreConnection();
		double price = 0, tot = 0;
		int q = 0, bal = 0;
		System.out.println("Welcome ----");
		System.out.println("Enter user name");
		String uname = s.next();
		System.out.println("Enter password");
		String pass = s.next();
		String query2 = "SELECT * FROM `agentlogin` WHERE `Username` =? AND `Password` =?";

		try {
			PreparedStatement pt = (PreparedStatement) conn.getConnection().prepareStatement(query2);

			pt.setString(1, uname);
			pt.setString(2, pass);

			ResultSet rs = pt.executeQuery();
			if (rs.next()) {
				System.out.println("Welcome " + uname);
				int op;
				do {
					System.out.println("1.BuySell\n2.View Products\n3.HomePage");
					System.out.println("Enter the Choice");
					op = s.nextInt();
					switch (op) {
					case 1:
						String yes = null,yes1=null;
						do {
							System.out.println("-----Welcome----");
							System.out.println("Enter the Id");
							int pid = s.nextInt();
							System.out.println("Enter Quantity");
							int quan = s.nextInt();
							int id2 = 0;
							Statement st4 = (Statement) conn.getConnection().createStatement();
							ResultSet rsp = st4.executeQuery("select * from addproduct");

							while (rsp.next()) {
								id2 = rsp.getInt(1);
								if (id2 == pid) {
									price = rsp.getInt(4);
									q = rsp.getInt(3);
									if(quan>q)
									{
										System.out.println("Out of Stock  !"+"\nAvailable Stock :"+q);
										System.exit(0);
										/*System.out.println("Do you want to continue ?");
										yes1 = s.next();
										if(yes1.contentEquals("yes"))
										{
											StoreAgent agent=new StoreAgent();
											agent.Agent();
										}
										else
										{
										System.exit(0);
										}*/
									}
									else
									{
									System.out.println("Your Total Price :" + (price * quan));

									bal = q - quan;
									}
								}

							}
							System.out.println("Continue with order?");
							yes = s.next();
							if (yes.contentEquals("yes")) {
								String query1 = "update addproduct set Quantity = ? where ID = ?";
								PreparedStatement preparedStmt = (PreparedStatement) conn.getConnection()
										.prepareStatement(query1);
								preparedStmt.setInt(1, bal);
								preparedStmt.setInt(2, pid);
								preparedStmt.executeUpdate();
								System.out.println("Thank you for Purchasing...!");
							}
						} while (yes.equals("no"));

						break;
					case 2:
						System.out.println("\n*********LIST OF PRODUCTS********************");
						Statement st = (Statement) conn.getConnection().createStatement();
						ResultSet rse = st.executeQuery("select * from addproduct");
						while (rse.next()) {
							System.out.println("Product ID :"+" "+rse.getInt(1) + "\n" +"Product Name :"+" "+ rse.getString(2) + "\n" +"Product Quantity :"+" "+ rse.getInt(3) + "\n"
									+"Product Price :"+" "+ rse.getInt(4));
							System.out.println("\n*****************************");
						}
						break;
					case 3:
						Home back = new Home();
						back.home();
						break;

					}

				} while (op == 1 || op == 2 || op == 3);

			} else {
				System.out.println("Incorrect Username Or Password");
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

}
