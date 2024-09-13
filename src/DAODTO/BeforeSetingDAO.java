package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BeforeSetingDAO {

	// Database connection details
	String driver = "oracle.jdbc.driver.OracleDriver"; // JDBC driver for Oracle
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false"; // JDBC URL for Oracle
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	// Counters for tracking operations
	public int n = 0; // Counter for operations
	public int i = 0; // Another counter (not used in this class)

	// Constructor to initialize the DAO and load the JDBC driver
	public BeforeSetingDAO() {
		try {
			Class.forName(driver); // Load the JDBC driver
		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace if loading the driver fails
		}
	}

	// Method to select all menu items from the database
	public ArrayList<BeforeSetingDTO> selectMenu() {
		ArrayList<BeforeSetingDTO> list = new ArrayList<BeforeSetingDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String query = "SELECT * FROM menu"; // SQL query to select all menu items
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			rs = pstmt.executeQuery(); // Execute the query and get the result set
			while (rs.next()) {
				BeforeSetingDTO dto = new BeforeSetingDTO();

				// Set the DTO properties from the result set
				dto.setKind(rs.getString("kind"));
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setMeterial(rs.getString("meterial"));
				dto.setExplain(rs.getString("explain"));
				dto.setCalorie(rs.getInt("calorie"));
				dto.setPrice(rs.getInt("price"));

				list.add(dto); // Add the DTO to the list

				n++; // Increment the counter
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

	public ArrayList<GameDTO> selectGame() {
		ArrayList<GameDTO> list = new ArrayList<GameDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String query = "SELECT * FROM gamequestion"; // SQL query to select all rows from gamequestion table
			pstmt = con.prepareStatement(query); // Prepare the SQL query
			rs = pstmt.executeQuery(); // Execute the query and get the result set

			while (rs.next()) {
				GameDTO dto = new GameDTO();

				// Set the DTO properties from the result set
				dto.setNum(rs.getInt("num")); // `num` is an integer and a primary key
				dto.setQuestion(rs.getString("question")); // `question` is a VARCHAR column
				dto.setFinger(rs.getInt("finger")); // `finger` is an integer
				dto.setWord(rs.getString("word")); // `word` is a VARCHAR column

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
		return list; // Return the list of menu items
	}

	// Method to insert a new menu item into the database
	public void insertmenu(String kind, int num, String name, String meterial,
						   String explain, int calorie, int price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String sql = "INSERT INTO menu (kind, num, name, meterial, `explain`, calorie, price) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql); // Prepare the SQL query

			// Set the parameters for the SQL query
			pstmt.setString(1, kind);
			pstmt.setInt(2, num);
			pstmt.setString(3, name);
			pstmt.setString(4, meterial);
			pstmt.setString(5, explain);
			pstmt.setInt(6, calorie);
			pstmt.setInt(7, price);

			int n = pstmt.executeUpdate(); // Execute the update (insert operation)
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

	// Method to insert a new game question into the database
	public void insertgame(String question, int num, int finger, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd); // Establish database connection
			String sql = "INSERT INTO gamequestion (question,finger,word) VALUES(?,?,?)"; // SQL query to insert a new game question
			pstmt = con.prepareStatement(sql); // Prepare the SQL query

			// Set the parameters for the SQL query
			//pstmt.setInt(1, num);
			pstmt.setString(1, question);
			pstmt.setInt(2, finger);
			pstmt.setString(3, word);

			int n = pstmt.executeUpdate(); // Execute the update (insert operation)
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