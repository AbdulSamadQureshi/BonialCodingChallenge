# Bonial Coding Challenge - Brochure App

This project is an Android application that displays brochures using a modern, multi-module Clean Architecture approach. It demonstrates best practices in Android development, including Jetpack Compose for UI, Koin for dependency injection, and a robust networking layer.

## Project Structure

The project follows **Clean Architecture** principles and is divided into several modules to ensure separation of concerns, scalability, and testability.

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
    - **Unit Testing**: JUnit 4, Mockito-Kotlin
    - **Assertions**: Google Truth
    - **Flow Testing**: Turbine
    - **UI Testing**: Compose UI Test

## Build Variants

The project supports multiple environments through Gradle build types:

- **`debug`**: Used for daily development with staging endpoints and logging enabled.
- **`qa`**: Dedicated environment for quality assurance testing.
- **`staging`**: Pre-production environment.
- **`release`**: Production-ready build with obfuscation and optimizations.

## Getting Started

1.  **Clone the repository**.
2.  **Gradle Sync**: Open the project in Android Studio and perform a Gradle sync.
3.  **Local Properties**: Ensure you have a `local.properties` file configured.
4.  **Environment Configuration**: The app uses `.properties` files (e.g., `staging.properties`, `qa.properties`) to manage environment-specific variables like `BASE_URL`.
5.  **Run the App**: Select the desired build variant (e.g., `debug`) and run it on an emulator or physical device.

## Testing

To run the unit tests across all modules, execute:
```bash
./gradlew test
```
To run tests for a specific module (e.g., `:domain`):
```bash
./gradlew :domain:test
```
