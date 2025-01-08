plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "nl.jovmit.androiddevs"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.haidoan.androiddevs"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.compileSdkVersion.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "nl.jovmit.androiddevs.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // signingConfigs {
    //     release {
    //         storeFile = file("android-devs-keystore")
    //         storePassword = "QWEqwe123!@#"
    //         keyAlias = "android-devs"
    //         keyPassword = "QWEqwe123!@#KEY_123"
    //     }
    // }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // signingConfig = signingConfigs.release
        }
    }

    compileOptions {
        val javaVersion = libs.versions.javaVersion.get()
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
    }

    kotlinOptions {
        jvmTarget = libs.versions.javaVersion.get()
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }

    testOptions.unitTests {
        isReturnDefaultValues = true
        all { tests ->
            tests.useJUnitPlatform()
            tests.testLogging {
                events("passed", "failed", "skipped")
            }
        }
    }
}

dependencies {
    implementation(projects.core.view)
    implementation(projects.core.network)
    implementation(projects.feature.welcome)
    implementation(projects.feature.signup)
    implementation(projects.feature.login)
    implementation(projects.feature.timeline)
    implementation(projects.feature.postdetails)

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.hilt)

    kapt(libs.hilt.compiler)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.ui.testing)
    androidTestImplementation(projects.testutils)

    kaptAndroidTest(libs.hilt.android.test.compiler)

    testImplementation(projects.testutils)

    testRuntimeOnly(libs.junit.jupiter.engine)
}