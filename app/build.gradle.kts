plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.dokka")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.imaan.store"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "com.imaan.store.HiltTestRunner"//ProjectConfig.instrumentedTestRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Documentation
tasks.dokkaHtml.configure {
    outputDirectory.set(file("../documentation/html"))
}

dependencies {

    implementation(Dependencies.coreKtx)
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    lifecycle()
    compose()
    navigation()
    hilt()
    ktor()
    coroutines()

    // Testing
    junit4()
    truth()
    esspresso()
    turbine()

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}