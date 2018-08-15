package grandma.cookbook;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class CookBook {
  protected List<Recipe> listOfRecipe;

  public CookBook() {
    listOfRecipe = new ArrayList<>();
  }

  public Boolean delete(Recipe recipe) {
    if (exists(recipe.recipeId()) != null) {
      listOfRecipe.remove(recipe);
      return true;
    }
    else
      return false;
  }

  public Recipe exists(int recipeId) {
    for (Recipe r : this.listOfRecipe) {
      if (r.recipeId() == recipeId)
        return r;
    }
    return null;
  }

  public void add(Recipe r) {
    listOfRecipe.add(r);
  }

  public void edit(Recipe recipe, String name, String des) {
    recipe.setName(name);
    recipe.setDescription(des);
  }

  public List<Recipe> listOfRecipe() {
    return listOfRecipe;
  }

  public String toJSON() {
    Gson gson = new Gson();
    return gson.toJson(this.listOfRecipe);
  }
}
