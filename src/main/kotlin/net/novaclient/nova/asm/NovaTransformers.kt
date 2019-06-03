package net.novaclient.nova.asm

import me.falsehonesty.asmhelper.BaseClassTransformer
import net.minecraft.launchwrapper.LaunchClassLoader
import net.novaclient.nova.asm.transformers.client.entity.injectTickEvent
import net.novaclient.nova.asm.transformers.client.gui.injectButtonTimer
import net.novaclient.nova.asm.transformers.client.injectStartNova
import net.novaclient.nova.asm.transformers.client.overwriteClientBrandRetriever
import net.novaclient.nova.asm.transformers.entity.injectMouseDelayFix
import net.novaclient.nova.asm.transformers.world.overwriteGetHorizon

class NovaTransformers : BaseClassTransformer() {

    override fun makeTransformers() {
        // injects
        injectStartNova()
        injectTickEvent()
        injectButtonTimer()
        injectMouseDelayFix()

        // overwrites
        overwriteClientBrandRetriever()
        overwriteGetHorizon()
    }

    override fun setup(classLoader: LaunchClassLoader) {
        classLoader.addTransformerExclusion("net.novaclient.nova.asm.")
    }
}