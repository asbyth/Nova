package net.novaclient.nova.handlers.config

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import net.novaclient.nova.config.ConfigValue
import org.apache.commons.io.IOUtils
import java.io.*
import java.util.*

class ConfigHandler(private val configurationFile: File) {

    private val registeredClasses = ArrayList<Any>()

    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun register(`object`: Any) {
        registeredClasses.add(`object`)
    }

    fun save() {
        val saveObject = JsonObject()
        for (o in registeredClasses) {
            val clazz = o.javaClass
            val classObject = JsonObject()
            for (field in clazz.declaredFields) {
                if (field.getAnnotation(ConfigValue::class.java) != null) {
                    try {
                        field.isAccessible = true
                        classObject.add(field.name, gson.toJsonTree(field.get(o)))
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }

                }
            }

            saveObject.add(clazz.name, classObject)
        }

        try {
            if (!configurationFile.exists()) {
                configurationFile.createNewFile()
                IOUtils.write(gson.toJson(saveObject), FileOutputStream(configurationFile))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun load() {
        try {
            if (!configurationFile.exists()) configurationFile.createNewFile()
            val reader = BufferedReader(FileReader(configurationFile))
            val jsonObject = gson.fromJson(reader, JsonObject::class.java) ?: return
            for (o in registeredClasses) {
                try {
                    val clazz = o.javaClass
                    if (!jsonObject.has(clazz.name)) continue
                    val classObject = jsonObject.getAsJsonObject(clazz.name)
                    for (field in clazz.declaredFields) {
                        if (field.isAnnotationPresent(ConfigValue::class.java) && classObject.has(field.name)) {
                            try {
                                field.isAccessible = true
                                field.set(o, gson.fromJson(classObject.get(field.name), field.type))
                            } catch (e: IllegalAccessException) {
                                e.printStackTrace()
                            }

                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}