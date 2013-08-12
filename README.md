--- Team 5 notes to Dr. Cay Hortsman ---

  Group Members

	Surafel Defar arifusew@gmail.com,
	Karthik Reddy karthikreddy.vakati@gmail.com,
	Nicolas Baudet nicolas.baudet@heig-vd.ch,
	raphael Schmutz raphael.schmutz@heig-vd.ch

Hello professor we decided to make the android project and the website a little startup. we started of with java but we decided to expand the website as a real startup therefore we wanted a server side programming language and framework that we are all familiar with, which is python and web2py framework. The android application is of course written in java and we used jquery on the client side so we still are going to apply what we learned in school. We know this will make grading difficult for you so we made the code available as a packaged application to run. We also hosted our web application on amazon ec2 for an even easier process and our android application is fetching data from there. 

amazon ec2 link: http://ec2-54-213-121-196.us-west-2.compute.amazonaws.com/bookApp/default/index

--- how to run the webServer and our application ---
- from the bookAppDevelopmental Folder run "python web2py.py -a test -p 8000" from the command line
	* you need python 2.5, 2.6 or 2.7 to run
- go to http://127.0.0.1:8000 on a web browser to run the web application
- web2py is an MVC framework, so you will find the model, view and 
	controller files we used in Team 5 CS 180 project/bookAppDevelopmental/applications/bookApp folder

---- how to use the websiste ---
- it is intended to be a simple book sharing website for sjsu students, many features are missing right now but will expand the feature set and schools in the future as this will be our pet project
- for now you can view and post books on the website
