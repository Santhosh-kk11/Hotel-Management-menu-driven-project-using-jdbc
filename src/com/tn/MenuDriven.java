package com.tn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuDriven {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","root");
			boolean repeat=true;
			do {
				System.out.println("1.AddMenu\n2.DeleteMenu\n3.UpdateMenu\n4.getMenu\n5.get Menu by name");
				System.out.println("enter your choice");
				int ch=scan.nextInt();
				switch(ch) {
				case 1:{
					System.out.println("enter the id");
					int id=scan.nextInt();
					System.out.println("enter the name");
					String name=scan.next();
					System.out.println("enter the quantity");
					int qnt=scan.nextInt();
					System.out.println("enter the cost");
					int cost=scan.nextInt();
					PreparedStatement preparedStatement=connection.prepareStatement("insert into hotel values(?,?,?,?)");
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setInt(3, qnt);
					preparedStatement.setInt(4, cost);
					preparedStatement.execute();

					System.out.println("items added");
				}break;
				case 2:{
					System.out.println("Enter the id to delete");
					int id=scan.nextInt();
					PreparedStatement preparedStatement=connection.prepareStatement("delete from hotel where id=?");
					preparedStatement.setInt(1, id);
					preparedStatement.execute();
					System.out.println("Item Deleted");

				}
				break;
				case 3:{
					System.out.println("Enter the id to be updated");
					int id=scan.nextInt();
					System.out.println("Enter the name to be updated");
					String name=scan.next();
					System.out.println("Enter the qnt to be updated");
					int qnt=scan.nextInt();
					System.out.println("Enter the cost to be updated");
					int cost=scan.nextInt();
					PreparedStatement preparedStatement=connection.prepareStatement("update hotel set name=? ,qnt=?,cost=? where id=?");
					preparedStatement.setString(1, name);
					preparedStatement.setInt(2, qnt);
					preparedStatement.setInt(3, cost);
					preparedStatement.setInt(4, id);
					preparedStatement.execute();
					System.out.println("Item updated");
				}
				break;
				case 4:{
					PreparedStatement preparedStatement=connection.prepareStatement("select * from hotel");
					ResultSet resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						System.out.println("id is: "+resultSet.getInt(1));
						System.out.println("name is: "+resultSet.getString(2));
						System.out.println("quantity is: "+resultSet.getInt(3));
						System.out.println("cost is: "+resultSet.getInt(4));
						System.out.println("************");
					}

				}
				break;
				case 5:{
					System.out.println("Enter item name to retrieve data");
					String name=scan.next();
					PreparedStatement preparedStatement=connection.prepareStatement("select * from hotel where name=?");
					preparedStatement.setString(1, name);
					ResultSet resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						System.out.println("id is: "+resultSet.getInt(1));
						System.out.println("name is: "+resultSet.getString(2));
						System.out.println("quantity is: "+resultSet.getInt(3));
						System.out.println("cost is: "+resultSet.getInt(4));
						System.out.println("----------------------------------------");
					}
				}
				break;	
				case 6:{
					repeat=false;
					System.out.println("thank you");
				}
				break;

				default:System.out.println("invalid choice");
				System.exit(0);
				break;
				}
			}while(repeat);
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
