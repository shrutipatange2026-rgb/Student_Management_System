# Student Management System #

# Overview
This is a simple **Student Management System** built using **Java** and **MySQL**.  
It allows users to **Add, Update, Delete, and View** student records via a GUI application.


## Branch
All source code is in the **" master " branch**.

## Project Structure
All code files are inside the `src/model/` folder:

- `Student.java` → Contains the Student class (POJO)
- `StudentDAO.java` → Handles all database operations (Add, Update, Delete, View)
- `StudentManagementGUI.java` → GUI interface for managing students


## Database Setup (MySQL)

**Run the following SQL commands to create the database and table:**

```sql
-- 1. Create the database
CREATE DATABASE studentdb;
USE studentdb;

-- 2. Create the students table
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    contact BIGINT,
    course VARCHAR(50)
);

-- Database is now ready for the application
-- Make sure to update username/password in StudentDAO.java if required

Author : Shruti Patange

Student | BCA Aspirant | Maharashtra, India
