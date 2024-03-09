plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("imaan.hilt.plugin")
    id("imaan.realm.plugin")
}

android {
    namespace = "com.imaan.products"
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
}

dependencies {

    implementation(project(":core:util"))
    implementation(project(":core:datasource:remote"))
    implementation(project(":data:auth"))
    implementation(project(":data:common"))
    implementation("androidx.core:core-ktx:1.10.1")
    paging()
}