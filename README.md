# Spring Boot Project with Lombok

This project is a demonstration of using Spring Boot along with Lombok library. It features an in-memory list of donors and includes an endpoint to fetch donor details by name.

## Features:
- **Spring Boot**: Utilizes the Spring Boot framework for rapid development and easy configuration.
- **Lombok Integration**: Lombok library is integrated to reduce boilerplate code, enhancing readability and maintainability.
- **In-Memory List**: The project includes an in-memory list of donors, enabling quick retrieval of donor details.
- **REST Endpoint**: Exposes a REST endpoint to fetch donor details by name.

## Endpoint:
- **GET /getDoner/{name}**: Retrieves doner information by name from the in-memory list.

## Usage:
1. Clone the repository.
2. Build and run the project using your preferred IDE or command-line tools.
3. Access the provided endpoint using a web browser or API testing tool.

## Example Usage:
- **Request**: `GET /getDoner/bomber`
- **Response**: 
{
"name": "bomber",
"price": 9.9,
"weight": 400
}
