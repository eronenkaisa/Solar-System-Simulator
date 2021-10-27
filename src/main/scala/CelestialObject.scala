package main.scala

import scala.math.{sin, cos, Pi, BigDecimal}


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
                        var angularSpeed: BigDecimal) /*(rad/s)*/ {

  var currentPoint: (BigDecimal, BigDecimal) = startingPoint


  def calculateNextCoordinate(time: Int) = {
    //radius*cos(angularSpeed*time + fii)
    var fii = Pi/4
    var nextXCoordinate = startingPoint._1 * cos(angularSpeed.toDouble * time + fii)
    var nextYCoordinate = startingPoint._2 * sin(angularSpeed.toDouble * time + fii)

    (nextXCoordinate, nextYCoordinate)
  }

}

