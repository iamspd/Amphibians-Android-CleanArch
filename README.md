# Amphibians-Android-CleanArch
Amphibians is a modern Android application built to showcase a robust, scalable, and testable architecture. The app fetches a list of amphibians from a network source and displays them in a list. This project serves as a demonstration of best practices in modern Android development using Kotlin, Jetpack Compose, and a Clean Architecture approach.

<img width="1408" height="2974" alt="Amphibians_error_screen" src="https://github.com/user-attachments/assets/4a02b4bc-d589-4e7b-b70d-684c68b43676" />
<img width="1408" height="2974" alt="Ampihbians_home_screen" src="https://github.com/user-attachments/assets/54549c8d-d70e-4aa7-a065-7cbd9a08d75d" />
<img width="1408" height="2974" alt="Amphibians_loading_screen" src="https://github.com/user-attachments/assets/04a05980-09cc-472a-ac17-2e90d0a92f99" />

### 🛠 Tech Stack & Libraries
This project is built with a 100% Kotlin-first approach and leverages the latest in Android development:

- UI: Jetpack Compose for a declarative, modern UI toolkit.

- Architecture: MVVM + Clean Architecture (UI, Domain, Data layers).

- Asynchronous Programming: Kotlin Coroutines & Flow for managing background threads and data streams.

- Dependency Injection: Manual DI container for clarity and foundational understanding.

- Networking: Retrofit for type-safe HTTP calls & Moshi for efficient JSON parsing.

- Image Loading: Coil for optimized image loading in Compose.

- Testing: JUnit 4, Turbine for testing Kotlin Flows, and custom fake dependencies for robust unit tests.

### 🏛️ Architecture
The application follows the principles of Clean Architecture, separating the code into three distinct layers. This separation of concerns makes the app highly scalable, maintainable, and testable.

- UI (Presentation) Layer: Contains Composables, ViewModels, and UI-specific state holders (HomeUiState). This layer is responsible for displaying the data and handling user interactions. It is completely unaware of where the data comes from.

- Domain Layer: The core of the application. It contains the business logic (UseCases) and the core data models (Amphibian). This layer is pure Kotlin and has zero Android framework dependencies, making it highly reusable and testable.

- Data Layer: Responsible for providing data to the domain layer. It contains the Repository implementation, network data sources (Retrofit), and DTOs (Data Transfer Objects). It handles the logic for fetching data and mapping it from network models to domain models.

### 🚀 Setup & Running the Project
To run this project, you need Android Studio Hedgehog or later.

1. Clone the repository:

git clone https://github.com/YOUR_USERNAME/Amphibians-Android-CleanArch.git

2. Open in Android Studio:

- Open Android Studio.

- Select File -> Open and navigate to the cloned project directory.

3. Run the app:

- Let Gradle sync the dependencies.

- Select an emulator or a physical device.

- Click the "Run" button.

### ✅ Testing
The project includes a suite of unit tests to ensure the reliability and correctness of the business logic.

- ViewModel Tests: Verify the state management logic in the HomeViewModel, ensuring the UI state transitions correctly based on results from the domain layer.

- Repository Tests: Verify the data mapping and error handling logic in the AmphibianRepositoryImpl.

To run the tests, navigate to the app/src/test directory in Android Studio and run the test classes.
