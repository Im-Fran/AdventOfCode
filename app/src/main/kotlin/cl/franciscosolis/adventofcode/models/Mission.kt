package cl.franciscosolis.adventofcode.models

import cl.franciscosolis.adventofcode.App
import java.net.URL
import javax.net.ssl.HttpsURLConnection

abstract class Mission (
    val year: Int,
    val day: Int
) {

    companion object {
        private val cache = mutableMapOf<String, String>()
        val missions = mutableMapOf<String, Mission>()
    }
    
    init {
        register()
    }
    
    private fun register() {
        missions["$year-$day"] = this
    }
    
    abstract fun run()
    
    fun input() = cache.computeIfAbsent("$year-$day") {
        val uri = URL("https://adventofcode.com/$year/day/$day/input")
        val httpUrlConnection = uri.openConnection() as HttpsURLConnection
        httpUrlConnection.addRequestProperty("Cookie", "session=${App.session}")
        httpUrlConnection.connect()
        String(httpUrlConnection.inputStream.readBytes())
    }
}