# WebEngineering Module, Graded Exercise

## Commit Proposal

Matriculation Number: 17-549-122
Email               : anessollah.ima@students.fhnw.ch

Project idea short description:

Financial Expenses Tracker
- CRUD with expenses and categories
- assign expenses to categories
- show statements for a certain time
- greatest expenses for categories

Possible extension:
- Interactive assignment (e.g. drag and drop)
- Net worth calculator
- financial adviser

## Project confirmation

Very good. Good separation of basic and extension features. Confirmed.

D. König


## Project delivery <to be filled by student>

How to start the project: (if other than `grailsw run-app`)

How to test the project:  (if other than `grailsw test-app`)

Hand-written, static HTML 
project description:      (if other than `index.html` in project root directory)

####External contributions:
-no external apis used
-this youtube playlist helped me a lot: https://www.youtube.com/watch?v=ekWwSZeywGw&list=PLNbZquqjvY1nI3gdgV71C8NAB_IRBIFAH


####Other comments: 
seeded some default data:
-User: admin, password: admin
-User: test, password: test

I tried to delete the materialized scaffold, but the application just would not run without it.
So here are the views that can be ignored (which I tried to delete):
-Roles:         everything
-Categories:    everything
-Users:         create.gsp and edit.gsp
-Expenses:      create.gsp, edit.gsp and show.gsp

####I'm particular proud of: 
-the role based security, because I struggled with the setup, but it works nicely
-the analysis page which shows the total amount for each category for the current user.
 It also shows the biggest expense for the category first, so that you can really see which is the worst

## Project grading 

index.html given and fully valid, includes semantic elements.

The application runs smoothly.
It would have been nice if the logged-in user would have been the default selection when
creating an expense. 
It appears that changing the month in the statement view is ignored - but one has to hit an additional "enter",
which is not apparent from the view. It might have been good to add a button.

Functionality is an information system with security plus crud operations for two domain classes in a one-to-many relation.
Data setup in bootstrap, specialized views, validation via constraints.

Engineering:
* good and extensive git log (a bit tail-heavy towards the deadline)
* very nice and extensive testing.
  Two "get" tests fail when run from inside the IDE but work fine from console.
  That is most likely a sequencing issue, which can be solved by properly cleaning up after each test.
  I count this as ok, though.  
* some html validity issues, e.g. in the statement.gsp "No li element in scope but a li end tag seen."
* comments are mostly missing

It is good that you are aware of the materialized scaffold issue.
It appears you have solved it for the Category controller and views.
For the RoleController there is no reason to have it materialized.
If you would have had more than 6 minutes before the deadline, you might have solved the issue ;-)
Since you are aware of the issue and at least tried to solve it, I only subtract one point.

Congratulations!
You created a fully functional, distributed, interactive, security-enabled, database-backed, web application
from scratch on your own in a rather tight time frame showing solid knowledge of WebMVC and engineering practices.

Grade: 5.9
