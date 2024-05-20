plugins{
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.mytemplate"
    compileSdk = ConfigData.sdk

    defaultConfig {
        applicationId = "com.example.mytemplate"
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "SERVER_URL", ConfigData.baseUrlDev)
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "SERVER_URL", ConfigData.baseUrlProd)
        }
    }
    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            // Assigns this product flavor to the "version" flavor dimension.
            // If you are using only one dimension, this property is optional,
            // and the plugin automatically assigns all the module's flavors to
            // that dimension.
            dimension = "version"
            applicationIdSuffix = ".demo"
        }
        create("full") {
            dimension = "version"
        }
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Versions.kotlinJvmTarget
    }
    kapt{
        includeCompileClasspath = false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(project(":core"))

    // android libs
    implementation(Dependencies.androidSupport)

    // hilt
    implementation(Dependencies.Hilt)
    implementation(Dependencies.HiltActivity)
    implementation(Dependencies.HiltFragment)
    implementation(Dependencies.HiltViewModelCompiler)
    kapt(Dependencies.HiltCompiler)

    // room database
    implementation(Dependencies.RoomRuntime)
    kapt(Dependencies.RoomCompiler)

    // lifecycle
    implementation(Dependencies.LifecycleLiveData)
    implementation(Dependencies.LifecycleViewModel)

    // unit testing
    testImplementation(Dependencies.jUnitAndroidx)
    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.hiltTesting)
    androidTestImplementation(Dependencies.hiltTesting)
    kaptTest(Dependencies.hiltCompilerTesting)
}

kapt {
    correctErrorTypes = true
}
