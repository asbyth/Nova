package net.novaclient.nova.asm.transformers.client.gui

import me.falsehonesty.asmhelper.dsl.At
import me.falsehonesty.asmhelper.dsl.InjectionPoint
import me.falsehonesty.asmhelper.dsl.inject
import me.falsehonesty.asmhelper.dsl.instructions.Descriptor
import me.falsehonesty.asmhelper.dsl.instructions.int
import me.falsehonesty.asmhelper.dsl.instructions.setLocalField
import net.novaclient.nova.asm.NovaTransformers

fun NovaTransformers.injectButtonTimer() {
// todo: re-enable this when false fixes PUTFIELD instructions ~ enableButtonTimer()
}

fun enableButtonTimer() = inject {
    className = "net.minecraft.client.gui.GuiGameOver"
    methodName = "initGui"
    methodDesc = "()V"
    at = At(InjectionPoint.HEAD)

    // enableButtonsTimer = 0
    insnList {
        aload(0)
        int(0)
        setLocalField(Descriptor("net/minecraft/client/gui/GuiGameOver", "enableButtonsTimer", "I"))
    }
}
