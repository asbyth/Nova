package net.novaclient.nova.tweaker

import net.minecraft.launchwrapper.ITweaker
import net.minecraft.launchwrapper.LaunchClassLoader
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class NovaTweaker : ITweaker {

    private var novaArguments: MutableList<String>? = null

    override fun acceptOptions(args: MutableList<String>?, gameDir: File?, assetsDir: File?, profile: String?) {
        novaArguments = ArrayList(args)

        if (gameDir != null) {
            novaArguments!!.addAll(Arrays.asList("--gameDir", gameDir.absolutePath))
        }

        if (assetsDir != null) {
            novaArguments!!.addAll(Arrays.asList("--assetsDir", assetsDir.absolutePath))
        }

        if (profile != null) {
            novaArguments!!.addAll(Arrays.asList("--version", profile))
        }
    }

    override fun getLaunchTarget(): String {
        return "net.minecraft.client.main.Main"
    }

    override fun getLaunchArguments(): Array<String> {
        return novaArguments!!.toTypedArray()
    }

    override fun injectIntoClassLoader(classLoader: LaunchClassLoader?) {

    }
}