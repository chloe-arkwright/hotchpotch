plugins {
	id("arkwright-minecraft")
}

val props = the<NonNullPropertyDelegate>()

val projectId: String by props

fabricApi {
	configureDataGeneration {
		client = true
		modId = "$projectId-data"
		createSourceSet = true
	}
}
