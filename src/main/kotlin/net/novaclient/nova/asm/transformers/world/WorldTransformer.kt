package net.novaclient.nova.asm.transformers.world

import me.falsehonesty.asmhelper.dsl.overwrite
import net.novaclient.nova.asm.NovaTransformers

fun NovaTransformers.overwriteGetHorizon() {
    getHorizon()
}

fun getHorizon() = overwrite {
    className = "net.minecraft.world.World"
    methodName = "getHorizon"
    methodDesc = "()D"

    // return 0.0D
    insnList {
        dconst_0()
        dreturn()
    }
}