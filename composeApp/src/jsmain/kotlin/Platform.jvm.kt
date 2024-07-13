class WebPlatform: Platform {
    override val name: String = "Web App"
}

actual fun getPlatform(): Platform = WebPlatform()