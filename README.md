# Bonial Coding Challenge - Brochure App

This project is an Android application that displays brochures using a modern, multi-module Clean Architecture approach. It demonstrates best practices in Android development, including Jetpack Compose for UI, Koin for dependency injection, and a robust networking layer.

## Project Structure

The project follows **Clean Architecture** principles and is divided into several modules (app, data, domain, network, core) to ensure separation of concerns, scalability, and testability.

### Modules

- **`:app`**: The presentation layer. It contains UI components built with **Jetpack Compose**, ViewModels, and Android-specific implementations. It depends on all other modules.
- **`:domain`**: The core business logic. It contains **Use Cases**, **Domain Models**, and **Repository Interfaces**. This module is pure Kotlin/Java and has no dependencies on the Android framework or other modules (except `:core` for common utilities).
- **`:data`**: The data layer implementation. it implements the repository interfaces defined in the `:domain` module. It manages data from various sources (Network and Local).
- **`:network`**: A dedicated module for networking. It handles **Retrofit** configuration, OkHttp interceptors (including authentication and logging), and API service definitions.
- **`:core`**: Contains shared components, utilities, and helper classes used across multiple modules, such as `SharedPrefsManager` for local storage.

## Architecture & Interaction

1.  **UI (`:app`)** calls a **Use Case (`:domain`)**.
2.  The **Use Case** requests data from a **Repository Interface (`:domain`)**.
3.  The **Repository Implementation (`:data`)** fetches data from the **API Service (`:network`)** or **Local Storage (`:core`)**.
4.  Data is mapped from **DTOs** (Data Transfer Objects) to **Domain Models** as it flows back to the UI.
5.  **State Management**: The UI observes changes in the data through `StateFlow` or `Flow` emitted by the Use Cases, wrapped in a `Request` or `UiState` sealed class to handle Loading, Success, and Error states.

## Technologies Used

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Asynchronous Programming**: Kotlin Coroutines & Flow
- **Dependency Injection**: Koin
- **Networking**: Retrofit & OkHttp
- **Serialization**: Gson
- **Image Loading**: Coil
- **Testing**:
    - **Unit Testing**: JUnit 4, Mockito-Kotlin, Google Truth, Turbine
    - **UI Testing**: Jetpack Compose UI Test Framework

## Testing

The project has a comprehensive suite of unit and UI tests.

### Unit Tests
Unit tests are in place for critical components, including:
- **`SharedPrefsManager`**: Verifies local storage logic.
- **`safeApiCall`**: Ensures correct `Flow` emissions for network states.
- **`ContentWrapperDeserializer`**: Confirms custom JSON parsing logic.
- **`BrochuresViewModel`**: Tests business logic, including filtering by distance and content type.

To run all unit tests, execute:
```bash
./gradlew test
```

### UI Tests (Espresso & Compose)
UI tests are implemented to verify the visual components and their behavior.
- **`BrochureScreenTest`**: Ensures the main screen launches and successfully displays the brochure grid after data is loaded.
- **`BrochuresGridTest`**: Contains isolated tests for the grid's UI logic:
    - Verifies that "premium" brochures span the full grid width while "simple" brochures occupy a single column.
    - Confirms that a placeholder image is displayed when a brochure image fails to load.

To run the instrumentation tests, execute:
```bash
./gradlew connectedAndroidTest
```

## Build Variants

The project supports multiple environments through Gradle build types:

- **`debug`**: Used for daily development with staging endpoints and logging enabled.
- **`qa`**: Dedicated environment for quality assurance testing.
- **`staging`**: Pre-production environment.
- **`release`**: Production-ready build with obfuscation and optimizations.

**Note**: The app uses `.properties` files (e.g., `staging.properties`, `qa.properties`) to manage environment-specific variables like `BASE_URL`.

## Future Updates

Several enhancements are planned to further modernize the codebase:

1.  **Migrate to Hilt for Dependency Injection**: Replace Koin with Hilt. While Koin is effective, Hilt offers better integration with the Jetpack ecosystem, improved tooling, and compile-time safety, reducing the chance of runtime errors. This was deferred due to initial time constraints.

2.  **Replace SharedPreferences with Jetpack DataStore**: The custom `SharedPrefsManager` will be migrated to Jetpack's **Preferences DataStore**. This provides a more robust and modern solution for simple key-value storage with the benefits of an asynchronous, transactional API using Kotlin Coroutines and Flow.
