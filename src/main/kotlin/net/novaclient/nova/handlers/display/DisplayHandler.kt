package net.novaclient.nova.handlers.display

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.novaclient.nova.event.NovaEventSubscriber
import net.novaclient.nova.event.events.TickEvent

class DisplayHandler {

    private var screen: GuiScreen? = null

    fun displayScreenNextTick(screen: GuiScreen) {
        this.screen = screen
    }

    @NovaEventSubscriber
    fun tickEvent(event: TickEvent) {
        if (screen != null) {
            Minecraft.getMinecraft().displayGuiScreen(screen)
            screen = null
        }
    }
}