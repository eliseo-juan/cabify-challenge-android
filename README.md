# Cabify Mobile Challenge

This is a Technical Challenge for Cabify.
Product specifications are detailed here: [CHALLENGE.md](.github/CHALLENGE.md)

## Solution
<p float="left">
  <img src=".github/screenshot/Screenshot_20220513-215817.png" width="100" />
  <img src=".github/screenshot/Screenshot_20220513-215825.png" width="100" /> 
  <img src=".github/screenshot/Screenshot_20220513-215854.png" width="100" />
</p>

## Architecture

This 

### Modules

### MVI

### Compose

### Composable Architecture for Navigation

## Decisions for this Tech test:

## Libraries

I want to explain a bit about the libraries I used in this project and how:

### Dependency Inversion: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
Becoming the standard way of handling with coupling in Android. I previously used [Koin](https://insert-koin.io/) but Hilt is having more and more support from the community.

### Database: [JetPack DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
Yes, I know I've used a Preferences library as a BBDD, I would do it with [Room](https://developer.android.com/jetpack/androidx/releases/room), but I'm bored of using Room, so I have taken the opportunity of using JetPack DataStore as the first time.

### Async Flow: [Flow & Coroutines](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
The standard way of handling async calls and make a reactive app.

### Image Loader: [Coil](https://github.com/coil-kt/coil)
Designed in Kotlin for Kotlin with good support for Compose. I used to use [Glide](https://github.com/bumptech/glide) but have been switching to Coil.

### Serialization: [Gson](https://github.com/google/gson)
One of the most popular JSON libraries for Android. But probably is time to move to [Moshi](https://github.com/square/moshi). I did not use it for this test because I want to evaluate the differences in depth.

### Network: [Retrofit](https://github.com/square/retrofit)
One of the most used libraries for network calls, I feel very confident with this library, and I use it in all my projects.

### Functional Programming [Arrow](https://github.com/arrow-kt/arrow)
*I did not use this library* for this tech test because the benefits of functional programing are more noticeable in medium and long term projects. Arrow is one of my favorite libraries to make kotlin something more functional.

## Testing
