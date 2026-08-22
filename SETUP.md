# Starter Template Setup Guide

Follow this guide step by step immediately after generating a new project from this template.

---

## 1. Package Name Refactoring

The starter template uses the following package structure:

```text
com.template.compose_hilt_starter
```

When creating a new project, make sure to rename **both** the base package and the application package. Do not only rename `compose_hilt_starter`.

For example:

```text
com.template.compose_hilt_starter
```

should become:

```text
com.mypackage.mynewapp
```

---

### A. Prepare the Package Structure

1. Open the project in **Android Studio**.

2. In the **Project** window, open it with:

    * `Alt + 1` on Windows/Linux
    * `Cmd + 1` on macOS

3. Click the settings icon (**⚙️**) and disable:

    * **Compact Middle Packages**, or
    * **Hide Empty Middle Packages**

   Depending on your Android Studio version, the exact option name may vary.

4. Navigate to:

```text
app/src/main/java/com/template/compose_hilt_starter
```

You should now see the individual package directories:

```text
com
└── template
    └── compose_hilt_starter
```

---

### B. Rename the Base Package

The `template` package is part of the starter template and should also be renamed for your new project.

For example:

```text
com.template.compose_hilt_starter
```

can become:

```text
com.mypackage.compose_hilt_starter
```

To rename it:

1. Right-click the `template` package.
2. Select:

```text
Refactor → Rename...
```

3. Alternatively, use:

```text
Shift + F6
```

4. Select **Rename Package**.
5. Enter your new base package name.

Example:

```text
mypackage
```

6. Click **Refactor**, then confirm with **Do Refactor**.

Your package structure should now be:

```text
com.mypackage.compose_hilt_starter
```

> **Important:** Do not leave `template` in the package name unless you intentionally want to use it as part of your application's package.

---

### C. Rename the Application Package

After renaming the base package, rename the application-specific package.

For example:

```text
com.mypackage.compose_hilt_starter
```

can become:

```text
com.mypackage.mynewapp
```

1. Right-click the `compose_hilt_starter` package.
2. Select:

```text
Refactor → Rename...
```

3. Alternatively, use:

```text
Shift + F6
```

4. Select **Rename Package**.
5. Enter your new application package name.

Example:

```text
mynewapp
```

6. Click **Refactor**, then confirm with **Do Refactor**.

Your final package structure should look like this:

```text
com.mypackage.mynewapp
```

Make sure the old package names:

```text
template
compose_hilt_starter
```

are no longer present unless they are intentionally kept.

---

## 2. Configuration and Manifest Updates

### A. Update `app/build.gradle.kts`

Open:

```text
app/build.gradle.kts
```

Update both the `namespace` and `applicationId` values to match your new package name.

Example:

```kotlin
android {
    namespace = "com.mypackage.mynewapp"

    defaultConfig {
        applicationId = "com.mypackage.mynewapp"

        // ...
    }
}
```

Make sure both values match your final package structure.

For example, if your package is:

```text
com.mypackage.mynewapp
```

then both values should be:

```text
com.mypackage.mynewapp
```

---

### B. Update `AndroidManifest.xml`

Open:

```text
app/src/main/AndroidManifest.xml
```

Verify that the `android:name` property correctly references your `@HiltAndroidApp` application class.

Example:

```xml
<application
    android:name=".ComposeHiltStarterApplication"
    android:label="@string/app_name"
    android:theme="@style/Theme.TemplateHiltStarter">

    <!-- ... -->

</application>
```

If you rename the `Application` class, make sure to update `android:name` accordingly.

For example, if you rename `ComposeHiltStarterApplication` to `MyNewAppApplication`:

```xml
<application
    android:name=".MyNewAppApplication"
    android:label="@string/app_name"
    android:theme="@style/Theme.TemplateHiltStarter">

    <!-- ... -->

</application>
```

---

## 3. String and Resource Adjustments

### A. Change the Application Name

Open:

```text
app/src/main/res/values/strings.xml
```

Update the application display name:

```xml
<resources>
    <string name="app_name">My New App</string>
</resources>
```

Replace `My New App` with your actual application name.

---

### B. Adjust Theme Colors (Optional)

If necessary, update the application's color palette and theme configuration.

Check the following files:

```text
app/src/main/java/.../ui/theme/Color.kt
```

and:

```text
app/src/main/java/.../ui/theme/Theme.kt
```

You can customize:

* Primary colors
* Secondary colors
* Background colors
* Surface colors
* Light theme colors
* Dark theme colors

---

## 4. Environment Clean-Up

After completing all package and configuration changes, perform the following steps.

### Step 1: Sync Gradle

In Android Studio, click:

```text
File → Sync Project with Gradle Files
```

Wait until the Gradle synchronization is completed successfully.

---

### Step 2: Clean the Project

Run:

```text
Build → Clean Project
```

---

### Step 3: Rebuild the Project

Then run:

```text
Build → Rebuild Project
```

Make sure the project builds successfully without any errors.

---

## 5. Final Clean-Up

Before creating your initial commit:

1. Verify that the application runs correctly.
2. Confirm that the `template` package has been renamed.
3. Confirm that the `compose_hilt_starter` package has been renamed if needed.
4. Confirm that the final package name is updated everywhere.
5. Confirm that `namespace` and `applicationId` match the final package name.
6. Verify that the application name is correct.
7. Search the project for old template-related names and replace anything that should not remain:

```text
template
compose_hilt_starter
ComposeHiltStarterApplication
```

8. Delete this file:

```text
SETUP.md
```

After that, you are ready to start developing your application.

---

## Quick Checklist

* [ ] Disable **Compact Middle Packages** or **Hide Empty Middle Packages**
* [ ] Rename the `template` base package
* [ ] Rename the `compose_hilt_starter` application package
* [ ] Verify the final package structure
* [ ] Update `namespace`
* [ ] Update `applicationId`
* [ ] Verify `AndroidManifest.xml`
* [ ] Verify or rename the `Application` class
* [ ] Update the application name in `strings.xml`
* [ ] Update theme colors if needed
* [ ] Search for remaining old template names
* [ ] Sync Gradle
* [ ] Clean the project
* [ ] Rebuild the project
* [ ] Run the application successfully
* [ ] Delete `SETUP.md`
* [ ] Create the initial commit

---

**The starter template is now ready to use.**
