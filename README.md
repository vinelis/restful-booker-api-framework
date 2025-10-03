
# API Test Automation Framework - Rest Assured & Java
![Allure Report](https://img.shields.io/badge/Report-Allure-brightgreen.svg) ![Java](https://img.shields.io/badge/Java-11-blue.svg) ![Rest Assured](https://img.shields.io/badge/Rest%20Assured-5.3-green.svg) ![TestNG](https://img.shields.io/badge/TestNG-7.8-yellow.svg) ![Maven](https://img.shields.io/badge/Build-Maven-red.svg)

This project is a complete API test automation framework built to validate the `Restful-booker` web service. It has been developed from scratch as a portfolio piece to demonstrate best practices in modern API testing, including a layered architecture, object-oriented data modeling (POJOs), and comprehensive reporting with Allure.

The framework is architected to be **robust, scalable, and easily maintainable**, mirroring the standards of real-world enterprise applications.

## 🚀 Features

* **Layered Architecture**: Clear separation between the test layer (`tests`) and the data layer (`pojos`), promoting clean code and organization.

* **Object-Oriented Data Modeling**: Utilizes **POJOs (Plain Old Java Objects)** for clean, type-safe serialization and deserialization of JSON request and response bodies.

* **Advanced Reporting**: Integrated with **Allure Report** to generate detailed, interactive, and visually appealing test execution reports that are easy for all stakeholders to understand.

* **Dynamic Data Generation**: Employs the **JavaFaker** library to generate realistic, random test data for each execution, ensuring tests are dynamic and cover a wider range of inputs.

* **Full API Workflow Coverage**: Tests cover the entire API lifecycle, including creating resources, retrieving them, updating them with authentication, and deleting them.

* **Centralized Endpoint Management**: API routes are managed as constants in a dedicated class, preventing hardcoded URLs and simplifying maintenance.

## ✅ Implemented Test Scenarios

The framework automates the complete CRUD and authentication workflow for the booking API:

* **Create Booking (POST)**

    * Verify successful creation of a new booking with dynamic data.

* **Get Bookings (GET)**

    * Retrieve a list of all existing booking IDs.

    * Retrieve the details of a specific booking by its ID.

* **Update Booking (PUT)**

    * Verify that an existing booking can be successfully updated after authenticating.

* **Delete Booking (DELETE)**

    * Verify that an existing booking can be successfully deleted after authenticating.

* **Authentication Flow**

    * **Happy Path**: Successfully generate an authentication token.

    * **Negative/Security Cases**: Verify that protected endpoints (like `PUT` and `DELETE`) correctly return a `403 Forbidden` status when an invalid or no token is provided.

## 🛠️ Tech Stack

* **Language**: Java 11

* **API Automation**: Rest Assured

* **Test Runner**: TestNG

* **Build Tool**: Maven

* **Reporting**: Allure Report

* **JSON Serialization/Deserialization**: Jackson Databind

* **Data Generation**: JavaFaker

## ⚙️ Getting Started

### Prerequisites

* Java JDK 11 or higher installed.

* Apache Maven installed.

* Allure Commandline installed and configured in your system's PATH.

## ⚡ Running the Tests

You can run the entire test suite using the following Maven command. The tests will execute as defined in the `testng.xml` file.

```sh
mvn clean test
```

## 📊 Viewing the Allure Report

After running the tests, Allure results will be generated in `target/allure-results`. To view the HTML report, run the following command from the project root:

```sh
allure serve target/allure-results
```

This will generate the report and open it in your default web browser.

## 👤 Author

* **[Luca Vinelli]**

* **LinkedIn**: `https://www.linkedin.com/in/luca-vinelli-723291351/`

* **GitHub**: `https://github.com/vinelis`
```