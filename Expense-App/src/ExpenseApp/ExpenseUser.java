package ExpenseApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ExpenseUser {

	public void ExUser() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ExpenseConnection con=new ExpenseConnection();
		Scanner s=new Scanner(System.in);
		System.out.println("1.Signin\n2.Signup");
		int choice=s.nextInt();
		
		//if already have an account then go to user login
		
		if(choice==1)
		{
			System.out.println("Enter user name");
			String userName=s.next();
			System.out.println("Enter password");
			String userPassword=s.next();
			 String query2 = "SELECT * FROM `admintable` WHERE `USER_NAME` =? AND `PASSWORD` =? AND `STATUS`=?";
		        
		        try {
		        	PreparedStatement pt1 =  (PreparedStatement) con.getConnection().prepareStatement(query2);
		            
		            pt1.setString(1, userName);
		            pt1.setString(2, userPassword);
		            pt1.setInt(3, 0);
		            
		            ResultSet rs1  = pt1.executeQuery();
		            if(rs1.next())
		            {
		            	System.out.println("Welcome "+userName );  
		                  
		            	
		            	//after login user can access below options
		            	
		            	
		            int op2,exid=0,uid=0;
            			
		            	do
		            	{
		            		System.out.println("0.Auto upload\n1.Add Expense\n2.Expense History\n3.Report\n4.Expense per date\n5.Delete Any expense\n6.Update Any Expense\n7.Exit");
			            	System.out.println("Enter the Choice");
		            		op2=s.nextInt();
		            		int cccidd=0;
			            	Statement st07 = (Statement) con.getConnection().createStatement();
	            			ResultSet rs07 = st07.executeQuery("select * from admintable");
							while (rs07.next()) {
								if(rs07.getString(2).contentEquals(userName)) {
								cccidd=rs07.getInt(4);
							}
								}
		            		
		            		switch(op2)
		            		{
		            		
		            		
		            		
		            		case 0:
		            			System.out.println("Sorry you are'nt connected....\nCheck your internet connection");
		            			
		            			break;
		            		case 1:
		            			System.out.println("********************\n");
		            			System.out.println("Welcome to the ExpenseApp......");
		            			Statement st = (Statement) con.getConnection().createStatement();
								ResultSet rs3 = st.executeQuery("select * from options");
								while (rs3.next()) {
									exid=rs3.getInt(1);
									System.out.println("category ID :" + " " + rs3.getString(1) + "\n" + "category Name :" + " "
											+ rs3.getString(2));  
									System.out.println("\n*****************************");
								}
								System.out.println("Hai "+userName+"\n"+"Your Account No :"+cccidd);
								System.out.println("Enter your Account Id for confirmation");
								uid=s.nextInt();
									System.out.println("Enter your Category id");
									int newid=s.nextInt();
									System.out.println("Bill Number as ID");
									int billid=s.nextInt();
									System.out.println("Providing Purchase name");
									String pname=s.next();
									System.out.println("Providing Purchasing date yyyy-mm-dd");
									String date=s.next();
									System.out.println("Purchase Amount");
									int amount=s.nextInt();
									
									PreparedStatement sql3 = (PreparedStatement) con.getConnection()
											.prepareStatement("insert into expense(ID,NAME,CATEGORY,DATE,AMOUNT,USERID) VALUES (?,?,?,?,?,?)");
									sql3.setInt(1, billid);
									sql3.setString(2, pname);
									sql3.setInt(3, newid);
									sql3.setString(4, date);
									sql3.setInt(5, amount);
									sql3.setInt(6, uid);
									
									sql3.executeUpdate();
									System.out.println("New Expense details Add Successfully");
									
								break;
		            		case 2:
		            			int id2=0,id3=0,price;
		            			double total=0;
		            			String dt=null;
		            			
		            			
		            			Statement st5 = (Statement) con.getConnection().createStatement();
		            			ResultSet rs5 = st5.executeQuery("select * from options");
								while (rs5.next()) {
									exid=rs5.getInt(1);
									System.out.println("category ID :" + " " + rs5.getString(1) + "category Name :" + " "
											+ rs5.getString(2));  
									System.out.println("\n*****************************");
								}
								
								System.out.println("Hai "+userName+"\n"+"Your Account No :"+cccidd);
								System.out.println("Enter your Account Id for confirmation");
		            			int authenid0=s.nextInt();
		            			System.out.println("Choose your Wanted category\n use Above category id ");
								int hid=s.nextInt();
								Statement st4 = (Statement) con.getConnection().createStatement();
								ResultSet rsp4 = st4.executeQuery("select * from expense");

								while (rsp4.next()) {
									id2 = rsp4.getInt(3);
									id3=rsp4.getInt(6);
									if (id2 == hid && authenid0 == id3) {
										price = rsp4.getInt(5);
										dt= rsp4.getString(4);
										System.out.println("Expense :"+price +"of date :"+dt);
										total=total+price;
										
										
									}

								}
								System.out.println("Total Expense :"+total);
								break;
		            		case 3:
		            			System.out.println("Do you want to konw your balancesheet");
		            			String yes=s.next();
		            			
		            			System.out.println("Hai "+userName+"\n"+"Your Account No :"+cccidd);
		            			System.out.println("Enter your Account name");
		            			String authen=s.next();
								System.out.println("Enter your Account Id for confirmation");
		            			int authenid=s.nextInt();
		            			String auth=null;
		            			int au=0,au1=0,auth1=0;
		            			int userincome=0;double ev=0;
		            			Statement st9 = (Statement) con.getConnection().createStatement();
		            			ResultSet rs9 = st9.executeQuery("select * from expense");
								while (rs9.next()) {
									auth1=rs9.getInt(6);
									
									if(authenid == auth1 )
									{
										au1=rs9.getInt(5);
										ev=ev+au1;
										
									
								}
									}
		            			if(yes.contentEquals("yes"))
		            			{
		            				Statement st7 = (Statement) con.getConnection().createStatement();
			            			ResultSet rs7 = st7.executeQuery("select * from userdata");
									while (rs7.next()) {
										auth=rs7.getString(2);
										au=rs7.getInt(1);
										if(auth.contentEquals(authen) && authenid == au )
										{
											userincome=rs7.getInt(4);
											
										
									}
										}
									if(ev<userincome)
									{
										System.out.println("********************\n");
										System.out.println("Until now you are saving person\n");
										System.out.println("Your Income :"+userincome);
										
										System.out.println("Your Expense :"+ev);
										
										System.out.println("Keep it Up...\n");
										System.out.println("********************");
										
									}
									else if(ev>userincome)
									{
										System.out.println("********************\n");
										System.out.println(" Your Expense is little bit high...");
										System.out.println("Thank you\n");
										System.out.println("********************");
									}
									else if(ev==0)
										
									{
										System.out.println("********************\n");
										System.out.println("Until Now no expense\n");
										System.out.println("********************\n");
									}
									else
									{
										System.out.println();
									}
		            			}
		            			break;
		            		
		            		case 4:
		            			System.out.println("....Expense for a particular date...");
		            			System.out.println("Hai "+userName+"\n"+"Your Account No :"+cccidd);
								System.out.println("Enter your Account Id for confirmation");
		            			int comonid=s.nextInt();
		            			
		            			System.out.println("Enter your date");
		            			String month=s.next();
		            			String month1=null;
		            			int i = 0,ccid=0;
								double ex=0,monthex=0;
								double ex1=0,monthex1=0;
		            			Statement st10 = (Statement) con.getConnection().createStatement();
		            			ResultSet rs10 = st10.executeQuery("select * from expense");
								while (rs10.next()) {
									month1=rs10.getString(4);
									ccid=rs10.getInt(6);
									String[] data = month1.split(" "); // store strings ton the array word by word
									
									for (i = 0; i < data.length; i++) // here the index star from 0
									{
										//System.out.println(data[i]);	
										if(data[i].contentEquals(month) && ccid==comonid )
										{
											//Statement st11 = (Statement) con.getConnection().createStatement();
					            			//ResultSet rs11 = st11.executeQuery("select * from expense");
											//while (rs11.next()) {
												ex=rs10.getInt(5);
											monthex=monthex+ex;
											
											//}
											
												
										}
									}
									
									
								}System.out.println("********************\n");
								System.out.println("Your expense of date "+month+" is "+monthex+"\n");
								System.out.println("********************\n");
									
									
		            			break;
		            		
		            		case 5:
		            			int f=0;
		            			System.out.println("Hai "+userName+"\n"+"Your Account No :"+cccidd);
		            			System.out.println("Enter your id for confirmation");
		            			int dddd=s.nextInt();
								System.out.println("Enter the bill id for deleting ");
								int idde = s.nextInt();
								int delete=0;
								Statement st99 = (Statement) con.getConnection().createStatement();
		            			ResultSet rs99 = st99.executeQuery("select * from expense");
								while (rs99.next()) {
									delete=rs99.getInt(6);
									
									if(delete == dddd && idde==rs99.getInt(1) )
									{
										String query = "DELETE FROM `expense` WHERE ID=?";
										PreparedStatement Stmt9 = (PreparedStatement) con.getConnection().prepareStatement(query);
										Stmt9.setInt(1, idde);
										Stmt9.executeUpdate();
										f=1;
										System.out.println("Bill remove successfully");
									
								}
									}
								
								
								
		            			break;
		            		case 6:
		            			
		            			int f1=0;
		            			System.out.println("Hai "+userName+"\n"+"Your Account No :"+cccidd);
		            			System.out.println("Enter your id for confirmation");
		            			int dddd1=s.nextInt();
								System.out.println("Enter the bill id for deleting ");
								int idde1 = s.nextInt();
								int delete1=0;
								Statement st999 = (Statement) con.getConnection().createStatement();
		            			ResultSet rs999 = st999.executeQuery("select * from expense");
								while (rs999.next()) {
									delete1=rs999.getInt(6);
									
									if(delete1 == dddd1 && idde1==rs999.getInt(1) )
									{
										System.out.println("Enter the update amount");
										int amountupdate = s.nextInt();
										String query19 = "update expense set AMOUNT = ? where ID = ?";
										PreparedStatement preparedStmt9 = (PreparedStatement) con.getConnection()
												.prepareStatement(query19);
										preparedStmt9.setInt(1, amountupdate);
										preparedStmt9.setInt(2, idde1);
										preparedStmt9.executeUpdate();
										//f1=1;
										System.out.println("Bill update successfully");
									
								}
									
									}
								
																
		            			break;
		            		case 7:
		            			ExpenseAppHome h=new ExpenseAppHome();
		            			h.Home();
		            			break;
		            			
		            		}
		            		
		            	}while(op2==1 || op2==2 || op2==3 || op2==4 || op2==5);
		                    
		                    
		            }
		            else{
		                    System.out.println("Incorrect Username Or Password" );
		                }
		            
		        } catch (SQLException ex) {
		            System.out.println( ex);
		        }

			
		}
		if(choice==2)
		{
			System.out.println("Enter the id");
			int userId=s.nextInt();
			System.out.println("Enter the name");
			String username=s.next();
			System.out.println("Enter the Income per month");
			int income=s.nextInt();
			
			System.out.println("Enter email");
			String useremail=s.next();
			System.out.println("Enter user name");
			String username1=s.next();
			System.out.println("Enter password");
			String userpwd1=s.next();
			PreparedStatement sqll = (PreparedStatement) con.getConnection()
					.prepareStatement("insert into userdata(ID,NAME,EMAIL_ID,INCOME) VALUES (?,?,?,?)");
			sqll.setInt(1, userId);
			sqll.setString(2, username);
			
			sqll.setString(3, useremail);
			sqll.setInt(4, income);
			
			
			sqll.executeUpdate();
			PreparedStatement sqll1 = (PreparedStatement) con.getConnection()
					.prepareStatement("insert into admintable(USER_NAME,PASSWORD,USID,STATUS) VALUES (?,?,?,?)");
			sqll1.setString(1, username1);
			sqll1.setString(2, userpwd1);
			sqll1.setInt(3, userId);
			sqll1.setInt(4, 0);
			sqll1.executeUpdate();
			System.out.println("Account Create  Successfully");
			System.out.println("Go to login ?");
			String y=s.next();
			if(y.contentEquals("yes"))
			{
			  ExpenseUser us=new ExpenseUser();
			  us.ExUser();
			}
			else
			{
				ExpenseAppHome us1=new ExpenseAppHome();
				  us1.Home();
			}
			
		}
		
		
	}

}
