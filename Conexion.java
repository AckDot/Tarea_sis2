
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private final String URL;
    private final String user;
    private final String password;
    private PreparedStatement ps;
    private ResultSet rs;

    public Conexion(String URL, String user, String password) {
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.ps = null;
        this.rs = null;
    }

    private Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = (Connection) DriverManager.getConnection(URL, user, password);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return c;
    }

    public boolean consultar(String nom) throws SQLException {
        Connection c = getConnection();
        boolean bandera = false;
        try {
            ps = c.prepareStatement("select * from plato where nomPlato = ?");
            ps.setString(1, nom);
            rs = ps.executeQuery();

            bandera = rs.next();

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            c.close();
        }

        return bandera;
    }

    public boolean agregarRegistro(String nom, String ingre, int valor) throws SQLException {
        Connection c = getConnection();
        boolean bandera = false;
        try {
            ps = c.prepareStatement("insert into plato (nomPlato, ingrePlato, valorPlato) values (?,?,?)");
            ps.setString(1, nom);
            ps.setString(2, ingre);
            ps.setInt(3, valor);

            int res = ps.executeUpdate();

            if (res > 0) {
                bandera = true;
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            c.close();
        }

        return bandera;
    }
}
