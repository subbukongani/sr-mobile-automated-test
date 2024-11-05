# MobileAutomationProject

## Overview
The MobileAutomationProject is designed for automated testing of mobile applications using Appium. This framework aims to provide a robust and scalable approach for testing mobile apps to ensure functionality and user experience across various devices.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [Contact](#contact)

## Features
- Cross-platform support for Android applications.
- Modular architecture for easy maintenance and scalability.
- Customizable test configurations using YAML files.
- User credential management for different user types.
- Comprehensive logging and error handling mechanisms.

## Technologies Used
- **Java** - Programming language used for test scripting.
- **Appium** - Open-source tool for automating mobile applications.
- **TestNG** - Testing framework for executing test cases.
- **YAML** - Human-readable data serialization standard used for configuration files.
- **Maven** - Project management tool for building and managing dependencies.

## Installation
Clone the repository:

```bash
git clone https://github.com/yourusername/MobileAutomationProject.git
cd MobileAutomationProject

# Setup Instructions

Ensure you have Java and Maven installed on your machine.

## Install Appium:

```bash
npm install -g appium
```

## Install project dependencies by running:

```bash
mvn install
```

## Configuration

Update the `resources/credentials.yaml` file with user credentials:

```yaml
users:
  free:
    email: "your_free_user_email@example.com"
    password: "your_free_user_password"
  premium:
    email: "your_premium_user_email@example.com"
    password: "your_premium_user_password"
```

Modify the `resources/androidCapabilities.yaml` file for your device configuration:

```yaml
platformName: "Android"
deviceName: "YourDeviceName"
automationName: "UiAutomator2"
appPackage: "com.yourapp.package"
appActivity: "com.yourapp.activity"
```

## Running Tests

To run the automated tests, use the following command:

```bash
mvn test
```

This will execute all test cases defined in the project, located in the `src/test/java/com/automation/tests/` directory.

## Project Structure

```
MobileAutomationProject/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── config/
│   │               │   └── DriverConfig.java
│   │               ├── driver/
│   │               │   ├── DriverGenerator.java
│   │               │   └── DriverInstance.java
│   │               └── screens/
│   │                   ├── OnboardingScreen.java
│   │                   ├── LoginScreen.java
│   │                   └── ...
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── automation/
│                   ├── tests/
│                   │   └── OnboardingTest.java
│                   └── BaseTest.java
│
└── resources/
    ├── androidCapabilities.yaml
    └── credentials.yaml
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any changes or improvements.

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a pull request.

## Contact

For inquiries or feedback, please reach out to:

Your Name - your.email@example.com  
Project Link: [GitHub Repository](https://github.com/your/repository)
