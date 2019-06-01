package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.BaseClassTransformer
import me.falsehonesty.asmhelper.dsl.At
import me.falsehonesty.asmhelper.dsl.InjectionPoint
import me.falsehonesty.asmhelper.dsl.inject
import me.falsehonesty.asmhelper.dsl.instructions.InvokeType
import me.falsehonesty.asmhelper.dsl.instructions.invoke

class NovaMinecraft : BaseClassTransformer() {

    override fun makeTransformers() {
        injectStartNova()
    }

    private fun injectStartNova() = inject {
        className = "net.minecraft.client.Minecraft"
        methodName = "startGame"
        at = At(InjectionPoint.RETURN) // change to invoke at GuiIngame whenever false is up bc i dont know how to use invoke rn lol ~ asbyth

        insnList {
            invoke(InvokeType.VIRTUAL, "net/novaclient/nova/Nova", "startNova", "()V") // i got a feelin that this isnt gonna work
        }
    }
}