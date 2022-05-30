Sample Android Weather app
=============================

This is a sample weatherapp thats shows the selected locations weather 

## Building the Sample App

First, clone the repo:

`git clone 'https://github.com/binokbenny77/WeatherApp.git'



### Android Studio (Recommended)

(These instructions were tested with Android Studio version 2.2.2, 2.2.3, 2.3, and 2.3.2)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'


## Libraries and tools 🛠


<li><a href="https://github.com/ReactiveX/RxJava">RxJava</a></li>
<li><a href="https://github.com/ReactiveX/RxAndroid">RxAndroid</a></li>
<li><a href="https://github.com/ReactiveX/RxKotlin">RxKotlin</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://github.com/square/okhttp">OkHttp</a></li>
<li><a href="https://github.com/square/moshi">Moshi</a></li>
<li><a href="https://material.io/develop/android/docs/getting-started/">Material Design</a></li>
<li><a href="https://github.com/lopspower/RxAnimation">RxAnimation</a></li>
<li><a href="https://github.com/JakeWharton/ThreeTenABP">ThreeTenABP</a></li>
<li><a href="https://github.com/algolia/algoliasearch-client-android">Algolia Search API Client for Android</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/navigation/">Navigation</a></li>
<li><a href="https://developer.android.com/training/data-storage/shared-preferences">Shared Preferences</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a></li>
<li><a href="https://github.com/facebook/stetho">Stetho</a></li>
<li><a href="https://github.com/square/picasso">Picasso</a></li>
<li><a href="https://developer.android.com/reference/androidx/lifecycle/Transformations">Transformations</a></li>
<li><a href="https://developer.android.com/topic/libraries/data-binding">Data Binding</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/room">RoomDB</a></li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a></li>

## Testing 
<li><a href="https://github.com/mockk/mockk">Mockk</a></li>
<li><a href="https://github.com/google/truth">Truth</a></li>


## Architecture
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

## Changing Build envoirnment
For changing the build envoirnment goto Constant.kt file and set 
    <li>private val defaultEnvironment = DEV for development</li>
      <li> private val defaultEnvironment = PROD for production</li>

## Inside application
  <li>Splash screen</li>
    <li>Home screen for showing the weather for the current location</li>
    <li>Search screen for searching the location and click on *(star) for adding it into the davorite list</li>
