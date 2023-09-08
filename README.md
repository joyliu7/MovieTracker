# A Movie Tracking Application

## About This Application

*This application is designed for* ***movie lovers***.
There are countless movies in human history, and a
large number of new movies are being created every year.
Therefore, sometimes it can be hard to track the movies of your
interest.
*This application is designed to* ***help you track movies***. You can
**enter information** about a movie, **add** it to your list, and
**mark** it as watched. Some other features are also available
for better tracking.

I am interested in this project because *I am a movie
lover myself*. Hopefully, this application can help
more movie lovers like me!

## User Stories

- As a user, I want to be able to add a movie to my collection
- As a user, I want to be able to view the list of movies in my collection
- As a user, I want to be able to mark a movie as watched
- As a user, I want to be able to remove a movie from my collection
- As a user, I want to be able to save my movie list to file
- As a user, I want to be able to load my movie list from file

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking the "Add" button
- You can generate the second required event related to adding Xs to a Y by selecting the item you want to remove and 
     clicking the "Remove" button
- You can locate my visual component by minimizing the application and seeing the dock bar icon
- You can save the state of my application by clicking the "Save" button
- You can reload the state of my application by clicking the "Load" button

# Phase 4: Task 2

Fri Dec 02 10:21:54 PST 2022
Added movie: "m1"
Fri Dec 02 10:21:59 PST 2022
Added movie: "m2"
Fri Dec 02 10:22:05 PST 2022
Added movie: "m3"
Fri Dec 02 10:22:10 PST 2022
Removed movie: "m1"
Fri Dec 02 10:22:12 PST 2022
Removed movie: "m3"
Fri Dec 02 10:22:13 PST 2022
Removed movie: "m2"

# Phase 4: Task 3

Changes for refactoring:
- In this application, users can add the same movie to the list multiple times. If I had more time, I will make the 
application reject duplication of adding. If a duplicate is added, an exception will be thrown.
- In this application, the user can enter anything they want into the "Watched?" text field. However, the "Watched?" 
status of a movie can only be "Y" or "N". If I had more time, I'll make the application throw an exception when the 
user enters a string other than "Y" and "N". Only "Y" and "N" will be accepted as proper "Watched?" status.
- In the GUI class, there are four inner classes: RemoveListener, AddListener, LoadListener, and SaveListener. Maybe I 
will separate these inner classes from the GUI class and think of other ways to make the GUI work. This may improve 
cohesion of the application.
