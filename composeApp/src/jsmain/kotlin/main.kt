import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import okio.FileSystem
import org.jetbrains.skiko.wasm.onWasmReady


@OptIn(ExperimentalComposeUiApi::class)
fun main(){

    onWasmReady {
        CanvasBasedWindow ("kmpapp") {

            CompositionLocalProvider(
                LocalImageLoader provides remember { generateImageLoader() },
            ) {

                App()
            }


        }
    }

}



private fun generateImageLoader(): ImageLoader {
    return ImageLoader {

        components {
            setupDefaultComponents()
        }
        interceptor {
            bitmapMemoryCacheConfig {
                maxSize(32 * 1024 * 1024) // 32MB
            }
            diskCacheConfig {
                directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY)
                maxSizeBytes(256L * 1024 * 1024) // 256MB
            }
        }
    }
}


