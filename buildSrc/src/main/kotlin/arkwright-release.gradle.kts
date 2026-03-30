import gradle.kotlin.dsl.accessors._0cb39c16b209519d61ee18b0fceac003.jar
import me.modmuss50.mpp.ReleaseType

plugins {
	id("me.modmuss50.mod-publish-plugin")
}

val props: NonNullPropertyDelegate = the()

val projectVersion: String by props
val projectName: String by props

val versionFabricApi = findProperty("version.fabric.api")
val versionFabricKotlin = findProperty("version.fabric.kotlin")

publishMods {
	tasks.jar.orNull?.also { jar ->
		version = jar.archiveVersion
		file = jar.archiveFile
		type = if ("alpha" in projectVersion) ReleaseType.ALPHA else if ("beta" in projectVersion) ReleaseType.BETA else ReleaseType.STABLE
		displayName = "$projectName $projectVersion"
	}
	modLoaders = listOf("fabric")
	changelog = file("changelog.md").readText(Charsets.UTF_8)
	dryRun = providers.gradleProperty("app.arkwright.chloe.release.dryrun").map { it == "true" }.orElse(true)

	modrinth {
		accessToken = providers.gradleProperty("app.arkwright.chloe.release.modrinth")
		minecraftVersions = listOf("26.1")

		if (versionFabricApi != null) {
			requires("fabric-api")
		}

		if (versionFabricKotlin != null) {
			requires("fabric-language-kotlin")
		}
	}
}
