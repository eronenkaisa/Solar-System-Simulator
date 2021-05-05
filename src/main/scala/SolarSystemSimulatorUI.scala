package main.scala

import scala.Console.{RESET, UNDERLINED}
import scala.io.StdIn.readLine

object SolarSystemSimulatorUI extends App {

  //Tervetuloa-viesti, jossa ohjeet simulaattorin käyttöön.
  private def run() = {
    println(s"${UNDERLINED}AURINKOKUNTASIMULAATTORI")
    println(s"${RESET}Syötä satelliitille arvot massa, alkupiste ja ratanopeus sekä simulaatiojakson pituus ja -askeleen pituus puolipisteellä erotettuna ilman yksiköitä.\n" +
      "Simulaattori laskee ratapisteet Auringon ympäri satelliitillesi sekä muille aurinkokuntamme planeetoille.\n\n")
    println(s"${UNDERLINED}Ideaalit suuruudet satelliitille:")
    println(s"${RESET}Massa: 2 000 kg - 5000 kg\nAlkupiste (Etäisyys Auringosta): 10 000 000 km - 1 000 000 000 km\n" +
      "Ratanopeus: 20 - 30 km/s\n\n")
    println(s"${UNDERLINED}Ideaalit suuruudet simulaatiolle:")
    println(s"${RESET}Simulaatioaskeleen pituus: 12 - 24h\nSimulaatiojakson pituus: 5d - 10d \n\n" +
      "Esimerkki syöte: 4 500; 18 500 000; 25; 17; 10\n")

    val input = readLine("Syötä arvot:")

    try {
      Simulator.simulate(input)
    } catch {
      case InvalidInputException(message) => println(message)
      case InvalidPlanetInfoException(message) => println(message)
    }
  }

  this.run()

}
