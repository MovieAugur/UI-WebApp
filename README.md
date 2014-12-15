UI-WebApp
=========

The front-end and main application for Augur

The front-end is developed using HTML, CSS and and JQuery.
We have used JSP and Servlet technolgy in order to implement the web application backend.
The web application is deployed on Tomcat server and MySQL is used as a backend database.
When a user queries for a movie the search string is passed to backend servlet which in turn would fire a query on the DB to fetch results.
If data for the movie is found in the DB it is returned back to the JSP to be displayed to the user.
If data is not found in the DB then the servlet will make call to run augury for the searched movie and then query the DB again to fetch the data.
The movie posters to be showed for the movie are stored at the server side and the keyword for the image file is stored along with the movie data in the DB. 
