package main.scala

  //In Finnish: Tähän luokkaan tallentuu käyttäjän syötteestä tieto kuinka pitkä simulaatiojakso halutaan ja kuinka lyhyt yksi simulaatioaskel on.
  //Tiedoilla saadaan selvitettyä simulaatio askelten määrä, jota tarvitaan OrbitCalculator luokassa.

  // In ENglish: In this class the program calculates the number of orbit points using the last two parameters of user input. They represent simulation step and simulation period.
  // They number of steps is found by dividing the value of simulation period by value of simulation step.

class Timer(val durationOfOneStep: Int, val simulationPeriod: Int) {

  // number of steps = duration of simulaation period / duration of one simulaation step
  val amountOfSteps = simulationPeriod / durationOfOneStep
}

object Timer {
  def apply(oneStepInHours: Int, periodInDays: Int) = {
    new Timer(oneStepInHours * 60 * 60, periodInDays * 24 * 60 * 60)
  }
}