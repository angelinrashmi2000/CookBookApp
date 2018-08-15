import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import grandma.cookbook.CookBook;
import grandma.cookbook.Recipe;


public class cookBookTest {
  CookBook grandmaCookBook;

  @Before
  public void initialize() {
    grandmaCookBook = new CookBook();
  }

  @Test
  public void addRecipeToEmptyCookBook() {
    assertTrue(grandmaCookBook.listOfRecipe().isEmpty());
    grandmaCookBook.add(new Recipe("recipe1", "description1"));
    assertEquals(String.valueOf(grandmaCookBook.listOfRecipe().size()), String.valueOf(1));
  }

  @Test
  public void persistRecipe() {
    grandmaCookBook.add(new Recipe("recipe1", "description1"));
    assertNotNull("should exist", grandmaCookBook.exists(1));
    assertNull(grandmaCookBook.exists(10));
  }

  @Test
  public void deleteRecipe() {
    Recipe r = new Recipe("recipe1", "description1");
    grandmaCookBook.add(r);
    grandmaCookBook.delete(r);
    assertTrue(grandmaCookBook.listOfRecipe().isEmpty());
    assertNull(grandmaCookBook.exists(2));
  }

  @Test
  public void deleteNotAvailableRecipe() {
    Recipe r = new Recipe("recipe1", "description1");
    Boolean result = grandmaCookBook.delete(r);
    assertFalse(result);
  }

  @Test
  public void editRecipe() {
    Recipe r = new Recipe("recipe1", "description1");
    grandmaCookBook.add(r);
    grandmaCookBook.edit(r, "recipe2", "description2");
    assertEquals(grandmaCookBook.exists(4).getName(), "recipe2");
    assertEquals(grandmaCookBook.exists(4).getDescription(), "description2");
  }

  @Test
  public void emptyJson() {
    assertEquals(grandmaCookBook.toJSON(), "[]");
  }

  @Test
  public void notEmptyJson() {
    grandmaCookBook.add(new Recipe("recipe1", "description1"));
    assertNotSame(grandmaCookBook.toJSON(), "[]");
  }
}
