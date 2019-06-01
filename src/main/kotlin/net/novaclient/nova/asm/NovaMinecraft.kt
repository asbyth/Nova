package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.BaseClassTransformer
import me.falsehonesty.asmhelper.dsl.At
import me.falsehonesty.asmhelper.dsl.InjectionPoint
import me.falsehonesty.asmhelper.dsl.inject
import me.falsehonesty.asmhelper.dsl.instructions.Descriptor
import me.falsehonesty.asmhelper.dsl.instructions.InvokeType
import me.falsehonesty.asmhelper.dsl.instructions.invoke
import me.falsehonesty.asmhelper.dsl.instructions.invokeKOBjectFunction

class NovaMinecraft : BaseClassTransformer() {

    override fun makeTransformers() {
        injectStartNova()
    }

    private fun injectStartNova() = inject {
        className = "net.minecraft.client.Minecraft"
        methodName = "startGame"
        methodDesc = "()V"
        at = At(InjectionPoint.INVOKE(Descriptor(
            "net/minecraft/client/gui/GuiIngame",
            "<init>",
            "(Lnet/minecraft/client/Minecraft;)V"
        )))

        insnList {
            invokeKOBjectFunction("net/novaclient/nova/Nova", "startNova", "()V")
        }
    }
}