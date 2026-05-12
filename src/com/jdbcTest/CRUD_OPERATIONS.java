package com.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUD_OPERATIONS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc_test";
		String username = "root";
		String password = "Sharath@14";
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			
			//Load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish Connection
			con = DriverManager.getConnection(url, username, password);
			
			//Create Statement
			stmt = con.createStatement();
			
			con.setAutoCommit(false);
			
			//Write a query to insert records
			String query1 = "insert into homebank values(101,'Govindu','Contractor', 100000)";
			String query2 = "insert into homebank values(102,'Sharath','Student', 80000)";
			String query3 = "insert into homebank values(103,'Neeraja','Teacher', 90000)";
			String query4 = "insert into homebank values(104,'Poojitha','Student', 30000)";
			
			stmt.addBatch(query1);
			stmt.addBatch(query2);
			stmt.addBatch(query3);
			stmt.addBatch(query4);
			
			stmt.executeBatch();
			System.out.println("Insertion Performed.");
			
			//Select records
			String query7 = "select * from homebank";
			res = stmt.executeQuery(query7);
			
			while(res.next()) {
				System.out.println(res.getInt(1)+ " " + res.getString(2) + " " + res.getString(3) + " " + res.getInt(4));
			}
			
			System.out.println("Selection Performed.");
			
			//Update records
			String query5 = "update homebank set balance = balance + ?" + " where designation = ?";
			
			pstmt = con.prepareStatement(query5);
			System.out.println("Enter amount to credit : ");
			int new_amount = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter designation: ");
			String dsig = scanner.nextLine();
			pstmt.setInt(1, new_amount);
			pstmt.setString(2, dsig);
			
			int i = pstmt.executeUpdate();
			System.out.println("Updation Performed : " + i + " rows Affected.");
			
			
			//Delete records
			String query6 = "delete from homebank where acc_num = ?";
			System.out.println("Enter account number perform deletion : ");
			int number = scanner.nextInt();
			pstmt = con.prepareStatement(query6);
			pstmt.setInt(1, number);
			
			pstmt.executeUpdate();
			System.out.println("Deletion Performed Successfully.");
			
			con.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			res.close();
			stmt.close();
			con.close();
		}
		catch(SQLException f) {
			f.printStackTrace();
		}

	}

}
