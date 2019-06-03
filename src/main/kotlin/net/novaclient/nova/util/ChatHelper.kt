package net.novaclient.nova.util

import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText

object ChatHelper {

    private val prefix =
        ChatColor.GRAY.toString() + "[" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "Nova" + ChatColor.GRAY + "] "

    @JvmOverloads
    fun addChatMessage(message: String, appendPrefix: Boolean = true) {
        val sb = StringBuilder()
        if (appendPrefix) {
            sb.append(prefix)
        }

        var currentColor = ""

        var i = 0
        while (i < message.length) {
            val currentChar = message[i]

            val chatCharacter = "§"
            val chatChar = '§'

            if (currentChar == chatChar) {
                if (currentColor.isEmpty()) {
                    sb.append(chatChar).append(message[i + 1])
                }

                currentColor = chatCharacter + message[i + 1]
                i++

                if (i >= message.length) {
                    break
                }
            } else {
                sb.append(currentColor).append(currentChar)
            }
            i++
        }

        Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(sb.toString()))
    }

    fun sendChatMessage(message: String) {
        if (Minecraft.getMinecraft().thePlayer == null) return
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message)
    }
}
