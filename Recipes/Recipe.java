import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Recipe {
    private int id,
                score;
    private String nameRecipe,
                   ingredients;
    private Connection connection;
    private DatabaseConnection connector;

    
    public Recipe(String nameRecipe, String ingredients, int score){
        this.id = 0; 
        this.nameRecipe = nameRecipe;
        this.ingredients = ingredients;
        this.score = score;
        this.connector = new DatabaseConnection();
        this.connection = connector.getConnection();
        
    }

    public void insertRecipe(){
        try {
            String sql = "INSERT INTO recipes(name_recipe, ingredients, score) VALUES (?,?,?)";
            PreparedStatement st = connector.getConnection().prepareStatement(sql);
            st.setString(1, this.nameRecipe);
            st.setString(2, ingredients);
            st.setInt(3, score);
            st.execute();
            JOptionPane.showMessageDialog(null, "Receta guardada exitosamente.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La receta ya existe en la BD."); 
        }   
    }
    
    //getters
    public int getId() {
        return id;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }
    
    public String getIngredients() {
        return ingredients;
    }
    
    public int getScore() {
        return score;
    }
}
