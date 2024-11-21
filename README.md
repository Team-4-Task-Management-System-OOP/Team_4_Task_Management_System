TEAM 4 - TASK MANAGEMENT SYSTEM - JAVA PROJECT 1

# 1. Description
The project focuses on the main principles of Object Oriented Programming in Java
The main goal of this console application is to keep track of tasks.
First, a member is created, then a team is created. We implemented a login command to ensure that the member is in the team in which he will make modifications.
After proper login, he can create a board in the team he is in. Then he can create tasks (bugs, stories or feedback) which need to be in the logged team's board.
Tasks that are assinable (bugs and stories) are created with assignee "unassigned" by default. Then the member can assign a task to himself or other members if he is logged in.
Members have a name, team and a list of (assinable, feedback is not included because it is not assinable) tasks.
Teams have a name, lists of members and boards.
Boards have lists of tasks, which also includes a list of assinable task for bugs and story. It uses overloading to determine if a task is assinable or not, because a member can add a feedback to the board, which is not assinable.
All of these models above have a list of activity history, which is registered every time an action is made, based on the class functionality.
There is a Task Base class, which includes an ID (which is managed by repository to ensure uniqueness), title, description and a list of commments. It is used as a foundation to the other task classes.
Feedback shares the base class characteristics, as well as having a feedback rating and feeback status, both of which are unique for the class.
Story shares the base class characteristics, while also having a Priority type (which is shared with Bug), story size, story status and member assignee.
Bug shares the base class characteristics, has a Priority type (shared with Story), list of reproducible steps, bug severity, bug status and member assignee.
We also implemented a login functionality, which validates that the member exists in the team and could make changes in the system.

For clarification:
•	Priority is one of the following: High, Medium, or Low.
•	Severity is one of the following: Critical, Major, or Minor.
•	Bug Status is one of the following: Active or Done.
•	Story Size is one of the following: Large, Medium, or Small.
•	Story Status is one of the following: Not Done, InProgress, or Done.
•	Feedback Status is one of the following: New, Unscheduled, Scheduled, or Done.

# 2. Commands
For the purposes of this project, we implemented a redacted engine, which takes the command, recognizes it from the enums of commands, and deletes the command parameter. After which, it takes what is type based on the command type and executes its functionalities.
In order to make long titles, descriptions, reproducible steps etc., we implemented a new functionality, which takes the first (") that it finds in the parameter, which indicates that the parameter is going to be a sequence of strings until the end of (") 
EXAMPLE: addcomment "long content for comment examople" John 3 (content, author, id of task to add the comment to).

LIST OF AVAILABLE COMMANDS:
*note - not all commands witch "" are necessary to have "", it just indicates that the parameter is going to be a sequence of strings for better visualization.

CREATION COMMANDS:
Adds comment to a desired task, not needed to be a member to add comments, neither requires login.
addcomment "description" authorName taskId

Creates a new member, does not require login, needs to be a unique name, does not require login
createmember memberName 

Creates a new team, does not require login, needs to be a unique name, does not require login
createteam teamName

Adds a member to a team, does not require login
addmembertoteam memberName teamName

Logins a member in order to make modifications in the system, need to be a valid member in a team first, does not require login
login member team

Logs out the current logged in member, requires login
logout

Creates a board in the desired team, requires login
createboard boardName boardName

Creates a new feedback in the desired board, requires login
createfeedback "title" "description" rating boardName

Creates a new story in the desired board, requires login
createstory "title" "description" priorityType storySize boardName

Creates a new bug in the desired board, requires login *note reproducible steps need to be separated by "," to appear as numbered list
createbug "title" "description" "step 1, step2, step3," priorityType bugSeverity boardName

LISTING COMMANDS:
ToDo

MODIFICATION COMMANDS:

Assigns an assinable task to a member in a team, requires login
assigntask id memberName teamName

Changes priority of Bug, requires login
changepriorityofbug id newPriority teamName

Changes priority of Story, requires login
changepriorityofstory id newPriority teamName

Changes rating of feedback, requires login
changeratingoffeedback id newFeedback teamName

Change severity of bug, requires login
changeseverityofbug id newSeverity teamName

Change size of story, requires login
changesizeofstory id newSize teamName

Change status of bug, requires login
changestatusofbug id newStatus teamName

Change status of feedback, requires login
changestatusoffeedback id newStatus teamName

Change status of story, requires login
changestatusofstory id newStatus teamName


# 3. Design proccess
First we started with designing the structure of the project and creating the logical sequence in the models. Then we implemented all the interfaces and implementations for the classes. 
Some tasks are assignable, so a logical addition was needed. Then we created the core of the program.- TaskManagementRepository, TaskManagementEngine and CommandFactory. 
The next step was to create all the commands responsible for running the application. 
After implementation of each command, we tested it with sample inputs, which then we added to the main input and expected output of the program. 
After all of the main functionality was done, we started creating tests for models, then repository, then commands. We found some bugs while doing tests, which was a good validation for the functionalities.

## 4. SAMPLE INPUT

showallmembers
showallteams
createmember sasho
createmember sasho
createmember pesho
createmember vi
showallmembers
createteam Managers
createteam QE
createteam JavaOOP
createteam JavaOOP
showallteams
createboard Java tasks
addmembertoteam pesho JAVAOOP
addmembertoteam pesho JAVAOOP
login pesho JAVAOOP
login pesho JAVAOOP

createboard python jokes
createboard python JavaOOP
showteamactivity JavaOOP
showmemberactivity pesho
createboard JavaTasks JavaOOP
createfeedback "Amazing work!" "good practice with stringbuilders!" 9 JavaTasks JavaOOP
createboard PythonTasks JavaOOP
createboard PythonTsks JavaOOP
showallteamboards JavaOOP
showboardactivity JavaOOP
showboardactivity JavaTasks
createmember tisho
addmembertoteam JavaOOP
addmembertoteam tisho JavaOOP
showmemberactivity tisho
createstory "has to happen tomorrow" "it has to be able to open for tomorrow afternoon" high medium JavaTasks
addcomment "will look into it soon" tisho 2
createbug "DOESNT WORK AS INTENDED" "Fix payment transaction implementation" "copy the input in file, try to pay, read the exception." high critical JavaTasks
showallteammembers JavaOOP
assigntask 3 tisho JavaOOP
unassigntask 3 tisho JavaOOP
assigntask 3 tisho JavaOOP




## 5. SAMPLE OUTPUT

There are no members in the system. Please add a member first.
####################
There are no teams to be shown. Please add a team first.
####################
A member with name "sasho" has been created!
####################
A member with this name already exists and cannot be created! Please, provide a different member name!
####################
A member with name "pesho" has been created!
####################
"vi" is an invalid member name! Name must be between 5 and 15 characters!
####################
---ALL MEMBERS---
---Member №1---

Member Name: sasho
Member Team: Unassigned
---Member Tasks---
---NO TASKS IN MEMBER'S LIST TO DISPLAY---
Add a task first!

---Member History---
[21-ноември-2024 02:33:07] - A member with name "sasho" has been created!

####################
---Member №2---

Member Name: pesho
Member Team: Unassigned
---Member Tasks---
---NO TASKS IN MEMBER'S LIST TO DISPLAY---
Add a task first!

---Member History---
[21-ноември-2024 02:33:07] - A member with name "pesho" has been created!

####################
A team with name "Managers" has been created!
####################
Team's name must be between 5 and 15!
####################
A team with name "JavaOOP" has been created!
####################
A team with this name already exists and cannot be created! Please, provide a different team name!
####################
---ALL TEAMS---
---Team №1---

Team Name: Managers
---Team Members---
---NO MEMBERS TO DISPLAY---
Add a member first!

---Team Boards---
---NO BOARDS TO DISPLAY---
Add a board first!

---Team History---
[21-ноември-2024 02:33:07] - A team with name "Managers" has been created!

####################
---Team №2---

Team Name: JavaOOP
---Team Members---
---NO MEMBERS TO DISPLAY---
Add a member first!

---Team Boards---
---NO BOARDS TO DISPLAY---
Add a board first!

---Team History---
[21-ноември-2024 02:33:07] - A team with name "JavaOOP" has been created!

####################
You are not logged in! Please login first!
####################
Member "pesho" was added to team "JAVAOOP".
####################
Member "pesho" is already in the team!
####################
Member pesho successfully logged in!
####################
Member pesho is already logged in! Please log out first!
####################
Command cannot be empty.
####################
No team with name "jokes" exists!
####################
Board with name "python" has been successfully created and added to team "JavaOOP!"
####################
[21-ноември-2024 02:33:07] - A team with name "JavaOOP" has been created!
[21-ноември-2024 02:33:07] - Member "pesho" was successfully added to team JavaOOP.
[21-ноември-2024 02:33:07] - Member "pesho" was added to team "JAVAOOP".
[21-ноември-2024 02:33:07] - Board "python" was successfully added to team JavaOOP.
[21-ноември-2024 02:33:07] - Board with name "python" has been successfully created and added to team "JavaOOP!"

####################
[21-ноември-2024 02:33:07] - A member with name "pesho" has been created!
[21-ноември-2024 02:33:07] - Member pesho assigned to team JavaOOP.
[21-ноември-2024 02:33:07] - Member pesho successfully logged in!

####################
Board with name "JavaTasks" has been successfully created and added to team "JavaOOP!"
####################
Feedback with ID 1 and title "Amazing work!" was created.
####################
Board's name must be between 5 and 10 symbols long!
####################
Board with name "PythonTsks" has been successfully created and added to team "JavaOOP!"
####################
---ALL TEAM BOARDS---
---Board №1---

Board Name: python
---Board Tasks---
---NO TASKS IN BOARD'S LIST TO DISPLAY---
Add a task first!

---Board History---
[21-ноември-2024 02:33:07] - Board with name "python" has been successfully created and added to team "JavaOOP!"

####################
---Board №2---

Board Name: JavaTasks
---Board Tasks---
Id: 1
Task Type: FEEDBACK
Title: Amazing work!
Description: good practice with stringbuilders!
Rating: 9
Feedback Status: New
Comments: ---NO COMMENTS ADDED TO TASK TO DISPLAY---
History: [21-ноември-2024 02:33:08] - Feedback with ID 1 and title "Amazing work!" was created.



---Board History---
[21-ноември-2024 02:33:07] - Board with name "JavaTasks" has been successfully created and added to team "JavaOOP!"
[21-ноември-2024 02:33:08] - Task with name "Amazing work!" has been added to board "JavaTasks"

####################
---Board №3---

Board Name: PythonTsks
---Board Tasks---
---NO TASKS IN BOARD'S LIST TO DISPLAY---
Add a task first!

---Board History---
[21-ноември-2024 02:33:08] - Board with name "PythonTsks" has been successfully created and added to team "JavaOOP!"

####################
No board with name "JavaOOP" exists!
####################
[21-ноември-2024 02:33:07] - Board with name "JavaTasks" has been successfully created and added to team "JavaOOP!"
[21-ноември-2024 02:33:08] - Task with name "Amazing work!" has been added to board "JavaTasks"

####################
A member with name "tisho" has been created!
####################
Invalid number of arguments. Expected: 2, received: 1.
####################
Member "tisho" was added to team "JavaOOP".
####################
Story with ID 2 and title "has to happen tomorrow" was created.
####################
Comment was added successfully to task with title "has to happen tomorrow" and ID 2. Author of comment: "tisho"
####################
Bug with ID 3 and title "DOESNT WORK AS INTENDED" was created.
####################
---ALL MEMBERS IN TEAM "JavaOOP"---
---Member №1---

Member Name: pesho
Member Team: JavaOOP
---Member Tasks---
---NO TASKS IN MEMBER'S LIST TO DISPLAY---
Add a task first!

---Member History---
[21-ноември-2024 02:33:07] - A member with name "pesho" has been created!
[21-ноември-2024 02:33:07] - Member pesho assigned to team JavaOOP.
[21-ноември-2024 02:33:07] - Member pesho successfully logged in!

####################
---Member №2---

Member Name: tisho
Member Team: JavaOOP
---Member Tasks---
---NO TASKS IN MEMBER'S LIST TO DISPLAY---
Add a task first!

---Member History---
[21-ноември-2024 02:33:08] - A member with name "tisho" has been created!
[21-ноември-2024 02:33:08] - Member tisho assigned to team JavaOOP.

####################
Task with ID 3 was assigned to "tisho"
####################
Task with ID 3 was unassigned from "tisho"
####################
Task with ID 3 was assigned to "tisho"
####################
Bug priority changed
####################
Story priority changed
####################
Successfully changed the rating of feedback '1' to 6.
####################
Successfully changed the severity of bug "3" to Major."
####################
Successfully changed the status of bug '3' to Done.
####################
Successfully changed the size of story '2' to Small.
####################
Successfully changed the status of feedback '1' to Unscheduled.
####################
Successfully changed the status of story '2' to In progress.
####################

Task with ID 2 was assigned to "tisho"
####################
