
package SisII.Tarea_sis2.Recetas;

import SisII.Tarea_sis2.DataBase.DatabaseConnection;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Receta {
    
    public static void main(String[] args){
        new Receta();
    }
    
    //GUI
    private static RecetaGUI interfaz;
    //DBConnector
    private Connection conexion;
    //DB
    private Statement consulta;
    private ResultSet respuesta;
    
    public Receta(){
        interfaz = new RecetaGUI();
        addListeners();
        initConexionDB();
    }
    
    private void addListeners(){
        interfaz.guardarButton.addActionListener((ActionEvent e) -> {
            gestorReceta();
        });
    }
    
    private void initConexionDB(){
        /*String bd = "sis2";
        String usuario = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/" + bd + "?useUnicode=true&use" + "JDBCCompliantTimeZoneShift=true"
            + "&useLegacyDateTimeCode=false&serverTimezone=UTC";
        
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion: CN54");
        }*/
        DatabaseConnection connector = new DatabaseConnection();
        conexion = connector.getConnection();
    }
    
    private void gestorReceta(){
        String receta = interfaz.recetaField.getText().trim().toLowerCase();
        if(esRecetaNueva(receta)){
            String ingredientes = interfaz.ingredientesArea.getText().trim().toLowerCase();
            int dificultad = Integer.parseInt(interfaz.difField.getText().trim());
            try {
                String sql = "INSERT INTO recipes(name_recipe, ingredients, score) VALUES (?,?,?)";
                PreparedStatement st = conexion.prepareStatement(sql);
                st.setString(1, receta);
                st.setString(2, ingredientes);
                st.setInt(3, dificultad);
                st.execute();
                JOptionPane.showMessageDialog(null, "Receta guardada exitosamente.");
            } catch (SQLException ex) {}
        }else{
            JOptionPane.showMessageDialog(null, "La receta ya existe en la BD.");
        }        
    }
    
    private boolean esRecetaNueva(String receta){
        try {
            consulta = conexion.createStatement();
            respuesta = consulta.executeQuery("SELECT name_recipe FROM recipes WHERE name_recipe = '" + receta + "'");
            if(respuesta.next()){            
                return false;
            }
        } catch (SQLException ex) {}
        return true;
    }
}
