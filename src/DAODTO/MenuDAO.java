package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO {

	// Database connection details
	// MySQL JDBC 드라이버
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	// Counter for tracking operations
	public int i = 0; // Counter for operations

	// Constructor to initialize the DAO and load the JDBC driver
	public MenuDAO() {
//		try {
//			Class.forName(driver); // Load the JDBC driver
//		} catch (Exception e) {
//			e.printStackTrace(); // Print stack trace if loading the driver fails
//		}
	}

	// Method to select menu content based on dynamic query and parameters
	public ArrayList<MenuDTO> contentselect(String query, String kind, String name) {
		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			pstmt = con.prepareStatement(query); // Prepare the SQL query

			// Set query parameters if they are not null
			if (kind != null) {
				pstmt.setString(1, kind);
			}
			if (name != null) {
				pstmt.setString(2, name);
			}

			rs = pstmt.executeQuery(); // Execute the query and get the result set
			while (rs.next()) {
				MenuDTO menudto = new MenuDTO();

				// Set the DTO properties from the result set
				menudto.setKind(rs.getString("kind"));
				menudto.setName(rs.getString("name"));
				menudto.setNum(rs.getInt("num"));
				menudto.setMeterial(rs.getString("meterial"));
				menudto.setExplain(rs.getString("explain"));
				menudto.setCalorie(rs.getInt("calorie"));
				menudto.setPrice(rs.getInt("price"));

				list.add(menudto); // Add the DTO to the list

				i++; // Increment the counter
			}

		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if an exception occurs
		} finally {
			try {
				if (con != null) con.close(); // Close the connection if it is not null
				if (pstmt != null) pstmt.close(); // Close the PreparedStatement if it is not null
				if (rs != null) rs.close(); // Close the ResultSet if it is not null
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if closing resources fails
			}
		}
		return list; // Return the list of menu items
	}

	// Method to select menu items based on name
	public ArrayList<MenuDTO> fororder(String name) {
		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String query = "SELECT * FROM menu WHERE name=?"; // SQL query to select menu items by name
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			pstmt.setString(1, name); // Set the query parameter

			rs = pstmt.executeQuery(); // Execute the query and get the result set
			while (rs.next()) {
				MenuDTO menudto = new MenuDTO();

				// Set the DTO properties from the result set
				menudto.setName(rs.getString("name"));
				menudto.setKind(rs.getString("kind"));
				menudto.setNum(rs.getInt("num"));
				menudto.setMeterial(rs.getString("meterial"));
				menudto.setExplain(rs.getString("explain"));
				menudto.setCalorie(rs.getInt("calorie"));
				menudto.setPrice(rs.getInt("price"));

				list.add(menudto); // Add the DTO to the list

				i++; // Increment the counter
			}

		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if an exception occurs
		} finally {
			try {
				if (con != null) con.close(); // Close the connection if it is not null
				if (pstmt != null) pstmt.close(); // Close the PreparedStatement if it is not null
				if (rs != null) rs.close(); // Close the ResultSet if it is not null
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if closing resources fails
			}
		}
		return list; // Return the list of menu items
	}
}