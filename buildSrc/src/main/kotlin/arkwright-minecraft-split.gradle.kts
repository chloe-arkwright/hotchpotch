plugins {
	id("arkwright-minecraft")
}

val props = the<NonNullPropertyDelegate>()

val projectId: String by props

loom {
	splitEnvironmentSourceSets()

	mods {
		create(projectId) {
			sourceSet("main")
			sourceSet("client")
		}
	}
}
