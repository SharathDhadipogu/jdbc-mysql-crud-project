package com.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/myntra";
		String username = "root";
		String password = "Sharath@14";
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			con= DriverManager.getConnection(url, username, password);
			System.out.println("Connection estd");
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 0);
			
			String query1 = "insert into user (`user_id`,`firstname`,`lastname`,`email`,`phone_number`,`password`) values (97, 'Sharoon', 'katepogu', 'sharoon12@gmail.com', '8888777765', 'sharoon@28')";
			String query2 = "insert into user (`user_id`,`firstname`,`lastname`,`email`,`phone_number`,`password`) values (98, 'Esther', 'katepogu', 'aleev12@gmail.com', '8888776765', 'aleev@28')";
			String query3 = "insert into user (`user_id`,`firstname`,`lastname`,`email`,`phone_number`,`password`) values (99, 'Aliva', 'katepogu', 'esther13@gmail.com', '8888777865', 'esther@28')";
			stmt.addBatch(query1);
			stmt.addBatch(query2);
			stmt.addBatch(query3);
			
			stmt.executeBatch();
			System.out.println("Queries executed successfully.");
			String q2 = "select * from user";
			res = stmt.executeQuery(q2);
			
			System.out.println("Insertion Performed.");
			
			while(res.next()) {
				System.out.println(res.getInt(1)+" "+res.getString(2)+" " + res.getString(3)+" " + res.getString(4)+" "+res.getString(5)+" "+res.getString(6));
			}
			res.absolute(18);
			System.out.println(res.getInt(1)+" "+res.getString(2)+" " + res.getString(3)+" " + res.getString(4)+" "+res.getString(5)+" "+res.getString(6));
			
			String query4 = "select * from user";
			res = stmt.executeQuery(query4);
			
			ResultSetMetaData metadata = res.getMetaData();
			
			for(int i = 1; i <= metadata.getColumnCount(); i++) {
				System.out.println(metadata.getColumnName(i) + " " + metadata.getColumnTypeName(i));
			}
			
			
			while(res.next()) {
				System.out.println(res.getInt(1)+" "+res.getString(2)+" " + res.getString(3)+" " + res.getString(4)+" "+res.getString(5)+" "+res.getString(6));
			}
			
			res.absolute(13);
			System.out.println(res.getInt(1)+" "+res.getString(2)+" " + res.getString(3)+" " + res.getString(4)+" "+res.getString(5)+" "+res.getString(6));
			
			System.out.println("Selection Prformed.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not Loaded");
		} catch(SQLException f) {
			f.printStackTrace();
		}
		
		try {
			res.close();
			stmt.close();
			con.close();
		}
		catch(SQLException f) {
			f.printStackTrace();
		}
	}
}
