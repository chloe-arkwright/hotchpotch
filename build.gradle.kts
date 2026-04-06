plugins {
	id("arkwright-root")
	id("arkwright-minecraft")
	id("arkwright-release")
}

val props = the<NonNullPropertyDelegate>()

val versionChloeLib: String by props

dependencies {
	include(api("app.arkwright.chloe.lib:chloe-lib:$versionChloeLib")!!)
}

publishMods {
	modrinth {
		projectId = "T8Cxr5Cl"
	}
}
