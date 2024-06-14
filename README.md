# JobPortal System

## Overview

JobPortal is a comprehensive Java-based application designed to streamline the job search and hiring process. This system allows job seekers to find and apply for jobs, while employers can post job listings and manage applications. The backend is powered by MySQL, ensuring robust data management and storage.

## Features

- User authentication and authorization for both job seekers and employers
- Job listing creation and management by employers
- Job search and application by job seekers
- Application management for employers
- Profile management for both job seekers and employers

## Prerequisites

Before running the JobPortal system, ensure you have the following installed:

- Java Development Kit (JDK) 8 or later
- MySQL Server
- MySQL Connector/J (Java MySQL driver)
- IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor

## Setup Instructions

### 1. Clone the Repository

Clone the repository to your local machine using the following command:

```sh
git clone https://github.com/yourusername/jobportal.git
```

### 2. Set Up the MySQL Database

1. Open MySQL Workbench or your preferred MySQL client.
2. Create a new database named `jobportal`.
3. Execute the SQL script (provided separately) to create the necessary tables and initial data.

### 3. Configure the Database Connection

1. Open the `src/main/resources/database.properties` file.
2. Update the file with your MySQL database connection details:

```properties
db.url=jdbc:mysql://localhost:3306/jobportal
db.username=your_mysql_username
db.password=your_mysql_password
```

### 4. Build and Run the Application

#### Using an IDE

1. Open the project in your preferred IDE.
2. Ensure that the MySQL Connector/J library is added to the project's dependencies.
3. Build and run the application from the IDE.

#### Using Command Line

1. Navigate to the project directory.
2. Compile the project using Maven:

```sh
mvn clean install
```

3. Run the application:

```sh
java -jar target/jobportal-1.0-SNAPSHOT.jar
```

## Usage

1. **User Registration**: Register as a job seeker or employer by providing the necessary details.
2. **User Login**: Log in with your credentials to access the system.
3. **Job Listings**: 
    - **For Employers**: Post new job listings and manage existing ones.
    - **For Job Seekers**: Search for jobs by keywords, location, and other filters.
4. **Job Application**: Apply for jobs directly through the portal.
5. **Manage Applications**: 
    - **For Job Seekers**: View the status of your applications.
    - **For Employers**: Review applications and update their status.
6. **Profile Management**: Update your personal information and preferences.

## Contributing

We welcome contributions to the JobPortal system. To contribute, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

If you have any questions or feedback, please contact us at [your-pranaypatel9696@gmail.com].

---

Thank you for using the JobPortal system! We hope it helps you find the perfect job or the ideal candidate.
