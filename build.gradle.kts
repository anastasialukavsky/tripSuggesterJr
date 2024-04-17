import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.*
import org.jooq.meta.jaxb.Configuration
import org.jooq.meta.jaxb.Target
import java.sql.Connection
import java.sql.DriverManager
import java.util.*


plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.netflix.dgs.codegen") version "6.0.3"
    id("org.jooq.jooq-codegen-gradle") version "3.19.7"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

group = "dev.tripsuggesterjr"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springAiVersion"] = "0.8.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    //TODO configure security
//	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    //TODO configure redis
//	implementation("org.springframework.ai:spring-ai-redis-spring-boot-starter")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//	testImplementation("org.springframework.security:spring-security-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.generateJava {
    schemaPaths.add("${projectDir}/src/main/resources/graphql-client")
    packageName = "dev.tripsuggesterjr.tripSuggesterJr.codegen"
    generateClient = true
}

//static def connectToPostgres(connParams) {
//	def props = new Properties()
//	props.setProperty("user", connParams.get("user"))
//	props.setProperty("password", connParams.get("password"))
//	def conn = new Driver()
//		.connect("jdbc:postgresql://${connParams.getOrDefault("host", "localhost")}:" +
//				"${connParams.get("port")}/${connParams.get("db")}".toString(), props)
//	return conn
//}

fun connectToPostgres(connParams: Map<String, String>): Connection {
    val props = Properties()
    props.setProperty("user", connParams["user"])
    props.setProperty("password", connParams["password"])
    val connUrl =
        "jdbc:postgresql://${connParams.getOrDefault("host", "localhost")}:${connParams["port"]}/${connParams["db"]}"
    println("CONN_URL $connUrl")
    return DriverManager.getConnection(connUrl, props)
}


//fun embeddedPostgres(sqlAction: (conn: java.sql.Connection, connParams: Map<String, String>) -> Unit) {
//	val pg = EmbeddedPostgres.start()
//	pg.postgresDatabase.connection.prepareStatement("CREATE DATABASE jooq;").execute()
//	pg.postgresDatabase.connection.prepareStatement("ALTER USER postgres WITH PASSWORD 'postgres'").execute()
//	val db = pg.getDatabase("postgres", "jooq")
//	val conn = db.connection
//	val connParams = mapOf(
//		"host" to "localhost",
//		"port" to pg.port.toString(),
//		"user" to "postgres",
//		"password" to "postgres",
//		"db" to "jooq"
//	)
//	sqlAction(conn, connParams)
//	pg.close()
//}


fun jooqGenerate(
    jdbcUrl: String,
    username: String,
    password: String,
    schemaFile: String,
    packageName: String
) {
    val jooqConfiguration = Configuration()
        .withJdbc(
            Jdbc()
                .withDriver("org.postgresql.Driver")
                .withUrl(jdbcUrl)
                .withUser("postgres")
                .withPassword("Trip1234")
        )
        .withGenerator(
            Generator()

                .withName("org.jooq.codegen.KotlinGenerator")

                .withDatabase(
                    Database()

                        .withIncludes("public.*")
                        .withExcludes("databasechangelog|databasechangeloglock")
                )
                .withTarget(
                    Target()
                        .withPackageName(packageName)
                        .withDirectory(project.file("build/generated/sources/jooq").absolutePath)
                )
        )


    GenerationTool.generate(jooqConfiguration)
}



tasks.register("jooqGenerate") {
    doLast {
        // Initialize jooqGenerateClasspath variable as MutableList<File>
        val jooqGenerateClasspath = mutableListOf<File>()


        val driverPaths = configurations.runtimeClasspath.get().filter { it.name.contains("postgresql") }

        println("CONFIG $configurations")
        if (!driverPaths.isEmpty) {
            jooqGenerateClasspath.addAll(driverPaths)
        } else {
            error("PostgreSQL driver JAR not found in runtime classpath.")
        }

        jooqGenerate(
            jdbcUrl = "jdbc:postgresql://localhost:5432/tripjr_db",
            username = "postgres",
            password = "Trip1234",
            schemaFile = "SourceMigrationSchema.sql",
            packageName = "dev.tripsuggesterjr.tripSuggesterJr.jooq"
        )
    }
}

//tasks.compileKotlin.get().dependsOn("jooqGenerate")