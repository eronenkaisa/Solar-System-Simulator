package main.scala
import scala.collection.mutable.Buffer
import scala.math._

class OrbitCalculator(celestialObject1: CelestialObject, celestialObject2: CelestialObject, timer: Timer) {

  //Gravitaatiolaki: F = g(m_1 * m_2)/r^2
  //jossa g on gravitaatiovakio, m_1 on sateliitin paino, m_2 planeetan tai Auringon paino ja r^2 kappaleiden etäisyys toisistaan.

  //Tämän luokan on taroitus tulostaa kokoelma, jossa on alkiona sateliitin paikkakoordinaatteja.
  //Yhden nopeusvektorin suuruus on 1000km/h. (arvio)



  def calculateAllCoordinates() = {
    var allCoordinates = Buffer[(Double, Double)](celestialObject1.currentPoint)

    for (_ <- 1 to timer.amountOfSteps) {
      var (xForce, yForce) = celestialObject1.calculateForce(celestialObject2)
      celestialObject1.updateCoordinates(xForce, yForce, timer.durationOfOneStep)
      allCoordinates += celestialObject1.currentPoint

    }

    allCoordinates
  }









  def isColliding: Boolean = ???


}
