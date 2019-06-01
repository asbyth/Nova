package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.dsl.At
import me.falsehonesty.asmhelper.dsl.InjectionPoint
import me.falsehonesty.asmhelper.dsl.inject
import me.falsehonesty.asmhelper.dsl.instructions.Descriptor
import me.falsehonesty.asmhelper.dsl.instructions.invokeKOBjectFunction

fun NovaTransformers.injectStartNova() {
    startGame()
}

fun startGame() = inject {
    className = "net.minecraft.client.Minecraft"
    methodName = "startGame"
    methodDesc = "()V"
    at = At(
        InjectionPoint.INVOKE(
            Descriptor(
                "net/minecraft/client/gui/GuiIngame",
                "<init>",
                "(Lnet/minecraft/client/Minecraft;)V"
            )
        ), before = false
    )

    //Nova.startNova()
    insnList {
        invokeKOBjectFunction("net/novaclient/nova/Nova", "startNova", "()V")
    }
}