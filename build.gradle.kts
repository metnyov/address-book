buildscript {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${LibraryVersion.GRADLE}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${LibraryVersion.KOTLIN}")
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        google()
        maven("https://jitpack.io")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}