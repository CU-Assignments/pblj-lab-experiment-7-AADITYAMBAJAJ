import java.sql.*; 
public class SQLiteConnection { 
 public static void main(String[] args) { 
 String url = "jdbc:sqlite:employees.db"; // Your SQLite database file 
 try { 
 // Load SQLite JDBC Driver 
 Class.forName("org.sqlite.JDBC"); 
 // Establish Connection
Connection conn = DriverManager.getConnection(url); 
 System.out.println("Connected to SQLite database!"); 
 // Create Employee Table if it doesn't exist 
 String createTableSQL = "CREATE TABLE IF NOT EXISTS Employee (" 
 + "EmpID INTEGER PRIMARY KEY AUTOINCREMENT, " 
 + "Name TEXT NOT NULL, " 
 + "Salary REAL NOT NULL" 
 + ");"; 
 Statement stmt = conn.createStatement(); 
 stmt.execute(createTableSQL); 
 System.out.println("Employee table is ready."); 
 // Insert Sample Data (if table was empty) 
 String insertSQL = "INSERT INTO Employee (Name, Salary) " 
 + "SELECT 'Alice', 50000.00 WHERE NOT EXISTS (SELECT 1 
FROM Employee);"; 
 stmt.execute(insertSQL); 
 // Query Employee Table 
 ResultSet rs = stmt.executeQuery("SELECT * FROM Employee"); 
 // Display Results 
 System.out.println("Employee Details:"); 
 while (rs.next()) { 
 System.out.println("EmpID: " + rs.getInt("EmpID") + 
 ", Name: " + rs.getString("Name") +
", Salary: " + rs.getDouble("Salary")); 
 } 
 // Close resources 
 rs.close(); 
 stmt.close(); 
 conn.close(); 
 System.out.println("Database operation completed."); 
 } catch (ClassNotFoundException e) { 
 System.out.println("SQLite JDBC Driver not found. Ensure sqlite-jdbc-
3.49.1.0.jar is in classpath."); 
 e.printStackTrace(); 
 } catch (SQLException e) { 
 e.printStackTrace(); 
 } 
 }
