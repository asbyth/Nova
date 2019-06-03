package net.novaclient.nova

import net.minecraft.client.Minecraft
import net.novaclient.nova.handlers.NovaHandlers
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.lwjgl.opengl.Display
import java.io.File

object Nova {
    val logger: Logger = LogManager.getLogger("Nova")
    val NOVA_STORAGE_FOLDER = File(Minecraft.getMinecraft().mcDataDir, "Nova")

    fun startNova() {
        logger.info("Starting Nova")

        if (!NOVA_STORAGE_FOLDER.exists()) {
            NOVA_STORAGE_FOLDER.mkdirs()
            logger.info("Creating storage folder")
        }

        logger.info("Initializing Handlers")
        NovaHandlers().init()

        Display.setTitle("Nova")
    }
}