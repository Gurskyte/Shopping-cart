/**
 * Created by Ugne on 2017.09.04.
 */
//https://stackoverflow.com/questions/18308038/inserting-data-into-a-h2-database-table
//https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class H2jdbcCreate {
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
            System.out.println("Creating table in given database");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE GOODS" +
                    "(id INTEGER not null," +
                    "name VARCHAR(20)," +
                    "category VARCHAR(20)," +
                    "quantity INTEGER," +
                    "price DOUBLE," +
                    "PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

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
