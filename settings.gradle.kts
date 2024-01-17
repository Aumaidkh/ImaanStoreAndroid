pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Imaan"
include(":app")
include(":sharedTest")
include(":feature:home")
include(":feature:cart")
include(":feature:payments")
include(":feature:addresses")
include(":feature:profile")
include(":feature:auth")
include(":feature:orders")
include(":core:util")
include(":data:offers")
include(":data:common")
include(":data:cart")
include(":data:addresses")
include(":data:products")
include(":data:categories")
include(":data:user")
include(":core:components")
include(":core:resources")
