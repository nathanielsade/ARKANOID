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

Open a terminal and clone the repository:

```sh
git clone https://github.com/nathanielsade/ARKANOID.git
cd ARKANOID
Compile the Java Files
Compile all Java files in the src directory and place the compiled classes in the bin directory:

sh
Copy code
javac -d bin -cp "lib/biuoop-1.4.jar" src/*.java
Create the JAR File
Create the JAR file named ArkanoidGameP4.jar using the MANIFEST.MF file located in the src directory and include all compiled classes:

sh
Copy code
jar cfm bin/ArkanoidGameP4.jar src/MANIFEST.MF -C bin .
Run the JAR File with Classpath
Run the JAR file and include the biuoop-1.4.jar library in the classpath:

sh
Copy code
java -cp "bin/ArkanoidGameP4.jar;lib/biuoop-1.4.jar" Ass6Game
How to Run the Project
Navigate to the Project Directory:

sh
Copy code
cd path/to/ArkanoidGameP4
Compile the Java Files:

sh
Copy code
javac -d bin -cp "lib/biuoop-1.4.jar" src/*.java
Create the JAR File:

sh
Copy code
jar cfm bin/ArkanoidGameP4.jar src/MANIFEST.MF -C bin .
Run the JAR File with Classpath:

sh
Copy code
java -cp "bin/ArkanoidGameP4.jar;lib/biuoop-1.4.jar" Ass6Game
Description of Classes
(Provide descriptions for your classes here)

Author
Nathaniel Sade
