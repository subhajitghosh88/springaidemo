# Spring AI Demo Application

## Overview
This is a **Spring Boot** application that demonstrates integration with Google's **Gemini AI API**. The application provides RESTful endpoints to generate **text** and **images** using AI models, showcasing the power of generative AI.

---

## Features
- **Text generation from prompts**
- **Image generation from text prompts**
- **Swagger/OpenAPI documentation**
- **Configuration-based API key management**

---

## Prerequisites
Before you start, make sure you have the following installed:
- **Java 21**
- **Spring Boot 3.4.4**
- A **valid Gemini API key** (you need to set this in `application.properties`)

---

## Project Structure
The project structure is organized as follows:
```properties
src/main/java/com/ai/springaidemo/
├── config/
│   ├── GeminiClientConfig.java - Configuration for Gemini client
│   └── OpenApiConfig.java - OpenAPI/Swagger configuration
├── controller/
│   └── GenAIController.java - REST controller for AI endpoints
├── service/
│   └── GeminiAIService.java - Service layer for Gemini AI operations
└── SpringAIDemoApplication.java - Main application class
```

## Dependencies
The main dependencies used in this project are:
- **Spring Boot Starter Web** - For building RESTful APIs
- **Google GenAI Client (0.1.0)** - Gemini API client for Java
- **SpringDoc OpenAPI** - For API documentation
- **Hibernate Validator** - For input validation
- **Lombok** - For reducing boilerplate code

## Building and Running the Application
### Clone the Repository
```bash
git clone https://github.com/your-repo/spring-ai-demo.git
cd spring-ai-demo
```
### Set API Key in application.properties
In the src/main/resources/application.properties file, set your Gemini API key and model name:
```properties
gemini.api.key=your_gemini_api_key
gemini.ai.model=your_gemini_model_name
```
### Build the Application
```bash
./gradlew build
```
### Run the Application
```bash
java -jar build/libs/springaidemo-0.0.1-SNAPSHOT.jar
```
The application will start on http://localhost:8080


## API Endpoints

### Text Generation
- **GET** `/api/gemini/generate-text?prompt=your_prompt`
- Returns generated text

### Image Generation
- **GET** `/api/gemini/generate-image?prompt=your_prompt`
- Returns generated PNG image

## Documentation
Access interactive API docs at:
- Swagger UI: `/swagger-ui.html`
- OpenAPI spec: `/v3/api-docs`

## Build & Run
```bash
./gradlew build
java -jar build/libs/springaidemo-0.0.1-SNAPSHOT.jar
```

## Testing the API
### Using Postman or Curl
You can test the API endpoints using Postman or curl. Here are examples of how to do that:
#### Text Generation
```bash
curl -X GET "http://localhost:8080/api/gemini/generate-text?prompt=Hello%20world"
```
#### Image Generation
```bash
curl -X GET "http://localhost:8080/api/gemini/generate-image?prompt=Beautiful%20landscape"
```

## Conclusion
The Spring AI Demo Application provides a simple but powerful demonstration of integrating Google Gemini AI API for generating both text and images. With the included Swagger UI and API documentation, it's easy to interact with the application and test the AI capabilities. Happy coding!