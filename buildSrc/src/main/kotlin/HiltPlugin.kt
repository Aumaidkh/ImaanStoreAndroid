import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
            }
            dependencies {
                hilt()
            }
        }
    }
}