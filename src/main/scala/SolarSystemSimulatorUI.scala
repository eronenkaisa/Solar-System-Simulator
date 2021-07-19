package main.scala

import scala.Console.{RESET, UNDERLINED}
import scala.io.StdIn.readLine

object SolarSystemSimulatorUI extends App {

  // Welcome message which includes user guide.
  private def run() = {
    println(s"${UNDERLINED}SOLAR SYSTEM SIMULATOR")
    println(s"${RESET}Give mass, starting point and speed for your satellite and duration of one simulation step and simulation period. Separate them using semicolon and without units.\n" +
      "The simulator calculates orbit points for your satellite around the Sun using your values and also for planets in our solar system.\n\n")
    println(s"${UNDERLINED}Ideal values for the satellite:")
    println(s"${RESET}Mass: 2 000 kg - 5000 kg\nStarting point (Distance from the Sun): 10 000 000 km - 1 000 000 000 km\n" +
      "Speed: 20 - 30 km/s\n\n")
    println(s"${UNDERLINED}Ideal values for the simulation:")
    println(s"${RESET}Simulation step: 12 - 24h\nSimulaation period: 200d - 500d \n\n" +
      "Example input: 4 500; 18 500 000; 25; 17; 250\n")

    val input = readLine("Input:")

    try {
      Simulator.simulate(input)
    } catch {
      case InvalidInputException(message) => println(message)
      case InvalidPlanetInfoException(message) => println(message)
    }
  }

  this.run()

}
