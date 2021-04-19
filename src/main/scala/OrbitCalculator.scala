package main.scala
import scala.math._

class OrbitCalculator(celestialObject: CelestialObject) {

  //Gravitaatiolaki: F = g(m_1 * m_2)/r^2
  //jossa g on gravitaatiovakio, m_1 on sateliitin paino, m_2 planeetan tai Auringon paino ja r^2 kappaleiden etäisyys toisistaan.

  def calculateForce(celestialObject1: CelestialObject, celestialObject2: CelestialObject) = {
    var g = 6.67384 * pow(10.0,-11.0)

    var xRadius = celestialObject1.getCurrentPoint._1 - celestialObject2.getCurrentPoint._1
    var yRadius = celestialObject1.getCurrentPoint._2 - celestialObject2.getCurrentPoint._2

    var xForce = (g*(celestialObject1.getMass * celestialObject2.getMass))/pow(xRadius, 2)
    var yForce = (g*(celestialObject1.getMass * celestialObject2.getMass))/pow(yRadius, 2)

    var resultForce = (xForce, yForce)


  def calculateNextSpeedVector(celestialObject: CelestialObject) = {

    var currentXSpeed = celestialObject.getSpeed._1
    var currentYSpeed = celestialObject.getSpeed._2

    var nextXSpeed = currentXSpeed - resultForce._1/celestialObject.getMass * ??? //Näihin timer luokasta ajan muutos
    var nextYSpeed = currentYSpeed - resultForce._2/celestialObject.getMass * ???
  }
  }
  def calculateNextCoordinates(celestialObject: CelestialObject) = {
    var currentXCoordinate = celestialObject.getCurrentPoint._1
    var currentYCoordinate = celestialObject.getCurrentPoint._2


  }

  def isColliding: Boolean = ???


}
