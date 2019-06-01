package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.BaseClassTransformer
import net.minecraft.launchwrapper.LaunchClassLoader

class NovaTransformers : BaseClassTransformer() {

    override fun makeTransformers() {
        // injects
        injectStartNova()
        injectTickEvent()

        // overwrites
        overwriteClientBrandRetriever()
    }

    override fun setup(classLoader: LaunchClassLoader) {
        classLoader.addTransformerExclusion("net.novaclient.nova.asm.")
    }
}