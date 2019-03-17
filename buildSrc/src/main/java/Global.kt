import org.gradle.api.Project

fun disableLintTasks(project: Project) {
    project.tasks.whenTaskAdded {
        if (this.name.startsWith("lint")) {
            this.enabled = false
        }
    }
}