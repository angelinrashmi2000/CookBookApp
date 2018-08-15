The requirement of the webapp is to design an online cookbook to save recipes. It has to allow adding new recipes, editing and deleting existing recipes.
This webapp uses a simple javax servlet to add, edit and delete a recipe to a cookbook and uses tomcat for deployment.
The following data are stored for a recipe.
1. recipe name
2. description
3. recipe identifier
The Recipe class has the above three fields. Recipe identifier is used to make deletion and editing instead of relying on the get(int index) of the ArrayList. This avoids confusion when more than one person deletes or edits the cookbook. The cookbook is an ArrayList of Recipe objects. 
URL /CookBook/index.jsp is for adding, deleting, viewing and editing recipe.
URL /CookBook/list.json returns the recipes in JSON format.

Maven is used fetching dependent libraries Junit and Gson. JUnit is used for unit testing the cookbook application and we use Gson for converting Java objects to JSON format fot the /CookBook/list.json API.
The following are the improvements which can be done

1. Currently, when editing a recipe by more than one user the latest changes will be saved. 
This can be improved by allowing only one user to edit at a time and notify the other users 
that a user is already editing.

2. The recipe can have fields like ingredients, cooking time so that user can filter based on these
criteria.

3. Currently, deleting a recipe which is already deleted does simply skip deleting operation. A user notification that a recipe has already been deleted by another user should be displayed.

4. The data is not persistent if the application shuts down. This can be avoided by designing and storing the recepies in the database. 
