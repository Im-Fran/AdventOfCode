package cl.franciscosolis.adventofcode.years.y2023

import cl.franciscosolis.adventofcode.models.Mission

class Day1: Mission(year = 2023, day = 1){
    
    override fun run() {
        part1()
        part2()
    }
    
    private fun part1() {
        val lines = input().split("\n")
        val numbers = lines.map { line ->
            line.let { 
                val res = it.replace("\\D".toRegex(), "")
                res
            }
        }.filter { it.isNotBlank() }.map {
            val result = it.let { numbers ->
                "${numbers.first()}${numbers.last()}"
            }.trim()
            result.toInt()
        }
        
        println("Sum part 1: ${numbers.sum()}")
    }
    
    private fun part2() {
        fun fixNumbers(string: String): String {
            var str = string
            val numbers = mapOf(
                "one" to "o1e",
                "two" to "t2o",
                "three" to "t3e",
                "four" to "f4r",
                "five" to "f5e",
                "six" to "s6x",
                "seven" to "s7n",
                "eight" to "e8t",
                "nine" to "n9e"
            )

            numbers.forEach { (k,v) ->
                str = str.replace(k,v)
            }
            return str
        }
        
        val lines = input().split("\n")
        val numbers = lines.map { line ->
            fixNumbers(line).let { 
                val res = it.replace("\\D".toRegex(), "")
                res
            }
        }.filter { it.isNotBlank() }.map {
            it.let { numbers ->
                "${numbers.first()}${numbers.last()}"
            }.trim()
        }
        println("Sum part 2: ${numbers.sumOf { it.toInt() }}")
    }
}