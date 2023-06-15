import org.gradle.api.tasks.testing.logging.TestExceptionFormat

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.5.1/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */

version = "0.0.1"

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:32.0.1-jre")

    // https://mvnrepository.com/artifact/xerces/xercesImpl
    implementation("xerces:xercesImpl:2.12.2")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.8.2")
        }
    }
}

// Test Logging
tasks.withType<Test> {
    testLogging {
        showStandardStreams = true
        events("standardOut", "started", "passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                         "Implementation-Version" to project.version))
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.register<Copy>("copyReportsAndJavadocForDocumentation") {
    into("$rootDir")

    from("$buildDir/docs"){
        into("docs")
    }
    from("$buildDir/reports/tests"){
        into("docs")
    }
}

publishing {
    publications {
        create<MavenPublication>("bonapata-ancheta") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "bonapata-ancheta"
            url = uri("../dist")
        }

        // TODO: Me falta crear mi cuenta y terminar de ajustar el proyecto
        // https://central.sonatype.org/publish/publish-guide/#introduction
        /*
        maven {
            name = "OSSRH"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
        */
    }
}
