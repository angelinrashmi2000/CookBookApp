<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Grandma's Cook Book</title>
</head>
<%@page import="grandma.cookbook.Recipe"%>
<%@page import="java.util.List" %>
<body>
	<form action="" method="post">
		<label for ="recipeName">Recipe Name</label>
		<input type="text" name="recipeName" />
		<label for ="recipeDescription ">Recipe Description</label>
		<input type="text" name="recipeDescription" />
		<input type="submit" name="add" value="add" />
	</form>
	<%
		if (request.getAttribute("error") != null) {
	%>	  
			<p style="color:red;"> Invalid Recipe Name or Description</p>
	<% 	
		}
	%>
	<%
		if (request.getAttribute("listOfRecipe") != null) {
		  List<Recipe> listOfRecipe =(List<Recipe>) request.getAttribute("listOfRecipe");
	      for(int i=0;i<listOfRecipe.size();i++)
	      {
	     
	%>
				<form action="" method="post">
					<% if (request.getAttribute("editmode") == null && request.getAttribute("id")== null) 
						{
					%>
						<input type="text" name="recipeName" value="<%= listOfRecipe.get(i).getName() %>" disabled/>
						<input type="text" name="recipeDescription" value="<%= listOfRecipe.get(i).getDescription() %>" disabled/>
					<% 
						} else { 
							if( (int)request.getAttribute("id") == listOfRecipe.get(i).getRecipeId())
							{
				     %>
								 <input type="text" name="recipeName" value="<%= listOfRecipe.get(i).getName() %>"/> 
								 <input type="text" name="recipeDescription" value="<%= listOfRecipe.get(i).getDescription() %>"/>
								 <input type="submit" name="ok" value="ok" />
					<%
							} else {
				 	%>
								 <input type="text" name="recipeName" value="<%= listOfRecipe.get(i).getName() %>" disabled/>
								 <input type="text" name="recipeDescription" value="<%= listOfRecipe.get(i).getDescription() %>" disabled/>
					 <%
					 			}
							}
					%> 
					<input type="submit" name="delete" value="Delete" />
					<input type="submit" name="edit" value="Edit" />
					<input id="id" name="id" type="hidden" value="<%= listOfRecipe.get(i).getRecipeId() %>" />
					<%
						if (request.getAttribute("editerror") != null && (int)request.getAttribute("id") == listOfRecipe.get(i).getRecipeId())
						{
					 %>	  
						<p style="color:red;"> Invalid Recipe Name or Description</p>
					<% 	
						}
					%>
				</form>
	<% 
			}
	    } else { 
	%>
	<h1>No recipe in database</h1>          
	<% 
		} 
	%>  
</body>
</html>