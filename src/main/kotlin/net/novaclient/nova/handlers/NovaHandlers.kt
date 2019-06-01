package net.novaclient.nova.handlers

import net.novaclient.nova.Nova
import net.novaclient.nova.handlers.config.ConfigHandler
import net.novaclient.nova.handlers.display.DisplayHandler
import java.io.File

class NovaHandlers {

    lateinit var displayHandler: DisplayHandler
    lateinit var configHandler: ConfigHandler

    fun init() {
        configHandler = ConfigHandler(File(Nova.NOVA_STORAGE_FOLDER, "novaConfiguration.json"))
        configHandler.load()

        displayHandler = DisplayHandler()
    }
}