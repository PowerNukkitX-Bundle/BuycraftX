rootProject.name = "BuycraftX"
include(":buycraftx-common")
include(":buycraftx-plugin-shared")
include(":buycraftx-pnx")
project(":buycraftx-common").projectDir = file("common")
project(":buycraftx-plugin-shared").projectDir = file("plugin-shared")
project(":buycraftx-pnx").projectDir = file("pnx")
