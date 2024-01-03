import org.gradle.api.JavaVersion

object ProjectConfig {
    const val appId = "com.imaan.store"
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val versionCode = 1
    const val versionName = "1.0"
    const val instrumentedTestRunner = "com.imaan.store.HiltTestRunner"

    val jdkVersion = JavaVersion.VERSION_17
}