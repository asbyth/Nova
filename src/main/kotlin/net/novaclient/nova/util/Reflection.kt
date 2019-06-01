package net.novaclient.nova.util

import java.lang.reflect.Field
import java.lang.reflect.Method

object Reflection {

    fun getMethod(clazz: Class<*>, methodNames: Array<String>, parameters: Array<Class<*>>): Method? {
        for (name in methodNames) {
            try {
                val m = clazz.getDeclaredMethod(name, *parameters)

                if (m != null) {
                    m.isAccessible = true
                    return m
                }
            } catch (ignored: NoSuchMethodException) {
            }

        }
        return null
    }

    fun getField(clazz: Class<*>, fieldNames: Array<String>): Field? {
        for (name in fieldNames) {
            try {
                val f = clazz.getDeclaredField(name)

                if (f != null) {
                    f.isAccessible = true
                    return f
                }
            } catch (ignored: NoSuchFieldException) {
            }

        }
        return null
    }

    fun isSubclassOf(clazz: Class<*>?, superClass: Class<*>): Boolean {
        var clazz = clazz
        if (superClass == Any::class.java) {
            return true
        }

        return if (clazz == superClass) {
            true
        } else {
            clazz = clazz!!.superclass

            if (clazz == null || clazz == Any::class.java) {
                false
            } else isSubclassOf(clazz, superClass)
        }
    }
}