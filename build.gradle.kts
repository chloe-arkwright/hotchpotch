plugins {
	id("arkwright-root")
	id("arkwright-kotlin")
	id("arkwright-minecraft-split")
	id("arkwright-minecraft-datagens")
}

kotlin {
	target.compilations.apply {
		val main by getting

		named("client") { associateWith(main) }
		named("datagen") { associateWith(main) }
	}
}
