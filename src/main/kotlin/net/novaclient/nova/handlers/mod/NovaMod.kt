package net.novaclient.nova.handlers.mod

import net.novaclient.nova.Nova

abstract class NovaMod {

    abstract val debugInfo: Map<String, Any>

    @Throws(Throwable::class)
    internal abstract fun initMod()

    internal abstract fun registerEventHandler()

    @Throws(Throwable::class)
    internal abstract fun close()

    class NovaModListener {
        @Throws(Throwable::class)
        fun close() {
            for (mod in NOVA_MODS) {
                mod.close()
            }
        }
    }

    companion object {
        private val CLASS_TO_LOAD = arrayOf<Class<*>>()
        private val NOVA_MODS = ArrayList<NovaMod>()

        @Throws(Throwable::class)
        fun init() {
            for (clazz in CLASS_TO_LOAD) {
                registerMod(clazz.newInstance())
            }
        }

        fun minecraftInitialize() {
            for (mod in NOVA_MODS) {
                mod.registerEventHandler()
                Nova.logger.info("Registered event handler(s) of mod " + mod.javaClass.simpleName)
            }

            Nova.logger.info("Registered all event handlers")
        }

        fun registerMod(modClass: Any) {
            try {
                val mod = modClass as NovaMod
                mod.initMod()
                NOVA_MODS.add(mod)
                Nova.logger.info("Successfully initialized mod " + mod.javaClass.simpleName)
            } catch (throwable: Throwable) {
                Nova.logger.warn(
                    "An error occured while initializing mod " + modClass.javaClass.simpleName,
                    throwable
                )
            }
        }

        fun registerMod(mod: NovaMod, modClass: Any) {
            try {
                mod.initMod()
                NOVA_MODS.add(mod)
                Nova.logger.info("Successfully initialized mod " + mod.javaClass.simpleName)
            } catch (throwable: Throwable) {
                Nova.logger.warn(
                    "An error occured while initializing mod " + modClass.javaClass.simpleName,
                    throwable
                )
            }
        }
    }
}