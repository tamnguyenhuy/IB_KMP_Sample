val analysisDir = files(
    "../../composeApp/src/commonMain",
    "../../composeApp/src/androidMain",
    "../../composeApp/src/iosMain",
)

val verifyRules = file("./lint/detekt.yml")
val formatRules = file("./lint/format.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.23.8")
}

apply(plugin = "io.gitlab.arturbosch.detekt")

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8")
}

detekt {
    this.config = files(verifyRules)
    this.toolVersion = "1.23.8"
    this.ignoreFailures = false
    this.debug = true
    this.autoCorrect = true
    this.reports {
        html {
            enabled = true
            destination = file("lint/report.html")
        }
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = false
    }
    this.parallel = false
    this.source = analysisDir
}

//tasks.withType(io.gitlab.arturbosch.detekt.Detekt::class) {
//    exclude("**/serverapi/**", "**/krypto/**", "**/protobuf/**")
//}

tasks.register("detektFormat", io.gitlab.arturbosch.detekt.Detekt::class) {
    description = "Formats whole project."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
    config.setFrom(formatRules)
    setSource(analysisDir)
    include(listOf(kotlinFiles, kotlinScriptFiles))
    reports {
        html {
            enabled = true
            destination = file("lint/formater.html")
        }
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = false
    }
}

tasks.register("verifyAll", io.gitlab.arturbosch.detekt.Detekt::class){
    dependsOn("detektFormat")
    dependsOn("detekt")
    tasks.findByName("detekt")?.mustRunAfter("detektFormat")
}