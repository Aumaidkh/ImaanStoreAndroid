import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationVersion}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    }

    object Testing {
        const val navigation = "androidx.navigation:navigation-testing:${Versions.navigationVersion}"
        const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
        const val junit4 = "junit:junit:${Versions.junit4}"
        const val mock = "io.mockk:mockk:${Versions.mock}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoIdlingResouce = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"
    }
}

fun DependencyHandler.lifecycle(){
    implementation(Dependencies.lifecycleRuntimeKtx)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.activityCompose)
    debugImplementation(Dependencies.composeUiToolingPreview)
}

fun DependencyHandler.junit4(){
    androidTestImplementation(Dependencies.Testing.junit4)
    testImplementation(Dependencies.Testing.junit4)
}

fun DependencyHandler.esspresso(){
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Testing.espressoIdlingResouce)
}

fun DependencyHandler.truth(){
    testImplementation(Dependencies.Testing.truth)
    androidTestImplementation(Dependencies.Testing.truth)
}

fun DependencyHandler.navigation(){
    implementation(Dependencies.Navigation.hiltNavigation)
    implementation(Dependencies.Navigation.navigationCompose)
    androidTestImplementation(Dependencies.Testing.navigation)
}

fun DependencyHandler.hilt(){
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}