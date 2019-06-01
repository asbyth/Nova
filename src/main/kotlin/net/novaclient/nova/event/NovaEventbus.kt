package net.novaclient.nova.event

import net.novaclient.nova.util.AsyncThreading
import net.novaclient.nova.util.Reflection
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

object NovaEventbus {

    private val registeredClasses = HashMap<Any, Map<Class<out NovaEvent>, List<Method>>>()
    @Volatile
    private var isCalling = false

    private fun isEventMethod(method: Method): Boolean {
        if (method.parameterCount <= 0) {
            return false
        }

        val paramType = method.parameters[0].type

        return if (paramType.isPrimitive) {
            false
        } else method.isAnnotationPresent(NovaEventSubscriber::class.java) && method.parameterCount == 1 && Reflection.isSubclassOf(
            paramType,
            NovaEvent::class.java
        )
    }

    fun register(o: Any) {
        val clazz = o.javaClass
        val map = mutableMapOf<Class<out NovaEvent>, MutableList<Method>>()

        clazz.methods.forEach {
            if (isEventMethod(it)) {
                val c = it.parameterTypes[0]
                map.putIfAbsent(c as Class<out NovaEvent>, mutableListOf())
                map[c]?.add(it)
            }
        }

        AsyncThreading.asyncRunnable {
            while (isCalling) {
                registeredClasses[o] = map
            }
        }
    }

    fun unregister(o: Any) {
        registeredClasses.remove(o)
    }

    fun call(event: NovaEvent) {
        isCalling = true
        for ((o, map) in registeredClasses) {

            if (!map.containsKey(event.javaClass)) {
                continue
            }

            val list = map[event.javaClass]

            for (m in list!!) {
                try {
                    m.invoke(o, event)
                } catch (e: InvocationTargetException) {
                    e.cause!!.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        isCalling = false
    }
}