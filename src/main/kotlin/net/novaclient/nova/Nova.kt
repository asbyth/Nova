package net.novaclient.nova

import org.apache.logging.log4j.LogManager

object Nova {
    private val instance = Nova()

    private operator fun invoke(): Any {
        return instance
    }

    val logger = LogManager.getLogger("Nova")!!

    fun startNova() {
        logger.info("Starting Nova")
    }
}