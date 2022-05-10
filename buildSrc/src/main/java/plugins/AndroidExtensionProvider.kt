package plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

interface AndroidExtensionProvider {
    fun getAndroidExtension(project: Project): BaseExtension? {
        return project.extensions.getByName("android") as? BaseExtension
    }

}