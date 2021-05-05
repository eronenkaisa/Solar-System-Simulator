package main.scala

  //Tähän luokkaan tallentuu käyttäjän syötteestä tieto kuinka pitkä simulaatiojakso halutaan ja kuinka lyhyt yksi simulaatioaskel on.
  //Tiedoilla saadaan selvitettyä simulaatio askelten määrä, jota tarvitaan OrbitCalculator luokassa.

class Timer(val durationOfOneStep: Int, val simulationPeriod: Int) {

  // askeleiden määrä = simulaatiojakson pituus / simulaatioaskelen pituus
  val amountOfSteps = simulationPeriod / durationOfOneStep
}

object Timer {
  def apply(oneStepInHours: Int, periodInDays: Int) = {
    new Timer(oneStepInHours * 60 * 60, periodInDays * 24 * 60 * 60)
  }
}