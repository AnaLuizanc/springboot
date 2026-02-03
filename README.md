# Spring Boot Anime API

A demo REST API built with Spring Boot for managing anime information. This project provides a simple CRUD (Create, Read, Update, Delete) interface for anime entries.

## Features

- List all animes
- Get anime by ID
- Create new anime entries
- Update existing animes
- Delete animes

## Technologies

- Spring Boot 4.0.2
- Java 25
- Lombok
- Gradle (Kotlin DSL)

## API Endpoints

- `GET /animes` - List all animes
- `GET /animes/{id}` - Get anime by ID
- `POST /animes` - Create new anime
- `PUT /animes/{id}` - Update existing anime
- `DELETE /animes/{id}` - Delete anime

## Getting Started

Run the application:
```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`
