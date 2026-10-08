# Development Log

A running record of what has been done in this project, so a new session (human or AI) can catch up
without reading the whole codebase.

- **Newest entries go at the top**, directly under this header. Read downward to go back in time.
- Entry heading format: `## YYYY-MM-DD HH:mm — Short title`
- Say what changed, why, and which files or areas were touched.
- Do not rewrite or delete older entries.

---

## 2026-10-08 18:05 — Project created from ComposeHiltStarter template

Starting point of the project. What the template provides:

- **Build:** single `:app` module, AGP 9.2.1 with built-in Kotlin 2.4.21, KSP, version catalog in
  `gradle/libs.versions.toml`, compileSdk/targetSdk 37, minSdk 24.
- **UI:** Jetpack Compose with Material 3, `AppTheme` in `ui/theme/`, `MainActivity` showing a
  placeholder greeting.
- **DI:** Hilt is wired up: `App` (`@HiltAndroidApp`), `MainActivity` (`@AndroidEntryPoint`),
  `DispatcherModule` provides `DispatcherProvider`. `RepositoryModule` is an empty Hilt module.
- **Core utilities:** `Resource` (Loading/Success/Error wrapper), `UiText` (context-free UI text),
  `DispatcherProvider` (injectable coroutine dispatchers).
- **Logging:** Timber. The debug tree is planted in `App.onCreate` for debug builds only
  (`BuildConfig` generation is enabled for this).
- **Placeholders, still empty:** `BaseViewModel`, `Constants`, `Extensions`, `ErrorView`,
  `AppModule`, `DatabaseModule`, `NetworkModule`, and the `data/`, `domain/`,
  `presentation/features`, `presentation/navigation` packages.
- **Not included yet:** navigation, networking (Retrofit/OkHttp), and database (Room) dependencies.
- **Tests:** JUnit, AndroidX Test, Espresso, and Compose UI test dependencies with the default
  example tests.
