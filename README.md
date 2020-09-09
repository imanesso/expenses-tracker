# Expenses Tracker
This application can be used to track your daily expenses. It was created for the FHNW module webec

## General Project Description
The expense tracker is used to track expenses and to categorise them. The web-application also has role based security which allows different things for the admins and normal users


## Project satisfaction
I have never worked with Grails before and I am not very experienced in web development. I struggled at first, but after a while I started to like it

I could not finish everything the way I wanted, because I struggled with the time. In the beginning I wanted to export the monthly statement to pdf, but I just couldn't make it work, so I reverted it completely. But overall I am happy that I have a functioning solution. 

## Project delivery

How to start the project: (if other than `grailsw run-app`)

How to test the project:  (if other than `grailsw test-app`)


I seeded some default data to make it easier to test after a start in the file Bootstrap.groovy.

    User: admin, password: admin
    User: test, password: test

### External contributions:
this youtube playlist helped me a lot:
 https://www.youtube.com/watch?v=ekWwSZeywGw&list=PLNbZquqjvY1nI3gdgV71C8NAB_IRBIFAH


## Features

### Security

**Role Admin**

This is how a navigation bar of an admin looks like

![admin navigation](/grails-app/assets/images/projectdescription/admin-nav.JPG)



Users with the role admin can create new users and categories additionally to all the functions that a normal user has. They can also see the expenses of all users in a parent-child view when they click on the user to show them.


**Role User**

This is how a navigation bar of a user looks like
user navigation

![user navigation](/grails-app/assets/images/projectdescription/user-nav.JPG)

Normal users can create expenses for all the categories that the admins define

The user can also see a monthly statement of all the expenses that they had

the user can see the expenses for all the categories that the admin created

### Expenses

![expenses](/grails-app/assets/images/projectdescription/expenses.JPG)

The CRUD functionalities are accessible in the index page, but there are also 2 new functionalities there

* Analyse: this page shows all the expenses for each category and their total amount
* Statement: The user can select a month to show a monthly statement of all the expenses

### Categories

The basic CRUD functionalities are accessible only for the users with the admin role

### User Management

The user and role management is only accessible for admins. The pages are used for basic CRUD functions

The admin can also see all expenses of a user in a parent child view when they show a user

