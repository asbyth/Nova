package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.dsl.At
import me.falsehonesty.asmhelper.dsl.InjectionPoint
import me.falsehonesty.asmhelper.dsl.inject
import me.falsehonesty.asmhelper.dsl.instructions.*

fun NovaTransformers.injectTickEvent() {
    onUpdate()
}

fun onUpdate() = inject {
    className = "net.minecraft.client.entity.EntityPlayerSP"
    methodName = "onUpdate"
    methodDesc = "()V"
    at = At(
        InjectionPoint.INVOKE(
            Descriptor(
                "net/minecraft/world/World",
                "isBlockLoaded",
                "(Lnet/minecraft/util/BlockPos;)Z"
            )
        ), before = false
    )

    //NovaEventbus.call(TickEvent())
    insnList {
        getKObjectInstance("net/novaclient/nova/event/NovaEventbus")
        createInstance("net/novaclient/nova/event/events/TickEvent", "()V")
        checkcast("net/novaclient/nova/event/NovaEvent")
        invoke(
            InvokeType.VIRTUAL,
            "net/novaclient/event/NovaEventbus",
            "call",
            "(Lnet/novaclient/nova/event/NovaEvent;)V"
        )
    }
}
