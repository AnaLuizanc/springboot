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

### Prerequisites

- Java 25
- MySQL 8.0+ or Docker
- Gradle

### Setup

1. **Clone the repository**

2. **Configure environment variables**
   
   Copy the `.env.example` file to `.env`:
   ```bash
   cp .env.example .env
   ```
   
   Then edit the `.env` file with your database credentials:
   ```properties
   DB_URL=jdbc:mysql://localhost:3306/animes
   DB_USERNAME=your_mysql_username
   DB_PASSWORD=your_mysql_password
   MYSQL_ROOT_PASSWORD=your_root_password
   MYSQL_DATABASE=animes
   ```

3. **Start the database**

   **Option A: Using Docker (Recommended)**
   ```bash
   docker-compose up -d
   ```
   This will start a MySQL container with the credentials from your `.env` file.

   **Option B: Using existing MySQL installation**
   ```sql
   CREATE DATABASE animes;
   ```

4. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

The API will be available at `http://localhost:8080`

### Important Security Notes

- **Never commit the `.env` file to version control**
- The `.env` file contains sensitive credentials and is already in `.gitignore`
- Use `.env.example` as a template for your local `.env` file
- Each developer should create their own `.env` file locally
- The `docker-compose.yml` also uses environment variables from `.env`

