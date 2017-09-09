/**
 * Created by Ugne on 2017.09.04.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FillTable {
    //JDBC driver name and database URL.
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //Database credentials.
    static final String USER = "sa";
    static final String PASS = "ucs";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 1: Register JDBC driver.
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection.
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query.
            stmt = conn.createStatement();
            String sql = "INSERT INTO GOODS " + "VALUES (1, 'Milk', 'Milk product', 5, 0.98)";

            stmt.executeUpdate(sql);
            sql = "INSERT INTO GOODS " + "VALUES (2, 'Ceese', 'Milk product', 7, 1.14)";

            stmt.executeUpdate(sql);

            //STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        }
        catch (SQLException sqlex) {
            //Hande errors for JDBC
            sqlex.printStackTrace();
        }
        catch (Exception e) {
            //Handle errors for Class.forName.
            e.printStackTrace();
        }
        finally {
            //finally block used to close resources.
            try {
                if(stmt!=null) conn.close();
            }
            catch(SQLException sqlex2) {
            } //Nothing we can do.
            try {
                if(conn!=null) conn.close();
            }
            catch(SQLException sqlex){
                sqlex.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

