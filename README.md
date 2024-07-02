# IXAC-BANK

This project is a comprehensive bank application developed in Java, featuring both Command Line Interface (CLI) and Graphical User Interface (GUI) implementations using Java Abstract Window Toolkit (AWT).

## Features

### CLI Version

The Command Line Interface (CLI) version is fully functional and includes:

- **Account Management**: Create, update.
- **Transaction Processing**: Perform deposits, withdrawals.
- **Balance Inquiry**: Check account balances and transaction histories.
- **User Authentication**: Secure login and user authentication mechanisms.

### GUI Version

The Graphical User Interface (GUI) version is a proof of concept and includes the following pages:

- **Home Page**: Welcome page for users.
- **Main Page**: Displayed after login.
- **Registration Page**: For new user sign-ups.
- **Settings Page**: Allows users to customize their experience.

The GUI is not fully functional. It serves as a base for further development. Users are encouraged to utilize the CLI code to enhance the GUI functionality.

## Technologies Used

- **Java**: Core language for implementing both CLI and GUI functionalities.
- **Java AWT**: For creating the graphical user interface.
- **MySQL**: Database for storing and managing data.
- **JDBC MySQL Driver**: For connecting the Java application to the MySQL database.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL JDBC Driver
- When you clone the project make sure to delete all lines of "package com.IXACBANK.TERMINAL;" in all files, I used my own package so you decide to create a new package with that name or better just remove it to avoid any conflicting errors. 

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Loki-whan12/ixac-bank
    ```
2. Navigate to the project directory:
    ```sh
    cd ixac-bank
    ```
3. Set up the MySQL database and make the required changes in the codes.
4. Update the database connection settings in the project code as needed.

### Running the CLI Version

1. Navigate to the CLI:
    ```sh
    cd CLI
    ```
2. Open and Edit this file, before running it, by just clicking on it:
   ```sh
   create_all_required_dir_and_files.bat
   ```
   contents of the file:
   ```sh
   cd C:\ProgramData\
    mkdir "IXAC BANK"
    cd "IXAC BANK"
    mkdir Database & mkdir Logs
    cd Database\
    mkdir Transactions
    cd /pathToWheretheMainExtractedCodesAre/
    java create_log_file.java
    ```
4. Compile the CLI code:
    ```sh
    javac Main.java
    ```
3. Run the CLI application:
    ```sh
    java Main
    ```

### Running the GUI Version


1. NAvigate to the CLI:
    ```sh
    cd CLI
    ```
    
2. Compile the CLI code:
    ```sh
    javac Home.java
    ```
3. Run the CLI application:
    ```sh
    java Home
    ```

## Contribution

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License.
