# API Automation Framework - Video Games Swagger API

## Project Description
This project is an API automation framework developed in Java using Rest-Assured, TestNG, and Maven. The framework automates test cases for the Video Games Swagger API, specifically for authentication and video game management.

The framework includes:
- Separation of concerns with service classes for each API endpoint.
- Automated tests to validate API responses.
- Continuous Integration setup with GitHub Actions.
- Docker support for containerized test execution.
- Allure reporting for detailed test execution results.

## Prerequisites

Before you begin, ensure you have the following installed on your local machine:
- **Java 11 or higher**
- **Maven** (if running locally without Docker)
- **Docker** (for containerized execution)
- **Git**

For Allure Reports, you will also need:
- **Allure Command Line** (for viewing reports locally)

### Installing Allure CLI:
Follow the installation instructions for your OS: [Allure Installation Guide](https://docs.qameta.io/allure/#_get_started).

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Kwasi-Asiedu-Mensah/api-automation-framework-fido.git
   cd api-automation-framework-fido
   ```

2. **Install dependencies**:
   If you are running the tests locally without Docker, install the Maven dependencies:
   ```bash
   mvn clean install
   ```

3. **Environment Configuration**:
   Configure the API endpoints and other environment variables by editing the `config.properties` file:
   ```properties
   baseUrl=https://www.videogamedb.uk
   username=admin
   password=admin
   ```

## Running the Tests Locally

To run the tests locally using Maven, use the following command:

```bash
mvn clean test
```

### Viewing Allure Reports

After running the tests, you can generate and view the Allure reports:

1. **Generate Allure report**:
   ```bash
   mvn allure:report
   ```

2. **Open Allure report**:
   ```bash
   mvn allure:serve
   ```

   The report will automatically open in your browser

Test results will also be generated in the `allure-results` directory.

## Running with Docker

If you want to run the tests inside a Docker container, follow these steps:

1. **Build and Run the Docker Container**:
   ```bash
   docker-compose up --build
   ```

   This command will:
   - Build the Docker image using the `Dockerfile`.
   - Run the API automation tests inside the container.

2. **Stop the Container**:
   After the tests finish running, you can stop the container using:
   ```bash
   docker-compose down
   ```

### Viewing Allure Reports in Docker

1. **Generate Allure Report in Docker**:
   Run the tests and generate the Allure report inside the container:
   ```bash
   docker exec -it api-automation mvn allure:report
   ```

2. **Serve the Allure Report**:
   View the report by running:
   ```bash
   docker exec -it api-automation mvn allure:serve
   ```

The report will be available on your local browser.

## Continuous Integration (CI) Setup

The project includes a CI pipeline using GitHub Actions to automatically run tests on every push or pull request to the `main` branch.

To check the CI status or add additional CI configurations:
1. **Check the `.github/workflows/ci.yml`** file for the pipeline configuration.
2. To view the CI build status or logs, go to the project's GitHub repository and click on the "Actions" tab.

## Project Structure

Here is an overview of the key directories and files in this project:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.videogames.api.config   # Configuration class for properties
│   │   │   ├── com.videogames.api.services # Service classes for each endpoint
│   ├── test
│   │   ├── java
│   │   │   ├── com.videogames.api.tests    # Test classes for each API endpoint
├── Dockerfile                               # Docker configuration for running the tests
├── docker-compose.yml                       # Docker Compose file for managing containers
├── allure-results                           # Allure test results
├── pom.xml                                  # Maven build configuration
└── README.md                                # Project documentation (this file)
```

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any suggestions or improvements.
