# 📚 Book App

Android application that displays a list of books from the Gutendex API with detailed information about each book.

## 📱 Features

- Browse a list of classic books
- View book details including:
  - Title
  - Author name
  - Author birth and death years
  - Book subject
- Clean and intuitive Material Design 3 UI

## 🏗️ Architecture

The app follows **Clean Architecture** principles with three main layers:

- **Presentation Layer**: Jetpack Compose UI + ViewModels
- **Domain Layer**: Business logic and Use Cases
- **Data Layer**: API integration and Repository

## 🛠️ Tech Stack

- **Kotlin** - Programming language
- **Jetpack Compose** - Modern UI toolkit
- **Retrofit** - REST API client
- **Coroutines & Flow** - Asynchronous programming
- **Hilt/Dagger** - Dependency injection
- **Material Design 3** - UI components
- **Navigation Component** - Screen navigation
- **JUnit & MockK** - Unit testing

## 🌐 API

This app uses the [Gutendex API](https://gutendex.com/) to fetch book data.

## 🧪 Testing

The project includes:
- Unit tests for Use Cases
- JSON serialization validation tests



This project is for educational purposes.

