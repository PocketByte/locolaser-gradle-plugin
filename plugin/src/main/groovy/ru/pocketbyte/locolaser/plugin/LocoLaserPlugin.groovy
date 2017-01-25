package ru.pocketbyte.locolaser.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec

class LocoLaserPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.repositories {
            maven { url  "http://pocketbyte.bintray.com/maven" }
        }

        project.configurations {
            localize
        }

        project.extensions.create("localize", LocalizeExtension)

        project.afterEvaluate {

            project.task('localize', type: JavaExec) {
                group = 'localization'
                description = 'Run LocoLaser'
                classpath project.configurations.localize
                main = "ru.pocketbyte.locolaser.Main"
                args = [project.localize.config]
            }

            project.task('localizeForce', type: JavaExec) {
                group = 'localization'
                description = 'Run LocoLaser with force'
                classpath project.configurations.localize
                main = "ru.pocketbyte.locolaser.Main"
                args = [project.localize.config, "--force"]
            }

            project.task('localizeWithExport', type: JavaExec) {
                group = 'localization'
                description = 'Run LocoLaser with force and conflict strategy = export_new_local'
                classpath project.configurations.localize
                main = "ru.pocketbyte.locolaser.Main"
                args = [project.localize.config, "--force", "--export"]
            }
        }
    }
}