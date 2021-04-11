package main.scala
import scala.math._

class OrbitCalculator(celestialObject: CelestialObject) {

  //Gravitaatiolaki: F = g(m_1 * m_2)/r^2
  //jossa g on gravitaatiovakio, m_1 on sateliitin paino, m_2 planeetan tai Auringon paino ja r^2 kappaleiden etäisyys toisistaan.


  def calculateForce(celestialObject1: CelestialObject, celestialObject2: CelestialObject, r: Double) = {

    var g = 6.67384 * pow(10.0,-11.0)

    var result = (g*(celestialObject1.getMass * celestialObject2.getMass))/pow(r, 2)

    result
  }

  def calculatePosition(celestialObject: CelestialObject) = ???

  def isColliding: Boolean = ???


}
