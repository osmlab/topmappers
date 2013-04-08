package tur.datasource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

public class BDConnecion implements Serializable {

    private String driver;
    private String url;
    private String username;
    private String pwname;

    public BDConnecion(ServletContext ctx) {
        this.driver = ctx.getInitParameter("driver");
        this.url = ctx.getInitParameter("url");
        this.username = ctx.getInitParameter("username");
        this.pwname = ctx.getInitParameter("pwname");
    }

    public Connection getConnection() {
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(this.url, this.username, this.pwname);
            System.out.println("Coneccion a base de datos" +conn);
                  return conn;

        } catch (SQLException sqle) {
            System.out.println("Error getConn=" + sqle.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error getConn=" + e.getMessage());
            return null;
        }
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            System.out.println("Error sqle=" + sqle.getMessage());
        }
    }
}
