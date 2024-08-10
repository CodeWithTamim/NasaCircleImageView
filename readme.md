# Thanks For Using Nasa Circle Image View Library
## This library is similar to the popular CircleImageView library but it is more light weight `7.69 KB` and  efficient.
## Follow the documentation below to know how to use the library

### Step 0: Add this to your project gradle
#### `settings.gradle`
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        ...
        //add jitpack
        maven { url 'https://jitpack.io' }
    }
}
```
#### `settings.gradle.kts`
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        ...
        // add jitpack
        maven(url = "https://jitpack.io")
    }
}
```
#### `build.gradle` app level module
```groovy
dependencies 
{
  implementation 'com.github.CodeWithTamim:NasaCircleImageView:1.0.0'
}
```
#### `build.gradle.kts` app level module
```groovy
dependencies 
{
implementation("com.github.CodeWithTamim:NasaCircleImageView:1.0.0")
}
```

### If your min sdk is not 21 or different then add this to the `AndroidManifest.xml`

```xml

<uses-sdk android:minSdkVersion="your_min_sdk" tools:overrideLibrary="com.nasahacker.downloader" />
```

### Step 1 : Add the view to your layout

```xml
 <com.nasahacker.library.NasaCircleImageView
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:src="@color/white"/>
 ```
### You can also add a border, use xml attributes or set from code
#### Using xml attributes
```xml
 app:borderColor="#ECEC" 
 app:borderWidth="10dp"
```

#### Java

```java
// Set border color using a direct color value
circleImageView.setBorderColor(Color.RED);
// Set border color using a color resource ID
circleImageView.setBorderColorResource(R.color.border_color);
// Set border width
circleImageView.setBorderWidth(10f); // 10f for 10 pixels
```
#### Kotlin

```Kotlin
// Set border color using a direct color value
circleImageView.setBorderColor(Color.RED)
// Set border color using a color resource ID
circleImageView.setBorderColorResource(R.color.border_color)
// Set border width
circleImageView.setBorderWidth(10f) // 10f for 10 pixels
```

### Thanks for reading the documentation, I'm `Tamim`, I made this library and I'm the one who was helping you throughout the documentation :)
### If the library helped you out then please give it a start and share with your dev friends ! The project is open for contrubution so if you have any fixes or new feature enhancement then just fork it then make your changes create a new brach and then just hit a pull request.

## Thank you guys for your love and support
## If you have any queries or need help then just open a issue or  <a href="mailto:tamimh.dev@gmail.com">mail me</a> 
## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.


 



