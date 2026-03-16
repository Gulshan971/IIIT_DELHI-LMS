/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.university.lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class IssueDAO {
    public static final int FINE_FREE_DAYS = 15 ;
    public static boolean issueBook(String bookId, String studentId) {
        String getBookName = "SELECT book_name FROM books WHERE book_id = ?";
        String getStudentName = "SELECT student_name FROM students where student_id = ?" ;
        String insertIssue = "INSERT INTO book_issue "
                + "(book_id, book_name, student_id, student_name, issue_date, expected_due_date, return_date) "
                + "VALUES (?, ?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY), NULL)";

        try (Connection conn = DBConnection.getConnection()) {

            // Step 1: fetch book_name
            PreparedStatement ps1 = conn.prepareStatement(getBookName);
            ps1.setString(1, bookId);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("Book not found");
                return false;
            }

            String bookName = rs.getString("Book_name");

            //Fetching student_name
            PreparedStatement ps3 =conn.prepareStatement(getStudentName) ;
            ps3.setString(1,studentId) ;
            ResultSet res =  ps3.executeQuery() ;
            if(!res.next()){
                System.out.println("Student not found");
                return false ;
            }

            String studentName = res.getString("Student_name") ;

            // Step 2: insert issue record
            PreparedStatement ps2 = conn.prepareStatement(insertIssue);
            ps2.setString(1, bookId);
            ps2.setString(2, bookName);
            ps2.setString(3, studentId);
            ps2.setString(4, studentName);

            ps2.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 

    public static boolean returnBook(String bookId, String studentId) {
        String sql = "UPDATE book_issue SET return_date = CURDATE() "
                   + "WHERE book_id = ? AND student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bookId);
            ps.setString(2, studentId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // true if a record was updated

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double calculateFine(Date dueDate, Date returnDate) {
    long diffInMillies = returnDate.getTime() - dueDate.getTime();
    long daysLate = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    if(daysLate > 0) {
        return daysLate * 5; // ₹5 per day
    } else {
        return 0;
    }
}
}
