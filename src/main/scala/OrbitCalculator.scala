package main.scala
import scala.collection.mutable.Buffer
import scala.math._

//Tämä luokka yhdistää CelestialObject luokassa tapahtuvan ratapisteiden laskennan Timer luokasta tulevan tiedon askelten määrästä.

class OrbitCalculator(celestialObject1: CelestialObject, celestialObject2: CelestialObject, timer: Timer) {

  def calculateAllCoordinates() = {
    var allCoordinates = Buffer[(BigDecimal, BigDecimal)](celestialObject1.currentPoint)

    for (_ <- 1 to timer.amountOfSteps) {
      var (xForce, yForce) = celestialObject1.calculateForce(celestialObject2)
      celestialObject1.updateCoordinates(xForce, yForce, timer.durationOfOneStep)
      allCoordinates += celestialObject1.currentPoint

    }
    allCoordinates
  }
}
