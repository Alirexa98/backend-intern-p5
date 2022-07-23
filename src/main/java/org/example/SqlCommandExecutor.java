package org.example;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SqlCommandExecutor {
  Connection conn;

  public SqlCommandExecutor(Connection connection) {
    conn = connection;
  }

  public void addTeacher(String teacherId, String fullName) throws SQLException {
    var stmt = conn.prepareStatement("insert into teachers values (?, ?)");
    stmt.setString(1, teacherId);
    stmt.setString(2, fullName);
    stmt.executeUpdate();
  }

  public void addCourse(String courseName, int capacity, String teacherId) throws SQLException {
    var stmt = conn.prepareStatement("insert into courses values (default, ?, ?, ?)");
    stmt.setString(1, courseName);
    stmt.setInt(2, capacity);
    stmt.setString(3, teacherId);
    stmt.executeUpdate();
  }

  public void addStudent(String studentId, String fullName, boolean isOnProbation, double score, int favoriteCourse) throws SQLException {
    var stmt = conn.prepareStatement("insert into students values (?, ?, ?, ?, ?)");
    stmt.setString(1, studentId);
    stmt.setString(2, fullName);
    stmt.setBoolean(3, isOnProbation);
    stmt.setDouble(4, score);
    stmt.setInt(5, favoriteCourse);
    stmt.executeUpdate();
  }

  public void addStudentToCourse(String studentId, int courseId) throws SQLException {
    var stmt = conn.prepareStatement("insert into student_course values (?, ?)");
    stmt.setString(1, studentId);
    stmt.setInt(2, courseId);
    stmt.executeUpdate();
  }

  public void deleteStudentFromCourse(String studentId, int courseId) throws SQLException {
    var stmt = conn.prepareStatement("delete from student_course where student_id = ? and course_id = ?");
    stmt.setString(1, studentId);
    stmt.setInt(2, courseId);
    stmt.executeUpdate();
  }

  public void updateStudentFavoriteCourse(String studentId, int courseId) throws SQLException {
    var stmt = conn.prepareStatement("update students set favorite_course = ? where student_id = ?");
    stmt.setInt(1, courseId);
    stmt.setString(2, studentId);
    stmt.executeUpdate();
  }

  public List<String> showStudentsWithMinimumAvgScore(double minimumAvgScore) throws SQLException {
    var result = new LinkedList<String>();
    var stmt = conn.prepareStatement("select full_name from students where average_score > ?");
    stmt.setDouble(1, minimumAvgScore);
    var resultSet = stmt.executeQuery();
    while (resultSet.next()) {
      var studentName = resultSet.getString(1);
      result.add(studentName);
    }
    return result;
  }
}
