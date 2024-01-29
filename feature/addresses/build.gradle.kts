plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("imaan.compose.plugin")
    id("imaan.hilt.plugin")
}

android {
    namespace = "com.imaan.addresses"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(project(":design-system"))
    implementation(project(":core:util"))
    implementation(project(":data:common"))
    implementation(project(":data:user"))
    implementation(project(":data:address"))
    implementation(project(":data:order"))
    implementation(project(":data:products"))
    implementation(project(":data:payment"))
    navigation()
}