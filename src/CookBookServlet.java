
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grandma.cookbook.CookBook;
import grandma.cookbook.Recipe;


/**
 * Servlet implementation class CookBookServlet
 */
//@WebServlet("/CookBookServlet")
@WebServlet(description = "Cook Book Servlet", urlPatterns = { "/index.jsp" })
public class CookBookServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  CookBook                  grandmaCookBook;

  /**
   * Default constructor. 
   */
  public CookBookServlet() {
    // TODO Auto-generated constructor stub
    grandmaCookBook = new CookBook();

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    if (!grandmaCookBook.listOfRecipe().isEmpty())
      request.setAttribute("listOfRecipe", grandmaCookBook.listOfRecipe());

    if (request.getRequestURI().substring(request.getContextPath().length()).contains("/list.json")) {
      listRecipe(response);
    }
    else {
      RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
      dispatcher.forward(request, response);
    }
  }

  private void listRecipe(HttpServletResponse response) throws IOException {

    response.setContentType("application/json; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.print(grandmaCookBook.toJSON());
    out.close();

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");

    if (request.getParameter("delete") != null) {
      deleteRecipe(request);
    }
    else if (request.getParameter("edit") != null) {
      request.setAttribute("editmode", 1);
      request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
    }
    else if (request.getParameter("ok") != null) {
      editRecipe(request);
    }
    else if (request.getParameter("add") != null) {
      addRecipe(request);
    }
    doGet(request, response);
  }

  private void editRecipe(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("recipeName");
    String des = request.getParameter("recipeDescription");
    Boolean valid = validate_input(name, des);
    if (valid) {
      Recipe recipe = grandmaCookBook.exists(id);
      if (recipe != null)
        grandmaCookBook.edit(recipe, name, des);
    }
    else {
      request.setAttribute("editerror", true);
      request.setAttribute("editmode", 1);
      request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
    }

  }

  private void addRecipe(HttpServletRequest request) {
    String name = request.getParameter("recipeName");
    String des = request.getParameter("recipeDescription");
    Boolean valid = validate_input(name, des);
    if (valid) {
      Recipe r = new Recipe(name, des);
      grandmaCookBook.add(r);
    }
    else {
      request.setAttribute("error", true);
    }

  }

  private void deleteRecipe(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));
    Recipe r = grandmaCookBook.exists(id);
    if (r != null)
      grandmaCookBook.delete(r);
    else
      request.setAttribute("delerror", true);
  }

  private Boolean validate_input(String name, String des) {
    if (!name.isEmpty() && !des.isEmpty())
      return true;
    else
      return false;

  }

}
