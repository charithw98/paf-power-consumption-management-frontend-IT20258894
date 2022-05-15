package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class power {
	
	private Connection connect() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// this sample 1
	
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Power_consumption?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "root");
			
			//For testing
			System.out.print("Successfully connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Read function
	public String readPower()
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
				return "Error while connecting to the database for reading."; 
			 } 
	
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Usage</th>" +"<th>Unit type</th><th>Discription</th>"+"<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from power"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 	
				 // Retrieve from database using column names
				 String ID = Integer.toString(rs.getInt("ID")); 
				 String usage = rs.getString("usage"); 
				 String unitType = rs.getString("unitType"); 
				 String discription = rs.getString("discription"); 
				 
				 // Add a row into the html table
				 output += "<tr><td>"+ usage + "</td>";
				 output += "<td>" + unitType + "</td>"; 
				 output += "<td>" + discription + "</td>";
				 
				 
				 // Buttons
				 output += 
				   "<td><input name='btnUpdate' type='button' value='Update' " + "class='btnUpdate btn btn-secondary' data-ID='" + ID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-ID='" + ID + "'>"+"</td>"
				 + "</form></td></tr>";			 
			 } 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
			 } 
		catch (Exception e) 
		 { 
			 output = "Error while reading the power."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}	
	//Insert function
	public String insertPower(String usage, String unitType, String description)
	{ 
		String output = "";  
		try
		 { 
			Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database"; 
			 } 
			 // create a prepared statement
			 String query = " insert into power (`ID`,`usage`,`unitType`,`description`)"+" values (?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, usage); 
			 preparedStmt.setString(3, unitType); 
			 preparedStmt.setString(5, description); 
			 
			 //execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newpower = readPower();
			 output = "{\"status\":\"success\", \"data\": \"" + newpower + "\"}";
		 } 
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the power.\"}";
			 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}	
	//Delete function
	public String deletePower(String ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
		
			// create a prepared statement
			String query = "delete from power where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newPower = readPower();
			output = "{\"status\":\"success\", \"data\": \"" + newPower + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the power.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//Update function
	public String updatePower(String ID, String usage, String unitType, String discription)
	{
		String output = "";
		try 
		{
			Connection con = connect();
	
			if (con == null) 
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE powers SET usage=?,unitType=?,discription=?" + "WHERE ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
					preparedStmt.setString(1, usage);
					preparedStmt.setString(2, unitType);
					preparedStmt.setString(4, discription);
					preparedStmt.setInt(5, Integer.parseInt(ID));

			// execute the statement
					preparedStmt.execute();
					con.close();

					String newPower = readPower();
					output = "{\"status\":\"success\", \"data\": \"" + newPower + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the power.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertpower(String parameter, String parameter2, String parameter3, Object write) {
		// TODO Auto-generated method stub
		return null;
	}
}
