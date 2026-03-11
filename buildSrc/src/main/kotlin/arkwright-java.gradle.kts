plugins {
	`java-library`
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

tasks {
	withType<JavaCompile> {
		options.encoding = "UTF-8"
		options.release = 25
	}

	jar {
		inputs.property("archivesName", project.base.archivesName)

		listOf(
			rootProject.file("LICENSE"),
			rootProject.file("LICENSE-ASSETS")
		).filter { it.exists() }
		.forEach(::from)
	}

	tasks.withType<ProcessResources> {
		filesMatching("**/*.jsonc") {
			filter { if (it.trimStart().startsWith("//")) null else it }
			name = name.replace(".jsonc", ".json")
		}
	}
}
