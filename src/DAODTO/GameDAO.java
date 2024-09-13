package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameDAO {

	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	// Counter for tracking operations
	public int n; // Counter for operations

	// Constructor to initialize the DAO and load the JDBC driver
	public GameDAO() {
//		try {
//			Class.forName(driver); // Load the JDBC driver
//		} catch (Exception e) {
//			e.printStackTrace(); // Print stack trace if loading the driver fails
//		}
	}

	// Method to select all game questions from the database
	public ArrayList<GameDTO> selectgame() {
		ArrayList<GameDTO> list = new ArrayList<GameDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String query = "SELECT * FROM gamequestion"; // SQL query to select all game questions
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			rs = pstmt.executeQuery(); // Execute the query and get the result set

			while (rs.next()) {
				GameDTO dto = new GameDTO();

				// Set the DTO properties from the result set
				dto.setNum(rs.getInt("num"));
				dto.setQuestion(rs.getString("question"));
				dto.setFinger(rs.getInt("finger"));
				dto.setWord(rs.getString("word"));

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

		return list; // Return the list of game questions
	}
}