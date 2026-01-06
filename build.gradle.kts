import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	id("net.fabricmc.fabric-loom") version "1.14.10"
	id("org.jetbrains.kotlin.jvm") version "2.3.0"
}

val versionMod: String by project
val mavenGroup: String by project
val projectId: String by project


version = versionMod
group = mavenGroup

base.archivesName = projectId

loom {
	splitEnvironmentSourceSets()

	accessWidenerPath = file("src/main/resources/${projectId}.classTweaker")

	runs {
		named("client") {
			property("fabric-tag-conventions-v2.missingTagTranslationWarning", "VERBOSE")
		}
	}
}

fabricApi {
	configureDataGeneration {
		modId = projectId
		client = true
		createSourceSet = true
	}
}

val versionMinecraft: String by project

dependencies {
	val versionLoader: String by project
	val versionFabricApi: String by project
	val versionFabricKotlin: String by project

	minecraft("com.mojang:minecraft:${versionMinecraft}")
	implementation("net.fabricmc:fabric-loader:${versionLoader}")

	implementation("net.fabricmc.fabric-api:fabric-api:${versionFabricApi}")
	implementation("net.fabricmc:fabric-language-kotlin:${versionFabricKotlin}")
}

tasks.withType<JavaCompile>().configureEach {
	options.encoding = "UTF-8"
	options.release = 25
}

kotlin {
	compilerOptions {
		jvmTarget = JvmTarget.JVM_25
	}

	target.compilations.apply {
		val main by getting

		named("client") {
			associateWith(main)
		}
		named("datagen") {
			associateWith(main)
		}
	}
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
	version = "${versionMod}+${versionMinecraft}"
	from("LICENSE")
}

tasks.processResources {
	inputs.property("version", versionMod)

	filesMatching("fabric.mod.json") {
		expand(inputs.properties)
	}
}