# Gym Journal

## Recognizing Trends and Progress

Questions:
- What will the application do?
- Who will use it?
- Why is this project of interest to me?

An example of text with **bold** and *italic* fonts. 

The main purpose of this application is to keep of progress and trends at the gym. This will help visualize
and identify your current state and your goals. Things that it will keep track of could include, but not limited to
personal records, weight, nutritional intake... This application will be used for anyone
who wishes to keep track of their progress all under 1 application. This project is of interest to me
because i **love** going to the gym, however I only keep track of my nutritional intake through spreadsheets,
and would like to keep it together with all my lift progress as well. Ultimately this will help
me stay focused towards reaching my goals at the gym.

## User Stories

- As a user, I want to be able to add a workout and food to the day
- As a user, I want to be able to see different days through a calendar
- As a user, I want to be able to go back and edit the day's food and workout
- As a user, I want to be able to keep count of my macros for food for each day
- As a user, I want to be able to save the calendar to file before exiting.
- As a user, I want to be able to reload and resume from where it's left off when reloading.

##Instructions for grader

- You can generate the first required event by clicking creating a new day, enter your inputs and add it to the 
calendar. The inputs (Day, Food, Exercise, Workout) are all classes created that are added
into each other (Exercise -> Workout, Exercise & Food -> Day, Day -> Calendar)

- You can generate the second required event by Journal History in the main menu, this will show
all the past days that are saved

- You can locate my visual component by going into Journal History, then clicking "View Calories Over Time"
.This will show a graph

- You can save the calendar by clicking the exit file, or selecting "Save Calendar" independently.

- You can  reload the calendar when you rerun the program. 

##Phase 4: Task 2
I have chosen to test and design a class that is robust. All the inputs from CalendarApp uses a method called intParse, which checks if the 
input is a integer, if not it will throw a ValidIntegerException and return 0.

##Phase 4: Task 3
- Problem 1: A lot of code is repeated in the GuiInputs class, I will improved coupling by extracting a method and calling it from the various places 
the code exists in.
- Solution 1: I made a method called "confirmationTemplate", which creates a confirmation box and takes in a string
for the text. 
- Low cohesion is currently found in the guiInputs class, as that class was originally intended for mainly inputs,
but includes many methods that set up the actual window alongside the inputs. I created a new class to help with setting up the gui layouts.
- Solution 2: I made a controller class which will reference the Calendar.fxml file which stores all the layout properties in order to 
keep the methods precise and short. In doing so, all the methods in main/guiInputs are either setting up, or taking the input as intended.