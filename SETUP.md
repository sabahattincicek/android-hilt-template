# Starter Template Setup Guide

Follow these steps immediately after generating a new project from this template.

---

## 1. Rename Packages

Rename the **base package** and the **application package**.

Current template package:

```text
com.template.composehiltstarter
```

Rename it to your new package structure, for example:

```text
com.mycompany.mynewapp
```

Make sure both:

* `template` → `mycompany`
* `composehiltstarter` → `mynewapp`

are renamed using **Refactor → Rename → Rename Package**. Keep package names lowercase, without underscores.

---

## 2. Update Gradle Configuration

Open:

```text
app/build.gradle.kts
```

Update both `namespace` and `applicationId`:

```kotlin
android {
    namespace = "com.mycompany.mynewapp"

    defaultConfig {
        applicationId = "com.mycompany.mynewapp"

        // ...
    }
}
```

---

## 3. Update Gradle Project Name

Open:

```text
settings.gradle.kts
```

Find:

```kotlin
rootProject.name = "ComposeHiltStarter"
```

Change it to your new project name:

```kotlin
rootProject.name = "MyNewApp"
```

---

## 4. Update Application Name

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

## 5. Verify and Clean the Project

Before starting development:

* [ ] Verify the base package was renamed.
* [ ] Verify the application package was renamed.
* [ ] Verify `namespace` was updated.
* [ ] Verify `applicationId` was updated.
* [ ] Verify `rootProject.name` in `settings.gradle.kts`.
* [ ] Verify `app_name` in `strings.xml`.
* [ ] Search the project for `composehiltstarter` and `ComposeHiltStarter` and rename any remaining references.

The `App` class, `AppTheme` and `Theme.App` are intentionally generic and do not need to be renamed.

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

## 6. Remove Setup File

After completing the setup successfully, delete:

```text
SETUP.md
```

The project is now ready for development.
