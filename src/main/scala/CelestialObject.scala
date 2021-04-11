package main.scala

class CelestialObject(name: String, mass: Double, startingPoint: (Double, Double), currentPoint: (Double, Double), speed: Double, acceleration: Double, radius: Double) {

  def getName = name

  def getMass = mass

  def getStartinPoint = startingPoint

  def getCurrentPoint = currentPoint

  def getSpeed = speed

  def getAcceleration = acceleration

  def getRadius = radius

}

