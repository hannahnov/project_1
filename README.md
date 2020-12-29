# The Tuition Reimbursement System (TRMS)
## Project Description
The Tuition Reimbursement System, TRMS, allows users to submit reimbursements for courses and training. The submitted reimbursement must be approved by that employee's supervisor, department head, and benefits coordinator. The benefits coordinator then reviews the grade received before finalizing the reimbursement.
## Technologies Used
* Java
* HTML
* CSS
* JDBC
* SQL
* JavaScript
* AJAX
* Azure
* Javalin
## Features
List of features ready and TODOs for future development
* Allows users to submit reimbursements for different events (courses, certification courses, certification examns, many more)
* The submitted reimbursement must be approved by that employee's director supervisor, The department Head, and a Benefits Coordinator in that order.
* The user can then add a grade to the reimbursement for final approval.
* Director supervisor can check reimbursement and leave a status.
* Department Head and Benifits Coordinator can place grades, also leave notes on the status of the reimbursement.
* Benefit Coordinator que change the rewarded amount on the reimbursement of the employee that submited.
To-do list:
* Implement JWTs for better session tracking and authurization.
* Implement more testing.
* Implement employees can cancel their reimbursement request feature
## Getting Started
- git clone https://github.com/hannahnov/project_1.git
- Within the connectionUtil.java, set your URL, Username, and Password in order to connect to your database
- Run the included SQL file in your database to setup tables and dummy data(replace dummy data as needed)
- If running within the Spring Tool Suit 4, within ServerDriver.java, specify your prefered port.
- Run the serverDriver.java and navigate to http://localhost:'port'/helloYara to test connectivity
## Usage
- Navigate to http://localhost:'port'/ to be redirected to the login page. Use the provided dummy data to explore all features.
