/***** 
 * class Recipe
*******/


public class Recipe {
    private int id,
                score;
    private String nameRecipe,
                   ingredients;
    
    public Recipe(String nameRecipe, String ingredients, int score){
        this.id = 0; 
        this.nameRecipe = nameRecipe;
        this.ingredients = ingredients;
        this.score = score;
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
