pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
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
include(":feature:auth")
include(":feature:orders")
include(":core:util")
include(":data:offers")
include(":data:common")
include(":data:cart")
include(":data:address")
include(":data:products")
include(":data:categories")
include(":data:user")
include(":core:components")
include(":core:resources")
include(":data:order")
include(":data:payment")
include(":data:total")
include(":feature:profile")
include(":design-system")
include(":domain:common")
include(":feature:onboarding")
include(":feature:products")
include(":feature:wishlist")
include(":feature:tracking")
include(":feature:product-details")
include(":data:delivery")
include(":core:datasource:remote")
include(":data:auth")
