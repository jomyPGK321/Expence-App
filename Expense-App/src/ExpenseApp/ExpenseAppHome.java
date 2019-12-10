package ExpenseApp;

import java.sql.SQLException;
import java.util.Scanner;

public class ExpenseAppHome {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		ExpenseAppHome obj=new ExpenseAppHome();
		obj.Home();
	}

	public void Home() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ExpenseConnection con=new ExpenseConnection();
		Scanner s=new Scanner(System.in);
		int op;
		do
		{
			System.out.println("Welcome");
			System.out.println("1.Admin\n2.User\n3.Quit");
			System.out.println("Enter the choice");
			op=s.nextInt();
			switch(op)
			{
			case 1:
				ExpenseAdmin exad=new ExpenseAdmin();
				exad.ExAdmin();
				break;
			case 2:
				ExpenseUser exus=new ExpenseUser();
				exus.ExUser();
				break;
			case 3:
				System.out.println("Thanks for visiting");
				System.exit(0);
				break;
			}
			
		}while(op==1 || op==2 || op==3);
		
	}

}
