package StoreManager;

import java.util.Scanner;

public class Home {

	public static void main(String[] args) throws ClassNotFoundException {
	
		// TODO Auto-generated method stub
		Home ob=new Home();
        ob.home();
	}
	public void home() throws ClassNotFoundException {
		Scanner s=new Scanner(System.in);
		int op;
		do
		{
		System.out.println("1.Admin\n2.Agent\n3.Exit");
		System.out.println("Enter the Choice");
		op=s.nextInt();
		
		if(op==1)
		{
			StoreAdmin sad=new StoreAdmin();
			sad.AddProduct();
		}
		if(op==2)
		{
			StoreAgent sad=new StoreAgent();
			sad.Agent();
		}
		if(op==3)
		{
			System.out.println("Complete the process");
			System.exit(0);
		}
		}while(op==1 || op==2 || op==3);

		
	}

	
		// TODO Auto-generated method stub
		
	}
	


