package org.example;

import lombok.SneakyThrows;

import java.sql.DriverManager;

public class Main {
  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    DriverManager.getConnection("jdbc:mysql://localhost?user=root&password=" + Configs.PASSWORD);
    System.out.println("Connected!");
  }
}