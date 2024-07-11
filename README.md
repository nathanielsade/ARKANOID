# ARKANOID Project

This project is an implementation of the classic Arkanoid game in Java, created as part of the Object-Oriented Programming (OOP) course.

## Introduction

As part of the OOP course, we were tasked with implementing a version of the old and familiar Arkanoid game. The game includes multiple stages with increasing difficulty levels and varied backgrounds. It is implemented in Java using OOP principles, incorporating features such as:

- Polymorphism and inheritance
- Familiarity with basic design patterns like the Observer pattern
- Use of Java collections and data structures
- Creation of game levels with different difficulties
- Adherence to the open/closed principle
- Working with GUI components

## Dependencies

- Windows / Linux / macOS
- Git
- Apache Ant (for building and running the project)
- Keyboard with Spacebar, "P" button, right and left arrows
- biuoop-1.4.jar: A required library for the GUI components

## Setup Process

### Clone the Repository
Compile all Java files in the src directory and place the compiled classes in the bin directory:

Create the JAR File
Create the JAR file named ArkanoidGameP4.jar using the MANIFEST.MF file located in the src directory and include all compiled classes:


##How to Run the Project
#Navigate to the Project Directory:
cd path/to/ArkanoidGameP4
#Compile the Java Files:
javac -d bin -cp "lib/biuoop-1.4.jar" src/*.java

#Create the JAR File:
jar cfm bin/ArkanoidGameP4.jar src/MANIFEST.MF -C bin .

#Run the JAR File with Classpath:
java -cp "bin/ArkanoidGameP4.jar;lib/biuoop-1.4.jar" Ass6Game
Description of Classes


Author
Nathaniel Sade
