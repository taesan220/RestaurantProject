package DAODTO;

import java.awt.Container;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.attribute.standard.PresentationDirection;
import javax.xml.crypto.Data;

public class AnorderDAO {

	// Oracle JDBC driver class
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	// Variable to count the number of processed records
	public int n;

	// Constructor to initialize the DAO and load the Oracle JDBC driver
	public AnorderDAO() {
		try {
			Class.forName(driver); // Load the driver class
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Method to select order information based on a query and condition
	public ArrayList<AnorderDTO> selectorder(String query, int condition) {
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>(); // List to hold results
		Connection con = null; // Connection object
		PreparedStatement pstmt = null; // Prepared statement object
		ResultSet rs = null; // Result set object

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			pstmt.setInt(1, condition); // Set the condition parameter
			rs = pstmt.executeQuery(); // Execute the query and get results

			while (rs.next()) {
				AnorderDTO anorderdto = new AnorderDTO(); // Create a new DTO instance

				// Set properties from the result set
				anorderdto.setNum(rs.getInt("num"));
				anorderdto.setTablecode(rs.getInt("tablecode"));
				anorderdto.setKind(rs.getString("kind"));
				anorderdto.setName(rs.getString("name"));
				anorderdto.setAmount(rs.getInt("amount"));
				anorderdto.setAnorder(rs.getInt("anorder"));
				anorderdto.setTime(rs.getDate("time"));
				anorderdto.setCondition(rs.getInt("condition"));
				n++; // Increment the processed record count
				list.add(anorderdto); // Add the DTO to the list
				System.out.println("name"); // Print name (for debugging)

			}

		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if an exception occurs
		} finally {
			try {
				// Close resources
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if an exception occurs
			}
		}
		return list; // Return the list of orders
	}

	// Method to insert a new order into the database
	public void insertorder(String sql, int tablecode, String kind,
							String name, int amount, int anorder, int condition) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			pstmt = con.prepareStatement(sql); // Prepare the SQL statement

			// Set parameters for the SQL statement
			// pstmt.setInt(1, num); // Uncomment if num is a parameter
			pstmt.setInt(1, tablecode);
			pstmt.setString(2, kind);
			pstmt.setString(3, name);
			pstmt.setInt(4, amount);
			pstmt.setInt(5, anorder);
			pstmt.setInt(6, condition);

			pstmt.executeUpdate(); // Execute the update
			// System.out.println(n+"�� ������"); // Uncomment if needed

		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if an exception occurs
		} finally {
			try {
				// Close resources
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if an exception occurs
			}

		}

	}

	// Method to select evaluation information based on a query and table code
	public ArrayList<AnorderDTO> selectevaluation(String query, int tablecode) {

		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>(); // List to hold results
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			// pstmt.setInt(1, tablecode); // Uncomment if tablecode is a parameter
			rs = pstmt.executeQuery(); // Execute the query

			while (rs.next()) {
				AnorderDTO anorderdto = new AnorderDTO(); // Create a new DTO instance
				anorderdto.setKind(rs.getString("kind")); // Set kind from result set
				anorderdto.setName(rs.getString("name")); // Set name from result set
				// anorderdto.setCondition(rs.getInt("condition")); // Uncomment if needed
				// anorderdto.setTablecode(rs.getInt("tablecode")); // Uncomment if needed
				// anorderdto.setNum(rs.getInt("num")); // Uncomment if needed
				// anorderdto.setAmount(rs.getInt("amount")); // Uncomment if needed
				// anorderdto.setAnorder(rs.getInt("anorder")); // Uncomment if needed
				// anorderdto.setTime(rs.getDate("time")); // Uncomment if needed

				n++; // Increment the processed record count
				list.add(anorderdto); // Add the DTO to the list
				System.out.println(anorderdto.getName()); // Print name (for debugging)
			}

		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if an exception occurs
		} finally {
			try {
				// Close resources
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if an exception occurs
			}
		}
		return list; // Return the list of evaluations
	}

}