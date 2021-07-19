package main.scala

import scala.math.{pow,sqrt, atan, sin, cos, Pi, BigDecimal, abs}
import java.math.MathContext


  //In Finnish:
  //Tämän luokan metodien on tarkoitus on palauttaa aina kappaleen seuraava ratapiste parametreiksi saamillaan tiedoilla.
  //Tämä luokka käyttää graviaatiolakia sekä Newtonin II lakia.
  //Gravitaatiolaki: F = g(m_1 * m_2)/r^2
  //jossa g on gravitaatiovakio, m_1 on satelliitin paino, m_2 planeetan tai Auringon paino ja r kappaleiden etäisyys toisistaan.

  //In English:
  //This class is meant to return the next orbit point of a planet/satellite. This class uses the second law of Newton F=ma and the formula of gravitational force F = g(m_1 * m_2)/(r^2)
  //where g is the gravitational constant, m_1 is the mass of the planet/satellite, m_2 is the mass of the Sun and r is the distance between those two objects.


class CelestialObject(
                       val name: String,
                       val mass: BigDecimal, //(kg)
                       startingPoint: (BigDecimal, BigDecimal), //m
                       var speed: (BigDecimal, BigDecimal))  { //(m/s)//

  var currentPoint: (BigDecimal, BigDecimal) = startingPoint


  def calculateForce(another: CelestialObject) = {
    val g: BigDecimal = 6.67384 * pow(10.0,-11.0)

    val x1 = this.currentPoint._1
    val y1 = this.currentPoint._2
    val x2 = another.currentPoint._1
    val y2 = another.currentPoint._2

    var xDistance = if (x1 > x2) x1 - x2 else x2 - x1
    var yDistance = if (y1 > y2) y1 - y2 else y2 - y1

    val xForce: BigDecimal = if (x1 == x2) 0 else (g * (this.mass * another.mass)) / (xDistance * xDistance)
    val yForce: BigDecimal = if (y1 == y2) 0 else (g * (this.mass * another.mass)) / (yDistance * yDistance)

    (xForce, yForce)
  }

  //In Finnish:
  //Metodi calculateForce palauttaa aina positiivisen tuloksen eli voiman itseisarvon. Koska Aurinko on origossa, voi gravitaatiovoima olla joskus myös negatiivinen.
  //Metodi updateCoordinates ottaa huomioon, missä kohtaa koordinaatistoa kappale on ja määrää siten voiman etumerkin.
  //Jaoin "avaruuden" neljään lohkoon, jossa voima etumerkin määrää suuntasopimus. Suuntasopimuksen mukaan positiiviset suunnat osoittavat x- ja y- koordinaattiakselien suuntaan.

  //In English:
  //Method calculateForce always returns absolute value of the force between the Sun and the satelite/planet. Since the Sun is in (0,0) gravitational force can also be negative according to
  //declaration that says positive directions are to right and up along the axis from (0,0). Method updateCoordinates takes planets/satelites previous orbit point into consideration when calculating
  //the sign of the force.
  //The space is divided into four quarters where the conclusive impact of the force to the coordinates is different in every one of the them.


  def updateCoordinates(xForce: BigDecimal, yForce: BigDecimal, duration: BigDecimal) = {
    val xSpeed = speed._1 + (((xForce/mass) * duration))
    val ySpeed = speed._2 + (((yForce/mass) * duration))

        // left hand side lower quarter
    if (currentPoint._1 <= 0 && currentPoint._2 < 0) {

      currentPoint = (currentPoint._1 - (xSpeed * duration), currentPoint._2 + (ySpeed * duration))

               // left hand side upper quarter
    } else if (currentPoint._1 < 0 && currentPoint._2 >= 0) {

      currentPoint = (currentPoint._1 + (xSpeed * duration), currentPoint._2 + (ySpeed * duration))

                // right hand sode upper quarter
    } else if (currentPoint._1 >= 0 && currentPoint._2 > 0) {

      currentPoint = (currentPoint._1 + (xSpeed * duration), currentPoint._2 - (ySpeed * duration))

              // right hand side lower quarter
    } else if (currentPoint._1 > 0 && currentPoint._2 <= 0) {

      currentPoint = (currentPoint._1 - (xSpeed * duration), currentPoint._2 - (ySpeed * duration))

    }
  }
}

