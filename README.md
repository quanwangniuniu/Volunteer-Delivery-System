# Campus Crowdsourced Delivery Platform

## Introduction

The Campus Crowdsourced Delivery Platform is a web-based application designed to facilitate efficient delivery services within a university campus environment. It allows users to create, accept, and manage delivery tasks, leveraging a crowdsourced approach to optimize delivery routes and ensure timely deliveries.

This README provides an overview of the platform's features, technologies used, and instructions for setting up and running the application.

## Features

- **Dispatching**: Users can create delivery tasks and dispatch them to available delivery agents.
- **Bidding**: Delivery agents can browse available tasks and bid on those they wish to fulfill.
- **Order Evaluation**: Both users and delivery agents can provide feedback and ratings for completed orders.
- **Payment Integration**: The platform includes a payment gateway for secure transactions.
- **Points System**: Users can accumulate points through completed orders, which can be redeemed for book exchanges.

## Technologies Used

### Frontend
- Vue.js 2
- npm
- Webpack

### Backend
- Spring Framework
- Java
- Maven

## Setup Instructions

### Frontend
1. Clone the repository.
2. Navigate to the frontend directory.
3. Install dependencies using npm: `npm install`.
4. Build the project: `npm run build`.
5. Run the development server: `npm run serve`.

### Backend
1. Clone the repository.
2. Navigate to the backend directory.
3. Ensure Java and Maven are installed on your system.
4. Build the project: `mvn clean install`.
5. Run the application: `java -jar <name_of_jar_file>`.

## Database Operations

The backend utilizes Spring's annotation-based approach for database operations. This includes the use of annotations for dependency injection and database interactions.

## Contributing

Contributions are welcome! Feel free to submit bug reports, feature requests, or pull requests through the GitHub repository.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
