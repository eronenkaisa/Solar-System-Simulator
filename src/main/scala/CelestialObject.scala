package main.scala

import scala.math.{sin, cos, Pi, BigDecimal}


  //In Finnish:
  //Tämän luokan metodien on tarkoitus on palauttaa aina kappaleen seuraava ratapiste parametreiksi saamillaan tiedoilla.
  //Tämä luokka käyttää kaavoja x(t) = Rcos(w*t+fii) ja y(t) = R*sin(w*t+fii)
  //jossa R on etäisyys Aurigosta (km), w on kulmanopeus (rad/s) ja fii on aika.



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

