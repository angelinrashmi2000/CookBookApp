package grandma.cookbook;

public class Recipe {
  private String     name;
  private String     description;
  private int        recipeId;
  private static int id = 0;

  public Recipe(String name, String des) {

    this.name = name;
    this.description = des;
    this.recipeId = id++;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String des) {
    this.description = des;
  }

  public String getName() {
    return this.name;
  }

  public int getRecipeId() {
    return this.recipeId;
  }

  public String getDescription() {
    return this.description;
  }
}
