package cl.franciscosolis.adventofcode.years.y2023

import cl.franciscosolis.adventofcode.models.Mission

class Day2 : Mission(2023, 2) {

    override fun run() {
        val input = this.input()
        val games = input.split("\n").filter { it.isNotBlank() }.map { line ->
            val id = line.substringAfter("Game ").substringBefore(":")
            val cubes = line.substringAfter(": ")
            val cubesList = cubes.replace(";", ",").split(",").filter { it.trim().isNotBlank() }.map { cube ->
                val (amount, color) = cube.trim().split(" ")
                Cube(CubeColor.valueOf(color.uppercase()), amount.toInt())
            }
            Game(id.toInt(), cubesList)
        }

        val possibleGames = games.filter { game ->
            game.cubes.all { cube ->
                when (cube.color) {
                    CubeColor.RED -> cube.amount <= 12
                    CubeColor.GREEN -> cube.amount <= 13
                    CubeColor.BLUE -> cube.amount <= 14
                }
            }
        }

        val sum = possibleGames.sumOf { it.id }
        println("(Part 1) Sum of possible games: $sum")

        var result = 0

        games.forEach { game ->
            val maxRed = game.cubes.filter { it.color == CubeColor.RED }.maxOf { it.amount }
            val maxGreen = game.cubes.filter { it.color == CubeColor.GREEN }.maxOf { it.amount }
            val maxBlue = game.cubes.filter { it.color == CubeColor.BLUE }.maxOf { it.amount }
            result += (maxRed * maxGreen * maxBlue)
        }

        println("(Part 2) Result: $result")
    }
}

data class Game(val id: Int, val cubes: List<Cube>)
data class Cube(val color: CubeColor, val amount: Int)
enum class CubeColor {
    RED, GREEN, BLUE
}
