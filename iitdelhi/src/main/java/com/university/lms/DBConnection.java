
package com.university.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/libraryiiitd";
    private static final String USER = "root";
    private static final String PASS = "Gulshan$41609";
    
    
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Connected to MySQL!");

                createStudentTable();
                createBooksTable();
                createBookIssueTable();
                createLibrarianTable(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

   

    private static void createStudentTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "Student_id VARCHAR(20) PRIMARY KEY,"
                + "Student_name VARCHAR(100),"
                + "Student_department VARCHAR(100),"
                + "Student_Batch VARCHAR(50),"
                + "Student_Password VARCHAR(40),"
                + "Student_Phone_Number VARCHAR(10)"
            + ");" ;
        

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

          

    private static void createBooksTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS books ("
               + "Book_id VARCHAR(20) PRIMARY KEY,"
               + "Book_author VARCHAR(100),"
               + "Book_name VARCHAR(100),"    
               + "Book_price DOUBLE,"
               + "Book_edition VARCHAR(30),"
               + "Book_Isbn VARCHAR(13)"
            + ");" ;

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    private static void createBookIssueTable() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS book_issue ("
            + "issue_id INT PRIMARY KEY AUTO_INCREMENT,"
            + "book_id VARCHAR(20) NOT NULL,"
            + "book_name VARCHAR(255),"
            + "student_id VARCHAR(20) NOT NULL,"
            + "student_name VARCHAR(100),"
            + "issue_date DATE,"
            + "expected_due_date DATE,"
            + "return_date DATE"
            + ");";

    Statement stmt = conn.createStatement();
    stmt.execute(sql);
}


   private static void createLibrarianTable() throws SQLException{
    String sql = "CREATE TABLE IF NOT EXISTS librarian ("
                 +"librarian_id VARCHAR(20) PRIMARY KEY ,"
                 +"librarian_name VARCHAR(50) ,"
                 +"librarian_password VARCHAR(50)"
                 +");";

    Statement stmt = conn.createStatement();
    stmt.execute(sql);             
   }
   public static void main(String[] args) {
       getConnection() ;
   } 
}
