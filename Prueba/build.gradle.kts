import org.springframework.boot.gradle.dsl.SpringBootExtension

plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.h2database:h2")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.mockito:mockito-inline:5.0.0")
	testImplementation("org.mockito:mockito-junit-jupiter")
}

configure<SpringBootExtension> {
	tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
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
	testLogging {
		events("passed", "skipped", "failed")
	}
}

