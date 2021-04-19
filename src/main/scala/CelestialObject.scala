package main.scala

import scala.math.pow

object CelestialObject {
  def Mars(startingPoint: (Double, Double), speed: (Double, Double), acceleration: Double) = {
    new CelestialObject("Mars", 6.29 * pow(10, 23), startingPoint, speed, acceleration, 3390)
  }
  def Sun = new CelestialObject("Sun", 1.939 * pow(10, 30), (0,0), (0,0), 0, 696340)
}

class CelestialObject(
                       val name: String,
                       val mass: Double,
                       startingPoint: (Double, Double),
                       var speed: (Double, Double),
                       var acceleration: Double,
                       val radius: Double) {

  var currentPoint: (Double, Double) = startingPoint

  def updateCoordinates(xForce: Double, yForce: Double, duration: Int) = {

    var xSpeed = speed._1 - xForce/mass * duration
    var ySpeed = speed._2 - yForce/mass * duration

    currentPoint = (currentPoint._1 + xSpeed, currentPoint._2 + ySpeed)
  }

  def calculateForce(another: CelestialObject) = {
    var g = 6.67384 * pow(10.0,-11.0)

    var xRadius = this.currentPoint._1 - another.currentPoint._1
    var yRadius = this.currentPoint._2 - another.currentPoint._2

    var xForce = (g*(this.mass * another.mass))/pow(xRadius, 2)
    var yForce = (g*(this.mass * another.mass))/pow(yRadius, 2)

    (xForce, yForce)
  }

  /*
  def getName = name

  def getMass = mass

  def getStartinPoint = startingPoint

  def getCurrentPoint = currentPoint

  def getSpeed = speed

  def getAcceleration = acceleration

  def getRadius = radius
*/
}

