object BuildPlugins{
    const val android = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.gradle}"
}

object Deps {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val jUnit = "junit:junit:${Versions.jUnit}"

    const val KotlinCore = "androidx.core:core-ktx:${Versions.Kotlin.core}"
    const val KotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.gradle}"
    const val KotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    const val KotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"

    const val RoomRuntime = "android.arch.persistence.room:runtime:${Versions.room}"
    const val RoomCompiler = "android.arch.persistence.room:compiler:${Versions.room}"

    const val RetrofitOkhttp = "com.squareup.okhttp3:okhttp:${Versions.Retrofit.okhttp}"
    const val RetrofitCore = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.core}"
    const val RetrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.core}"
    const val RetrofitLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Retrofit.loggingInterceptor}"
    const val RetrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.Retrofit.converterScalars}"

    const val GlideCore = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val GlideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}