package net.novaclient.nova.asm.transformers.entity

import me.falsehonesty.asmhelper.dsl.At
import me.falsehonesty.asmhelper.dsl.InjectionPoint
import me.falsehonesty.asmhelper.dsl.inject
import me.falsehonesty.asmhelper.dsl.instructions.InvokeType
import me.falsehonesty.asmhelper.dsl.instructions.JumpCondition
import me.falsehonesty.asmhelper.dsl.instructions.ifClause
import me.falsehonesty.asmhelper.dsl.instructions.invoke
import net.novaclient.nova.asm.NovaTransformers

fun NovaTransformers.injectMouseDelayFix() {
//    transformGetLook()
}

fun transformGetLook() = inject {
    className = "net.minecraft.entity.EntityLivingBase"
    methodName = "getLook"
    methodDesc = "(F)Lnet/minecraft/util/Vec3;"
    at = At(InjectionPoint.HEAD)

    insnList {
        aload(0)
        instanceof("net/minecraft/entity/EntityLivingBase")
        ifClause(JumpCondition.NOT_EQUAL) {
            aload(0)
            fload(1)
            invoke(InvokeType.SPECIAL, "net/minecraft/entity/Entity", "getLook", "(F)Lnet/minecraft/util/Vec3;")
            pop()
        }
    }
}