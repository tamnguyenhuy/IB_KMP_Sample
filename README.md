This is a Kotlin Multiplatform project targeting Android, iOS.

- `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

- `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.
- Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

# Kotlin Multiplatform Compose Sample

Note: Replace with your own GitHub API key to increase rate limit in `RemoteRepoBase.kt` file.

## Screenshots

### Android

| List User                                             | List User Error                                             | User Detail                                             |
| ----------------------------------------------------- | ----------------------------------------------------------- | ------------------------------------------------------- |
| <img src="docs/images/android_list.png" width="320"/> | <img src="docs/images/android_list_error.png" width="320"/> | <img src="docs/images/android_detail.png" width="320"/> |

### iOS

| List User                                         | List User Error                                         | User Detail                                         |
| ------------------------------------------------- | ------------------------------------------------------- | --------------------------------------------------- |
| <img src="docs/images/ios_list.png" width="320"/> | <img src="docs/images/ios_list_error.png" width="320"/> | <img src="docs/images/ios_detail.png" width="320"/> |

## Demo Videos

| Android                                            | iOS                                            |
| -------------------------------------------------- | ---------------------------------------------- |
| <img src="docs/gif/android_demo.gif" width="320"/> | <img src="docs/gif/ios_demo.gif" width="320"/> |

## Technical Dependencies

- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Composable Multiplatform](https://jb.gg/compose) for building common UI. It simplifies and accelerates UI development on Android, iOS and Desktop.
- [Koin](https://insert-koin.io/) for dependency injection.
- [Ktor & Kotlin Serialization](https://ktor.io/) for constructing the REST APIs and paging network data.
- [SqlDelight](https://sqldelight.github.io/sqldelight/2.0.2/) for local database.
- [Voyager](https://github.com/adrielcafe/voyager) for navigation and screen models.
- [Coil](https://coil-kt.github.io/coil/getting_started/) An image loading library for Android and Compose Multiplatform

### Common Structure

```
├── composeApp
│   ├── androidMain
│   ├── commonMain
│   │   ├── kotlin/com/initium/assignment
│   │   │   ├── data                                         <––– Data Layer
│   │   │   │   ├── di                                       <––– Koin DI for data layer
│   │   │   │   ├── local                                    <––– Local data sources (SQLDelight)
│   │   │   │   ├── remote                                   <––– Remote data sources (Ktor APIs, DTOs)
│   │   │   │   └── repository                               <––– Repository implementations
│   │   │   ├── di                                           <––– Koin DI for whole app
│   │   │   ├── domain                                       <––– Domain Layer (Clean Architecture core)
│   │   │   │   ├── di                                       <––– Koin DI for domain layer
│   │   │   │   ├── model                                    <––– Domain models (User, ListDataStruct)
│   │   │   │   ├── repository                               <––– Repository interfaces (contracts)
│   │   │   │   └── usecase                                  <––– Use Cases (business logic)
│   │   │   └── ui                                           <––– Presentation Layer
│   │   │       ├── app                                      <––– Screens + MVI (ViewModel, State, Event)
│   │   │       ├── core                                     <––– Base ViewModel, MVI contracts, Logger
│   │   │       ├── di                                       <––– Koin DI for UI layer
│   │   │       ├── theme                                    <––– Material3 theme (Color, Theme)
│   │   │       └── uikit                                    <––– Reusable composable components
│   │   ├── composeResources                                 <––– Resources (images, strings)
│   │   └── sqldelight/com/initium/assignment                <––– SQLDelight schema
│   └── iosMain
└── ...
```
