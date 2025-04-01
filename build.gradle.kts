plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "9.8.1"
}

group = "com.patos"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation ("org.modelmapper:modelmapper:3.1.1")
	implementation("org.apache.poi:poi-ooxml:5.2.3")


}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	url = System.getenv("FLYWAY_DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/mydatabase"
	user = System.getenv("FLYWAY_DATABASE_USER") ?: "myuser"
	password = System.getenv("FLYWAY_DATABASE_PASSWORD") ?: "secret"
	schemas = arrayOf("public")
	outOfOrder = true

}

