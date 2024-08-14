import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;



public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "hamsandwich1234";

                
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    if (connection != null) {
                        System.out.println("Connected to the database!");
                    } else {
                        System.out.println("Failed to make connection!");
                    }
                } catch (SQLException e) {
                    System.out.println("Connection failed.");
                    e.printStackTrace();
                }


            SwingUtilities.invokeLater(() -> {
                new LoginFrame(); 
            });
                
                   
            }
            
        }
    
        
