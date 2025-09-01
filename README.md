# Doctor Patient Portal

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A web-based portal for managing doctor-patient appointments, built with Java, Servlets, and JSP. This project provides a simple and intuitive interface for patients, doctors, and administrators to manage healthcare interactions efficiently.

## 📸 Screenshots

*(Add screenshots of the application here. For example:)*

| Login Page | Patient Dashboard | Admin Panel |
| :---: | :---: | :---: |
| *(login.png)* | *(patient_dashboard.png)* | *(admin_panel.png)* |


## ✨ Features

The application has three main roles with distinct functionalities:

### 👨‍⚕️ Administrator
- **Login:** Secure login portal for the admin.
- **Manage Doctors:** Add, update, and delete doctor records.
- **View Specialists:** Manage and view the list of medical specializations.
- **View Patients:** See a list of all registered patients.

### 🩺 Doctor
- **Login:** Secure login portal for doctors.
- **View Appointments:** See a list of all appointments scheduled for them.
- **Update Appointment Status:** Comment on and update the status of appointments (e.g., "Completed").
- **Edit Profile:** Doctors can update their personal and professional details.

### 👤 Patient
- **Register & Login:** Patients can create an account and log in.
- **Book Appointment:** Schedule appointments with available doctors.
- **View Appointments:** Check the status and details of their appointments.
- **Change Password:** Update their account password.


## 🛠️ Technologies Used

*   **Backend:** Java, Servlets, JSP
*   **Database:** MySQL
*   **Build Tool:** Apache Maven
*   **Frontend:** HTML, CSS, Bootstrap
*   **Web Server:** Apache Tomcat


## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   Apache Maven
*   Apache Tomcat 9 or higher
*   MySQL Server

### 1. Clone the Repository

```bash
git clone https://github.com/pulkitbose18/doctor-patient-portal-using-java.git
cd doctor-patient-portal-using-java
```

### 2. Database Setup

1.  Open your MySQL client and create a new database named `hospital`.
    ```sql
    CREATE DATABASE hospital;
    ```
2.  Use the new database.
    ```sql
    USE hospital;
    ```
3.  Run the following SQL scripts to create the necessary tables:

    ```sql
    --
    -- Table structure for table `user_details`
    --
    CREATE TABLE `user_details` (
      `id` int NOT NULL AUTO_INCREMENT,
      `full_name` varchar(255) DEFAULT NULL,
      `email` varchar(255) DEFAULT NULL,
      `password` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    );

    --
    -- Table structure for table `doctor`
    --
    CREATE TABLE `doctor` (
      `id` int NOT NULL AUTO_INCREMENT,
      `full_name` varchar(255) DEFAULT NULL,
      `dob` varchar(255) DEFAULT NULL,
      `qualification` varchar(255) DEFAULT NULL,
      `specialist` varchar(255) DEFAULT NULL,
      `email` varchar(255) DEFAULT NULL,
      `phone` varchar(255) DEFAULT NULL,
      `password` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    );

    --
    -- Table structure for table `specialist`
    --
    CREATE TABLE `specialist` (
      `id` int NOT NULL AUTO_INCREMENT,
      `specialist_name` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    );

    --
    -- Table structure for table `appointment`
    --
    CREATE TABLE `appointment` (
      `id` int NOT NULL AUTO_INCREMENT,
      `user_id` int DEFAULT NULL,
      `full_name` varchar(255) DEFAULT NULL,
      `gender` varchar(255) DEFAULT NULL,
      `age` varchar(255) DEFAULT NULL,
      `appointment_date` varchar(255) DEFAULT NULL,
      `email` varchar(255) DEFAULT NULL,
      `phone` varchar(255) DEFAULT NULL,
      `diseases` varchar(255) DEFAULT NULL,
      `doctor_id` int DEFAULT NULL,
      `address` varchar(255) DEFAULT NULL,
      `status` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `user_id` (`user_id`),
      KEY `doctor_id` (`doctor_id`),
      CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    );
    ```

4.  **Important:** The project uses hardcoded database credentials. To run it, you must either update your MySQL `root` user's password to `Naman@2004` or modify the credentials in the source code at `src/main/java/com/hms/db/DBConnection.java` and recompile the project.

### 3. Build the Project

Use Maven to build the project, which will download dependencies and create a `.war` file in the `target` directory.

```bash
mvn clean install
```

### 4. Deploy to Tomcat

1.  Copy the generated `Healthcare-Management-System.war` (or similar name) file from the `target` directory.
2.  Paste it into the `webapps` directory of your Apache Tomcat installation.
3.  Start the Tomcat server.

### 5. Usage

*   Access the application at `http://localhost:8080/{your-war-file-name}/`.
*   **Admin Login:**
    *   **Email:** `admin@gmail.com`
    *   **Password:** `admin`

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
