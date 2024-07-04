import org.springframework.boot.gradle.tasks.run.BootRun
import org.gradle.api.tasks.testing.Test
import org.springframework.boot.gradle.dsl.SpringBootExtension

plugins {
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.h2database:h2")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configure<SpringBootExtension> {
	// Configuración de H2
	tasks.withType<BootRun> {
		systemProperty("spring.datasource.url", "jdbc:h2:mem:testdb")
		systemProperty("spring.datasource.driverClassName", "org.h2.Driver")
		systemProperty("spring.datasource.username", "sa")
		systemProperty("spring.datasource.password", "password")
		systemProperty("spring.h2.console.enabled", "true")
		systemProperty("spring.h2.console.path", "/h2-console")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}