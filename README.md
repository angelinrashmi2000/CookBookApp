This webapp uses a simple javax servlet to add, edit and delete a recipe to a cookbook.
The following data are stored for a recipe
1. recipe name
2. description
3. recipe identifier

URL /CookBook/index.jsp is for adding, deleting, viewing and editing recipe 
and URL /CookBook/list.json returns the recipes in JSON format

The following are the improvements which can be done

1. Currently, when editing a recipe by more than one user the latest changes will be saved. 
This can be improved by allowing only one user to edit at a time and notify the other users 
that a user is already editing.

2. The recipe can have fields like ingredients, cooking time so that user can filter based on these
criteria.

3. Currently, deleting a recipe which is already deleted does simply skip deleting operation. A user notification that a recipe has already been deleted by another user should be displayed. 
