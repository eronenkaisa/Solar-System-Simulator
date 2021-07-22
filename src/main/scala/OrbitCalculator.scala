package main.scala
import scala.collection.mutable.Buffer
import scala.math._

  //Tämä luokka yhdistää CelestialObject luokassa tapahtuvan ratapisteiden laskennan ja Timer luokasta tulevan tiedon askelten määrästä.
  //This class connects the calculating in class CelestialObjest to the number of steps which is calculated in class Timer.

class OrbitCalculator(celestialObject1: CelestialObject, celestialObject2: CelestialObject, timer: Timer) {

  def calculateAllCoordinates() = {
    var allCoordinates = Buffer[(BigDecimal, BigDecimal)](celestialObject1.currentPoint)

    for (_ <- 1 to timer.amountOfSteps) {
      var (xForce, yForce) = celestialObject1.calculateForceA(celestialObject2)
      celestialObject1.updateCoordinates(xForce, yForce, timer.durationOfOneStep)
      allCoordinates += celestialObject1.currentPoint

    }
    allCoordinates
  }
}
