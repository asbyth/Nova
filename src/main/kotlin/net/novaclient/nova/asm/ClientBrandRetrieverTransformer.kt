package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.dsl.overwrite

fun NovaTransformers.overwriteClientBrandRetriever() {
    getClientModName()
}

fun getClientModName() = overwrite {
    className = "net/minecraft/client/ClientBrandRetriever"
    methodName = "getClientModName"
    methodDesc = "()Ljava/lang/String;"

    //     val clientModName: String
    //        get() = "Nova"
    insnList {
        ldc("Nova")
        areturn()
    }
}
