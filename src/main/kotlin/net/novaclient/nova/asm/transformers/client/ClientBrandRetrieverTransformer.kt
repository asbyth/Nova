package net.novaclient.nova.asm.transformers.client

import me.falsehonesty.asmhelper.dsl.overwrite
import net.novaclient.nova.asm.NovaTransformers

fun NovaTransformers.overwriteClientBrandRetriever() {
    getClientModName()
}

fun getClientModName() = overwrite {
    className = "net.minecraft.client.ClientBrandRetriever"
    methodName = "getClientModName"
    methodDesc = "()Ljava/lang/String;"

    //     val clientModName: String
    //        get() = "Nova"
    insnList {
        ldc("Nova")
        areturn()
    }
}
