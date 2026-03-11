plugins {
	idea
}

tasks.named<Wrapper>("wrapper") {
	gradleVersion = "9.4.0"
	distributionSha256Sum = "b21468753cb43c167738ee04f10c706c46459cf8f8ae6ea132dc9ce589a261f2"
	distributionType = Wrapper.DistributionType.ALL
}

idea {
	module {
		isDownloadSources = true
		isDownloadJavadoc = true

		excludeDirs.addAll(
			rootDir.walkTopDown().filter {
				it.isDirectory && (it.name == "run" || it.name == "build")
			}
		)
	}
}
