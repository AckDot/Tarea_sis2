/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea_sis2;
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
    
    private static Ventana interfaz;
    private Connection conexion;
    private Statement consulta;
    private ResultSet respuesta;
    
    public Receta(){
        interfaz = new Ventana();
        addListeners();
        initConexionDB();
    }
    
    private void addListeners(){
        interfaz.getBotonGuardar().addActionListener((ActionEvent e) -> {
            gestorReceta();
        });
         interfaz.getBotonSalir().addActionListener((ActionEvent e) -> {
            System.exit(0);
        });       
    }  
    private void initConexionDB(){
        DatabaseConnection connector = new DatabaseConnection();
        conexion = connector.getConnection();
    }
    
    private void gestorReceta(){
        String receta = interfaz.getCajaNomPlato().getText().trim().toLowerCase();
        if(esRecetaNueva(receta)){
            String ingredientes = interfaz.getCajaIngredientes().getText().trim().toLowerCase();
            int dificultad = Integer.parseInt(interfaz.getCajaValor().getText().trim());
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