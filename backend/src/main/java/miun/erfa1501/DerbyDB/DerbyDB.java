package miun.erfa1501.DerbyDB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//https://stackoverflow.com/questions/12875682/initialize-database-on-jersey-webapp-startup
public class DerbyDB implements ServletContextListener {
    private static String dbURL = "jdbc:derby://localhost:1527/myDB";
    public static Connection conn;

    public void contextInitialized(ServletContextEvent arg0) {
        System.out.print("DerbyDB initialized");
        createConnection();
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        endConnection();
    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    private static void endConnection() {
        try {
            if (conn != null) {
                //DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
}
