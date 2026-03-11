plugins {
	id("arkwright-java")
	id("net.fabricmc.fabric-loom")
}

val props = project.extensions.create("props", NonNullPropertyDelegate::class, project.extra)

val projectVersion: String by props
val projectGroup: String by props
val projectId: String by props

version = projectVersion
group = projectGroup

base.archivesName = projectId

loom {
	accessWidenerPath = file("src/main/resources/$projectId.classTweaker").takeIf { it.exists() }

	runs {
		named("client") {
			property("fabric-tag-conventions-v2.missingTagTranslationWarning", "VERBOSE")
		}
	}
}

repositories {
	exclusiveContent {
		forRepository {
			maven {
				name = "Modrinth Maven"
				url = uri("https://api.modrinth.com/maven")
			}
		}
		filter {
			includeGroup("maven.modrinth")
		}
	}
}

val versionMinecraft: String by props
val versionFabricLoader: String by props

val versionFabricApi = findProperty("version.fabric.api")
val versionFabricKotlin = findProperty("version.fabric.kotlin")

dependencies {
	minecraft("com.mojang:minecraft:$versionMinecraft")

	implementation("net.fabricmc:fabric-loader:$versionFabricLoader")

	versionFabricApi?.let { ver ->
		implementation("net.fabricmc.fabric-api:fabric-api:$ver")
	}

	versionFabricKotlin?.let { ver ->
		implementation("net.fabricmc:fabric-language-kotlin:$ver")
	}

}

tasks {
	jar {
		version = "$projectVersion+$versionMinecraft"
	}

	tasks.withType<ProcessResources> {
		inputs.properties(
			"project" to mapOf(
				"version" to projectVersion
			)
		)

		filesMatching("fabric.mod.json") {
			expand(inputs.properties)
		}
	}
}
