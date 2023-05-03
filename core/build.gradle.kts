plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.core"
    compileSdk = ConfigData.sdk

    defaultConfig {
        minSdk = ConfigData.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Versions.kotlinJvmTarget
    }
}

dependencies {
    // android libs
    api(Dependencies.appCompat)
    api(Dependencies.material)
    api(Dependencies.lifeCycleJava)

    // kotlin
    api(Dependencies.KotlinCore)
    api(Dependencies.KotlinStdlib)
    api(Dependencies.KotlinCoroutinesCore)
    api(Dependencies.KotlinCoroutinesAndroid)

    // retrofit
    api(Dependencies.RetrofitOkhttp)
    api(Dependencies.RetrofitCore)
    api(Dependencies.RetrofitLoggingInterceptor)
    api(Dependencies.RetrofitConverterScalars)
    api(Dependencies.RetrofitConverterGson)

    // glide
    api(Dependencies.GlideCore)
    kapt(Dependencies.GlideCompiler)

    // unit testing
    api(Dependencies.jUnitAndroidx)

    // timber
    api(Dependencies.Timber)
}