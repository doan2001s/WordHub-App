plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.workhub.global"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.workhub.global"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"http://192.168.1.15:3000/api/v1/\""
            )
        }

        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.workhub.com/api/v1/\""
            )

            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    testImplementation(libs.junit)
    testImplementation(libs.json)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.security.crypto)
}
