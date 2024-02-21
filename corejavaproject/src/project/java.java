package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


class code
{
	String url="jdbc:mysql://localhost:3306/students";
	String username="root";
	String password="";
void createdatabase()
{
	String url="jdbc:mysql://localhost:3306/";
	String username="root";
	String password="";
	try 
	{
		Scanner sc=new Scanner (System.in);
		System.out.println("write a query to create a database");
		String query=sc.next();
		Connection c=DriverManager.getConnection(url,username,password);
		Statement s=c.createStatement();
	
		
		s.execute(query);
		System.out.println("database created  successfully");
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	
}

void createtable()
{
	try 
		{
		Connection c=DriverManager.getConnection(url,username,password);
			Statement s=c.createStatement();
			s.execute("create table javastudent (rollno int,name varchar(50),surname varchar(50),fees int ,paidfees int,balancefees int)");
			System.out.println("table created  successfully");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
}

	 void insert()
	{
		
	try 
	{
		
		Connection c=DriverManager.getConnection(url,username,password);
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the data you want to enter");
		PreparedStatement ps=c.prepareStatement("insert into javastudent values(?,?,?,?,?,?)");
		ps.setInt(1, sc.nextInt());
		ps.setString(2, sc.next());
		ps.setString (3,sc.next());
		System.out.println("total fees"+100000);
		
		ps.setInt(4, 100000);
		System.out.println("enter the fees how much you want to pay");
		int payingfees=sc.nextInt();
		
		ps.setInt(5, payingfees);
		
		System.out.println("balance fees"+(100000-payingfees));
		ps.setInt(6,100000-payingfees);
		
		ps.execute();
		System.out.println("data inserted successfully");
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}

		
	}
	 int sel()
	 {
	 			try 
	 			{
	 				Scanner sc=new Scanner(System.in);
	 				Connection c=DriverManager.getConnection(url,username,password);
	 				PreparedStatement ps=c.prepareStatement("select balancefees from javastudent where rollno=?");
	 				System.out.println("enter the roll no of which you want to see the balance fees");
	 				ps.setInt(1, sc.nextInt());
	 				
	 		
	 			ResultSet rs= ps.executeQuery();
	 				
	 			while(rs.next())
	 			{
	 					System.out.println(rs.getInt(1));
	 					return rs.getInt(1);
	 			}
	 			
	 			
	 				}
	 			catch(Exception e)
	 			{
	 				e.printStackTrace();
	 				
	 			}	
	 			return 5;
	 			
	 		}
	
	 void payfees()
	{
		 code cd=new code();
		 int totalfees=100000;	
			
			 int balfees=cd.sel();
		 int paidfees=totalfees-balfees;
		
		try 
		{
			Scanner sc=new Scanner(System.in);
			Connection c=DriverManager.getConnection(url,username,password);
			Statement s=c.createStatement();
		
			PreparedStatement ps=c.prepareStatement("update javastudent set paidfees=?,balancefees =? where rollno=?");
System.out.println("enter the rollno from which you want to pay fees");
			
			ps.setInt(3, sc.nextInt());
			System.out.println("enter the amount how much you want to pay");
			int amount=sc.nextInt();
			ps.setInt(1, paidfees+amount);
			ps.setInt(2,totalfees-(paidfees+amount));
			
			
			ps.execute();
	
			System.out.println("fees paid  successfully");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	
}
	void delete()
	{
		try 
		{
			Scanner sc=new Scanner(System.in);
			Connection c=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=c.prepareStatement("delete from javastudent where rollno=?");
			System.out.println("enter the rollno of student which you want to delete");
		ps.setInt(1,sc.nextInt());
		ps.execute();
		System.out.println("data deleted successfully");
			}
		catch(Exception e)
		{
			e.printStackTrace();
			}
	}
	  void select()
	{
		
		try 
		{
			Scanner sc=new Scanner(System.in);
			Connection c=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=c.prepareStatement("select * from javastudent");
			System.out.println("data");
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getInt(6));
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}	
	}
}
public class java 
{
	public static void main(String[] args)
	{
		

code c=new code();

boolean isExit=false;

System.out.println("***student management system***");

while(!isExit) {
	System.out.println("=====****=====");
	System.out.println("Enter your choice");
	System.out.println("1.create database\n2.create table \n3.Insert\n4.payfees\n5.Delete\n6.select\n7.Exit");
	System.out.println("=====****======");
	Scanner scanner = new Scanner(System.in);
	int choice = scanner.nextInt();
	
	switch (choice) {
	case 1: {
		c.createdatabase();
		break;
	}
	
	case 2: {
		c.createtable();
		break;
	}
	case 3: {
		c.insert();
		break;
	}
	case 4: {
		c.payfees();
		break;
	}
	case 5: {
		c.delete();
		break;
	}
	case 6: {
		c.select();
		break;
	}
	case 7: {
		System.out.println("Thank you!!");
		System.exit(0);
	}
	default:
		System.out.println("Please enter valid choice");
	}
}
}
}



