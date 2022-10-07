package SisII.Tarea_sis2.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConexion
 */
public class DatabaseConnection {

    private Connection con;

    public DatabaseConnection() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://us-east.connect.psdb.cloud/recipes?sslMode=VERIFY_IDENTITY",
                    "s674h3cmydc5zb924t4a",
                    "pscale_pw_QRqZc5Y2Yfaj8606oXXw6gulUjpvQk1nryWxOlwQXo");
            System.out.print("conected");
        } catch (SQLException e) {
            System.out.print("error");
        }
    }
    
    public Connection getConnection(){
        return con;
    }
}
