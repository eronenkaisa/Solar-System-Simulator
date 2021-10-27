package main.scala
import scala.collection.mutable.Buffer
import scala.math._

  //Tämä luokka yhdistää CelestialObject luokassa tapahtuvan ratapisteiden laskennan ja Timer luokasta tulevan tiedon askelten määrästä.
  //This class connects the calculating in class CelestialObjest to the number of steps which is calculated in class Timer.
  //Here the planet/satellite is always CelestialObject1 and the Sun is CelestialObject2.

class OrbitCalculator(celestialObject1: CelestialObject, celestialObject2: CelestialObject, timer: Timer) {

  def calculateAllCoordinates() = {
    var allCoordinates = Buffer[(BigDecimal, BigDecimal)]()

    for (t <- 1 to timer.amountOfSteps) {
      allCoordinates += celestialObject1.calculateNextCoordinate(timer.durationOfOneStep * t)
    }
    allCoordinates
  }
}
