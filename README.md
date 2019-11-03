#### OVERVIEW
This project is a full-stack Spring MVC web application that allows users to retrieve create, delete, and update data from a database. This project is intended to allow us to familiarize ourselves with pull request from GitHub, using C.R.U.D functionality, Spring MVC and the DAO pattern.

### FLOW
The application begins in an HTML File where the user is displayed the options to:
-Search Film by ID
-Search Film by keyword
-Create new Film

Search Film by ID captures the annotation value that a user submits and sends it to the mapped method in the controller class. The method then passes the search ID value to the corresponding method in the Data Access Object class. Inside that method, a SQL string queries the results from the database to match the binding variable ( Film ID). The method returns a Film object back to the method in the controller class. This method creates a ModelAndView object of the film object attributes that are then displayed in a JSP file.

When a film is retrieved, its properties are displayed. The user is informed if there aren't any matching results. If the user selects the option to create a film, the user has the options to modify or delete that film – which follows the same flow logic as the search by ID option.

### Challenges
One of the more difficult aspects of this project was understanding how values are passed from different file types. We had an issue in a JSP form that uses text boxes to get the updated properties of a film. The values were all captured as strings, so when we sent those parameters to the controller to be redirected to the DAO, a 400 error kept appearing on the site. We decided to convert the string fields to their appropriate data types.

This halfway solved the issue. We were also capturing a backslash in the string value from the JSP page. This was happening because we did not have double quotes around the JSP expressions that was assigned each field value.  Once we resolved this issue the modify film option began working properly.
