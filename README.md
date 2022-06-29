# Course-Management-System
File-base
Java
Swing

Course Management System repository consists of 14 classes. The data in this  project are managed by file handling (.txt file). 
There are all together 5 text files. 
The data in the files are separated by a tab (\t) and they are read and stored in array . 
This is how data is circulated through out the classes using those data. 
So, those data are read and wrtten in CourseManager, ModuleManager, StudentFileManager, TutorFileManager class and 
hence has many associations with other classes.
The mainGui class has the main function which will operate this overall project. It has an association 
relation with SignIn class which consists of the signIn() function. Then it validates among student, tutor 
and admin username and password. 
It will prompt the user to admin, student or tutor dashboard depending on the type of account. 
TutorSpace class has the tutor dashboard, StudentSpace class has the student dashboard and AdminControl class has the admin dashboard. 
It will have association with course, module, tutor,, student etc managers as their data are being manipulated
