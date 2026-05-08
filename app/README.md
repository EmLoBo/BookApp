# 📚 Book App

A small Android application that browses classic public-domain books from the [Gutendex API](https://gutendex.com/), built as a showcase of modern Android architecture and tooling.

---

## ✨ Features

- Browse a list of classic books from Project Gutenberg
- View detailed information per book (title, author, life dates, subject)
- Material Design 3 theming with dynamic color
- Localized error messages with retry
- Offline-aware: surfaces clear errors on network loss

## 🏛️ Architecture

The project follows **Clean Architecture** with a strict three-layer split:

```
┌────────────────────────────────────────────────┐
│              Presentation Layer                │
│ Compose UI · ViewModels · UI State · Navigation│
└──────────────────────┬─────────────────────────┘
                       │ depends on
                       ▼
┌────────────────────────────────────────────────┐
│                Domain Layer                    │
│  Models · Repository interfaces · Use Cases    │
│  Resource<T> · DataError taxonomy              │
└──────────────────────┬─────────────────────────┘
                       │ implemented by
                       ▼
┌────────────────────────────────────────────────┐
│                  Data Layer                    │
│ Retrofit API · DTOs · Mappers · Repository Impl│
└────────────────────────────────────────────────┘
```

**Dependency rule:** the domain layer knows nothing about Android, Retrofit, or Compose. Everything points inward.


## 🛠️ Tech Stack

| Layer | Library |
|---|---|
| **Language** | Kotlin 1.9.10 (JDK 17 bytecode, JDK 21 toolchain) |
| **UI** | Jetpack Compose · Material 3 · Compose BOM 2023.10.01 |
| **Async** | Coroutines · Flow · StateFlow |
| **Navigation** | Navigation Compose |
| **DI** | Hilt 2.51.1 (KSP-based annotation processing) |
| **Networking** | Retrofit 2.11.0 · OkHttp 4.12.0 |
| **JSON** | kotlinx-serialization (compile-time codegen, not reflection) |
| **Build** | AGP 8.2.2 · Gradle 8.13 · `libs.versions.toml` version catalog |

---

## 🌐 API

The app consumes the public [Gutendex API](https://gutendex.com/) — a JSON wrapper around Project Gutenberg's catalog of free public-domain books. No authentication required.

Endpoints used:

| Method | Path | Purpose |
|---|---|---|
| `GET` | `/books` | Paginated catalog (32 books per page) |
| `GET` | `/books/{id}` | Single book by id |

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or newer
- **JDK 17 or 21** (Android Studio's bundled JBR works out of the box)
- A device or emulator running **Android 7.0 (API 24)** or higher

### Run locally

```bash
git clone https://github.com/<your-username>/BookApp.git
cd BookApp
./gradlew installDebug
```

Or open the project in Android Studio and click ▶️ Run.

---

## 📚 Learning Goals

This project is built deliberately as a **portfolio piece for Android job applications**. It emphasizes:

- Clean Architecture boundaries that hold up under review
- Modern Kotlin idioms (sealed interfaces, typed errors, StateFlow)
- Up-to-date tooling (KSP over kapt, version catalog, AGP 8.x)
- Single-source-of-truth state and unidirectional data flow
- Compile-time safety for error handling

---

## 🙏 Acknowledgments

- [Project Gutenberg](https://www.gutenberg.org/) — the public-domain library that makes this possible
- [Gutendex](https://gutendex.com/) — the JSON API maintained by [@garethbjohnson](https://github.com/garethbjohnson)

---

## 📄 License

This project is for educational and portfolio purposes. Project Gutenberg books are public domain.