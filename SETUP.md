# Starter Template Setup Guide

Follow these steps immediately after generating a new project from this template.

---

## 1. Rename Packages

Rename the **base package** and the **application package**.

Current template package:

```text
com.template.compose_hilt_starter
```

Rename it to your new package structure, for example:

```text
com.myPackage.myNewApp
```

Make sure both:

* `template` → `myPackage`
* `compose_hilt_starter` → `myNewApp`

are renamed using **Refactor → Rename → Rename Package**.

---

## 2. Update Gradle Configuration

Open:

```text
app/build.gradle.kts
```

Update both `namespace` and `applicationId`:

```kotlin
android {
    namespace = "com.myPackage.myNewApp"

    defaultConfig {
        applicationId = "com.myPackage.myNewApp"

        // ...
    }
}
```

---

## 3. Rename the Application Class

Rename the template `Application` class.

For example:

```text
ComposeHiltStarterApplication
```

→

```text
MyApplication
```

Make sure the class still contains `@HiltAndroidApp`.

Then open:

```text
app/src/main/AndroidManifest.xml
```

Verify that `android:name` points to the renamed `Application` class:

```xml
<application
    android:name=".MyApplication"
    android:label="@string/app_name"
    android:theme="@style/Theme.TemplateHiltStarter">

    <!-- ... -->

</application>
```

---

## 4. Update Gradle Project Name

Open:

```text
settings.gradle.kts
```

Find:

```kotlin
rootProject.name = "compose_hilt_starter"
```

Change it to your new project name:

```kotlin
rootProject.name = "myNewApp"
```

---

## 5. Update Application Name

Open:

```text
app/src/main/res/values/strings.xml
```

Update:

```xml
<resources>
    <string name="app_name">My New App</string>
</resources>
```

Replace `My New App` with the actual application name.

---

## 6. Verify and Clean the Project

Before starting development:

* [ ] Verify the base package was renamed.
* [ ] Verify the application package was renamed.
* [ ] Verify `namespace` was updated.
* [ ] Verify `applicationId` was updated.
* [ ] Verify the `Application` class was renamed.
* [ ] Verify `AndroidManifest.xml` references the renamed `Application` class.
* [ ] Verify `rootProject.name` in `settings.gradle.kts`.
* [ ] Verify `app_name` in `strings.xml`.
* [ ] Search the project for old template names and rename any remaining references.

Then run:

```text
File → Sync Project with Gradle Files
```

```text
Build → Clean Project
```

```text
Build → Rebuild Project
```

Finally, run the application and make sure everything builds and launches successfully.

---

## 7. Remove Setup File

After completing the setup successfully, delete:

```text
SETUP.md
```

The project is now ready for development.
