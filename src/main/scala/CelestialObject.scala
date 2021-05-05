package main.scala

import scala.math.{pow,sqrt, atan, sin, cos, Pi, BigDecimal, abs}
import java.math.MathContext

  //Tämän luokan metodien on tarkoitus on palauttaa aina kappaleen seuraava ratapiste parametreiksi saamillaan tiedoilla.
  //Tämä luokka käyttää graviaatiolakia sekä Newtonin II lakia.
  //Gravitaatiolaki: F = g(m_1 * m_2)/r^2
  //jossa g on gravitaatiovakio, m_1 on satelliitin paino, m_2 planeetan tai Auringon paino ja r^2 kappaleiden etäisyys toisistaan.



class CelestialObject(
                       val name: String,
                       val mass: BigDecimal,
                       startingPoint: (BigDecimal, BigDecimal),
                       var speed: (BigDecimal, BigDecimal)) {

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

  //Metodi calculateForce palauttaa aina positiivisen tuloksen eli voiman itseisarvon. Koska Aurinko on origossa, voi gravitaatiovoima olla joskus myös negatiivinen.
  //Metodi updateCoordinates ottaa huomioon, missä kohtaa koordinaatistoa kappale on ja määrää siten voiman etumerkin.
  //Jaoin "avaruuden" neljään lohkoon, jossa voima etumerkin määrää suuntasopimus. Suuntasopimuksen mukaan positiiviset suunnat osoittavat x- ja y- koordinaattiakselien suuntaan.
  def updateCoordinates(xForce: BigDecimal, yForce: BigDecimal, duration: BigDecimal) = {
        // vasen ala neljännes
    if (currentPoint._1 <= 0 && currentPoint._2 < 0) {
      var xSpeed = speed._1 + ((xForce/mass) * duration)
      var ySpeed = speed._2 + ((yForce/mass) * duration)

      currentPoint = (currentPoint._1 - (xSpeed * duration), currentPoint._2 + (ySpeed * duration))

               // vasen ylä neljännes
    } else if (currentPoint._1 < 0 && currentPoint._2 >= 0) {
      var xSpeed = speed._1 + ((xForce/mass) * duration)
      var ySpeed = speed._2 + ((yForce/mass) * duration)

      currentPoint = (currentPoint._1 + (xSpeed * duration), currentPoint._2 + (ySpeed * duration))

                // oikea ylä neljännes
    } else if (currentPoint._1 >= 0 && currentPoint._2 > 0) {
      var xSpeed = speed._1 + ((xForce/mass) * duration)
      var ySpeed = speed._2 + ((yForce/mass) * duration)

      currentPoint = (currentPoint._1 + (xSpeed * duration), currentPoint._2 - (ySpeed * duration))

              // oikea ala neljännes
    } else if (currentPoint._1 > 0 && currentPoint._2 <= 0) {
      var xSpeed = speed._1 + ((xForce/mass) * duration)
      var ySpeed = speed._2 + ((yForce/mass) * duration)

      currentPoint = (currentPoint._1 - (xSpeed * duration), currentPoint._2 - (ySpeed * duration))

    }
  }
}

