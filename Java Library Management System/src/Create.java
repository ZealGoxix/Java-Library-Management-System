import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;


public class Create {

    public static void create() {
    try {
    Connection connection = Connect.connect();
    ResultSet resultSet = connection.getMetaData().getCatalogs();
    //iterate each catalog in the ResultSet
        while (resultSet.next()) {
          // Get the database name, which is at position 1
          String databaseName = resultSet.getString(1);
          if(databaseName.equals("library")) {
              //System.out.print("yes");
              Statement stmt = connection.createStatement();
              //Drop database if it pre-exists to reset the complete database
              String sql = "DROP DATABASE library";
              stmt.executeUpdate(sql);
          }
        }
          Statement stmt = connection.createStatement();
           
          String sql = "CREATE DATABASE LIBRARY"; //Create Database
          stmt.executeUpdate(sql); 
          stmt.executeUpdate("USE LIBRARY"); //Use Database
          //Create Users Table
          String sql1 = "CREATE TABLE USERS(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), ADMIN BOOLEAN)";
          stmt.executeUpdate(sql1);
          //Insert into users table
          stmt.executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD, ADMIN) VALUES('admin','admin',TRUE)");
          //Create Books table
          stmt.executeUpdate("CREATE TABLE BOOKS(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BNAME VARCHAR(50), GENRE VARCHAR(20), PRICE INT)");
          //Create Issued Table
          stmt.executeUpdate("CREATE TABLE ISSUED(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, FINE INT)");
          //Insert into books table
          stmt.executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, PRICE) VALUES ('War and Peace', 'Mystery', 200),  ('The Guest Book', 'Fiction', 300), ('The Perfect Murder','Mystery', 150), ('Accidental Presidents', 'Biography', 250), ('The Wicked King','Fiction', 350)");
           
    resultSet.close();
    }
     catch (Exception ex) {
         ex.printStackTrace();
}

    }}