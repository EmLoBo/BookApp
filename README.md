# BookApp

> A small Jetpack Compose client for the [Gutendex](https://gutendex.com/) API, built to work
> through modern Android architecture end to end: Compose, Clean Architecture layering,
> a typed error model and unit-tested use cases.

**Stack:** Kotlin · Jetpack Compose · Material 3 · Hilt (KSP) · Retrofit · kotlinx-serialization · Coroutines & Flow · JUnit + MockK

---

## Screenshots

| Book list | Book detail | Error state |
|---|---|---|
| <img src="docs/screenshots/list.png" width="220"/> | <img src="docs/screenshots/detail.png" width="220"/> | <img src="docs/screenshots/error.png" width="220"/> |

<!-- Pokaż też error state — to sygnał, że myślisz o ścieżkach niehappy-path. -->

---

## Architecture

```
com.emlobo.bookapp/
├── data/
│   ├── remote/       GutendexApi, DTOs
│   ├── mapper/       BookMapper — DTO -> domain
│   ├── repository/   BookRepositoryImpl
│   └── util/         ErrorMapping — exceptions -> DataError
├── di/               AppModule (Hilt)
├── domain/
│   ├── model/        Book, Author
│   ├── repository/   BookRepository (interface)
│   ├── usecase/      GetBooksUseCase, GetBookByIdUseCase
│   └── util/         Resource<T>, DataError
└── presentation/
    ├── book_list/    BookListScreen + BookListViewModel
    ├── book_detail/  BookDetailScreen + BookDetailViewModel
    ├── navigation/
    ├── theme/
    └── util/         ErrorUiMapping — DataError -> user-facing text
```

The domain layer has no Android or Retrofit imports — it depends on nothing but Kotlin.

## Engineering decisions

**Errors are typed, not strings.**
The data layer converts exceptions into a `DataError` sealed hierarchy (`ErrorMapping`).
Use cases return `Resource<T>`. Only at the very edge, in `ErrorUiMapping`, does an error
become text a person reads. This means the domain layer never knows about string resources
and error handling is exhaustive at compile time via `when`.

**`suspend Resource<T>` instead of `Flow<Result<T>>`.**
The repository originally exposed `Flow<Result<T>>`. For one-shot HTTP reads that was
ceremony without benefit — a flow that emits exactly once, plus a nested wrapper type.
Switching to a suspend function returning `Resource<T>` removed a layer of nesting from
every ViewModel. Flow stays where there is a genuine stream.

**KSP instead of kapt for Hilt.**
kapt runs a full Java stub-generation pass. KSP reads Kotlin directly and cut incremental
build times noticeably on this project.

**kotlinx-serialization instead of Gson.**
Compile-time serializer generation, no reflection, and `@SerialName` makes the API contract
explicit in the DTOs — which is exactly what `JsonSerializationTest` locks down.

**Version catalog (`libs.versions.toml`).**
Single place for dependency versions.

## Testing

```bash
./gradlew test
```

- `GetBooksUseCaseTest` — use case behaviour against a mocked repository, including
  the error path.
- `JsonSerializationTest` — guards the API contract: if Gutendex changes a field name,
  this fails in CI rather than silently producing empty models in production.

## Build & run

```bash
git clone https://github.com/EmLoBo/BookApp.git
```

No API key required — Gutendex is public. Open in Android Studio and run `app`.
Min SDK `<24>` · Target SDK `<35>`.

## Known limitations

- No pagination — the app loads the first Gutendex page only.
- No local caching; every launch hits the network.
- No UI tests; coverage is at the use-case and serialization level.

---

**Author:** Maja Łobodziec — Android Developer, Wrocław
[LinkedIn](https://linkedin.com/in/maja-lobodziec) · maja.lobodziec@gmail.com
