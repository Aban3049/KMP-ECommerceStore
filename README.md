E-Commerce Store Model with Kotlin Multiplatform

This project demonstrates how to build an e-commerce store model using Kotlin Multiplatform. The application fetches data from an API using Ktor and is compatible with macOS, Windows, iOS, Android, and web platforms.
Features

    Fetch product data from the API.
    Display product listings.
    Support multiple platforms.

Prerequisites

    Kotlin Multiplatform plugin for Android Studio.
    Enable experimental Multiplatform IDE features in Android Studio settings1.

Getting Started

    Clone this repository.
    Open the project in Android Studio.
    Set up your shared module for cross-platform code.
    Configure build scripts in gradle/libs.versions.toml:
Contributing

Feel free to contribute to this project by opening issues or pull requests.
License

This project is licensed under the MIT License.

This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
