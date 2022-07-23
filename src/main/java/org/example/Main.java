package org.example;

import lombok.SneakyThrows;

import java.sql.DriverManager;

public class Main {
  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("Hello world!");
    var conn = DriverManager.getConnection("jdbc:mysql://localhost/backend_intern_mysql","root", Configs.PASSWORD);
    System.out.println("Connected!");
    var sqlExecutor = new SqlCommandExecutor(conn);
//    sqlExecutor.addTeacher("903613092", "Mahdavi");
//    sqlExecutor.addCourse("Riazi 2", 50, "903613092");
//    sqlExecutor.addStudent("963613092", "Alireza", false, 20.0, 1);
//    sqlExecutor.addStudentToCourse("963613092", 1);
//    sqlExecutor.deleteStudentFromCourse("963613092", 1);
//    sqlExecutor.updateStudentFavoriteCourse("963613092", 2);
    System.out.println(sqlExecutor.showStudentsWithMinimumAvgScore(10.0));
  }
}