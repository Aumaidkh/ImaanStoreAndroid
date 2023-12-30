import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String){
    add("implementation",dependency)
}

fun DependencyHandler.debugImplementation(dependency: String){
    add("debugImplementation",dependency)
}

fun DependencyHandler.testImplementation(dependency: String){
    add("testImplementation",dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String){
    add("androidTestImplementation",dependency)
}

fun DependencyHandler.kapt(dependency: String){
    add("kapt",dependency)
}

fun DependencyHandler.kaptAndroidTest(dependency: String){
    add("kaptAndroidTest",dependency)
}

fun DependencyHandler.project(dependency: Dependency){
    add("implementation",dependency)
}