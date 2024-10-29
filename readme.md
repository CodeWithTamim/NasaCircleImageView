
# Nasa Circle Image View Library
[![](https://jitpack.io/v/CodeWithTamim/NasaCircleImageView.svg)](https://jitpack.io/#CodeWithTamim/NasaCircleImageView)

**Nasa Circle Image View** is a lightweight (~7.69 KB) and efficient library similar to the popular CircleImageView library. It provides a simple way to create circular image views with customizable borders.

## 📚 Documentation

Follow the steps below to integrate **Nasa Circle Image View** into your Android project.

---

### Step 0: Add JitPack to Your Project's Gradle

#### `settings.gradle` (Groovy)
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // other repositories
        maven { url 'https://jitpack.io' }  // add JitPack repository
    }
}
```

#### `settings.gradle.kts` (Kotlin)
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // other repositories
        maven(url = "https://jitpack.io")  // add JitPack repository
    }
}
```

---

### Step 1: Add the Nasa Circle Image View Dependency

#### `build.gradle` (App-level) (Groovy)
```groovy
dependencies {
    implementation 'com.github.CodeWithTamim:NasaCircleImageView:1.0.4'
}
```

#### `build.gradle.kts` (App-level) (Kotlin)
```kotlin
dependencies {
    implementation("com.github.CodeWithTamim:NasaCircleImageView:1.0.4")
}
```

---

### Step 2: Update AndroidManifest for Minimum SDK Version

If your minimum SDK version is not 21 or differs, add the following to your `AndroidManifest.xml`:

```xml
<uses-sdk android:minSdkVersion="your_min_sdk" tools:overrideLibrary="com.nasahacker.downloader" />
```

---

### Step 3: Add the Circle Image View to Your Layout

You can add the circle image view to your XML layout like this:

```xml
<com.nasahacker.library.NasaCircleImageView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:src="@color/white"/>
```

---

### Step 4: Customize Borders (Optional)

You can customize the border color and width using XML attributes or programmatically.

#### Using XML Attributes:
```xml
app:borderColor="#ECEC" 
app:borderWidth="10dp"
```

#### Java Example:
```java
// Set border color using a direct color value
circleImageView.setBorderColor(Color.RED);
// Set border color using a color resource ID
circleImageView.setBorderColorResource(R.color.border_color);
// Set border width
circleImageView.setBorderWidth(10f); // 10f for 10 pixels
```

#### Kotlin Example:
```kotlin
// Set border color using a direct color value
circleImageView.setBorderColor(Color.RED)
// Set border color using a color resource ID
circleImageView.setBorderColorResource(R.color.border_color)
// Set border width
circleImageView.setBorderWidth(10f) // 10f for 10 pixels
```

---

## Contributions and Support

If **Nasa Circle Image View** helped you, consider giving it a ⭐️ and sharing it with fellow developers. Contributions are welcome! Feel free to fork the project, make your changes, and submit a pull request.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for more details.

---

Thank you for using **Nasa Circle Image View**! I’m **Tamim**, the creator of this library. If you have any questions or need further assistance, feel free to [open an issue](https://github.com/CodeWithTamim/NasaCircleImageView/issues) or [email me](mailto:tamimh.dev@gmail.com).
