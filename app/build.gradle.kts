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
            signingConfig = signingConfigs.getByName("debug")
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
    implementation(project(":design-system"))
    implementation(project(":core:util"))
    implementation(project(":data:common"))
    implementation(project(":data:user"))
    implementation(project(":data:address"))
    implementation(project(":data:categories"))

    implementation(project(":feature:onboarding"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:products"))
    implementation(project(":feature:cart"))
    implementation(project(":feature:addresses"))
    implementation(project(":feature:payments"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:orders"))
    implementation(project(":feature:wishlist"))

    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    lifecycle()
    compose()
    navigation()
    hilt()
    ktor()
    coil()
    coroutines()

    // Testing
    junit4()
    truth()
    esspresso()
    turbine()

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}