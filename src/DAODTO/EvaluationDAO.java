package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluationDAO {

	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	// Counter for tracking operations
	public int n = 0; // Counter for operations

	// Constructor to initialize the DAO and load the JDBC driver
	public EvaluationDAO() {
//		try {
//			Class.forName(driver); // Load the JDBC driver
//		} catch (Exception e) {
//			e.printStackTrace(); // Print stack trace if loading the driver fails
//		}
	}

	// Method to select evaluations from the database based on the provided name
	public ArrayList<EvaluationDTO> select(String name) {
		ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String query = "SELECT * FROM evaluation WHERE checkname=?"; // SQL query to select evaluations by name
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			pstmt.setString(1, name); // Set the name parameter
			rs = pstmt.executeQuery(); // Execute the query and get the result set

			while (rs.next()) {
				EvaluationDTO dto = new EvaluationDTO();

				// Set the DTO properties from the result set
				dto.setName(rs.getString("checkname"));
				dto.setCheckscore(rs.getInt("checkscore"));
				dto.setChecknum(rs.getInt("checknum"));

				n++; // Increment the counter

				list.add(dto); // Add the DTO to the list
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
		return list; // Return the list of evaluations
	}

	// Method to update evaluation records in the database
	public void update(String sql, String name, int checkscore, int checknum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			pstmt = con.prepareStatement(sql); // Prepare the SQL query

			// Set the parameters for the SQL query
			pstmt.setInt(1, checkscore);
			pstmt.setInt(2, checknum);
			pstmt.setString(3, name);

			int n = pstmt.executeUpdate(); // Execute the update (update operation)

			n++; // Increment the counter (not used in this context)
		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if an exception occurs
		} finally {
			try {
				if (con != null) con.close(); // Close the connection if it is not null
				if (pstmt != null) pstmt.close(); // Close the PreparedStatement if it is not null
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if closing resources fails
			}
		}
	}
}