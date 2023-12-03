package cl.franciscosolis.adventofcode

import cl.franciscosolis.adventofcode.models.Mission
import cl.franciscosolis.adventofcode.years.y2023.*
import java.io.File
import java.util.Properties
import kotlin.system.exitProcess

class App {
    
    companion object {
        val session = Properties().let {
            val file = File("config.properties").apply {
                if(!exists()) {
                    createNewFile()
                }
                
                it.load(this@apply.reader())
            }
            val session = if(it.containsKey("session")) {
                it.getProperty("session")
            } else {
                it.getProperty("session", let {
                    println("Please enter your session cookie: ")
                    readlnOrNull()
                } ?: "")
            }
            
            if(!it.containsKey("session")) {
                it.setProperty("session", session)
                it.store(file.outputStream(), null)
            }
            
            session
        }
        
        @JvmStatic
        fun main(vararg args: String) {
            register()
            
            print("Year to execute [${java.time.Year.now()}]: ")
            val userInput = readlnOrNull()
            val yearToExecute = userInput?.takeIf { it.isNotEmpty() }?.toInt() ?: java.time.Year.now().value
            println("\nYou selected year: $yearToExecute")
            
            
            val month = java.time.LocalDate.now().month
            val defaultDay = if (month == java.time.Month.DECEMBER) java.time.LocalDate.now().dayOfMonth else null
            print("Day to execute${if(defaultDay != null) " [$defaultDay]" else ""}: ")
            val userInputDay = readlnOrNull()
            val dayToExecute = userInputDay?.takeIf { it.isNotEmpty() }?.toInt() ?: defaultDay
            println("\nYou selected day: $dayToExecute")
            
            val mission = Mission.missions["$yearToExecute-$dayToExecute"] ?: run {
                println("Couldn't find the missions under $yearToExecute and $dayToExecute")
                exitProcess(1)
            }
            
            mission.run()
        }
        
        private fun register() {
            Day1(); Day2(); Day3(); Day4(); Day5()
        }
    }
    
}