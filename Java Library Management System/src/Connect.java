import java.sql.Connection;
import java.sql.DriverManager;



public class Connect {

         public static Connection connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //load in 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db"
            ,"root","hamsandwich1234");

            return con;
        } 
        catch (Exception e) {
            e.printStackTrace();        
        }
            return null;
        }

}
    

