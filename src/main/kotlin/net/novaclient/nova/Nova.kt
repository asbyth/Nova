package net.novaclient.nova

import org.apache.logging.log4j.LogManager

object Nova {
    val logger = LogManager.getLogger("Nova")

    fun startNova() {
        logger.info("Starting Nova")
    }
}