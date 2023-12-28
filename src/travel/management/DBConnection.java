package travel.management;

import java.sql.*;

/**
 *
 * @author sriramvalluri
 */
public class DBConnection {
    
    Connection conn;
    Statement stmt;
    DBConnection() {
        try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_management", "test", "test");
         stmt = conn.createStatement();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
