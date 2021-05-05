package main.scala
import scala.math._
import java.io.{BufferedReader, FileReader}
import scala.Console.{RESET, UNDERLINED}
import scala.io.StdIn.readLine

object Simulator {

  // Muuttuja asetettu nulliksi, jotta se voidaan täyttää myöhemmin.
  var Mercury: CelestialObject = null
  var Venus: CelestialObject = null
  var Earth: CelestialObject = null
  var Mars: CelestialObject = null
  var Jupiter: CelestialObject = null
  var Saturn: CelestialObject = null
  var Uranus: CelestialObject = null
  var Neptune: CelestialObject = null
  var Sun: CelestialObject = null

  // Planeettojen tietojen haku asetustiedosta ja tallentaminen muuttujiin käyttäen BufferedReaderia.
  val lineReader = new BufferedReader(new FileReader("resources/planetInfo.txt"))
  var currentline: String = null

  while ({currentline = lineReader.readLine(); currentline != null}) {
    if (currentline.startsWith("#")) {

      try {

        val parts = currentline.drop(1).split(';').map(_.trim)
        val tmp = new CelestialObject(parts(0), BigDecimal(parts(1)), (BigDecimal(parts(2)), BigDecimal(parts(3))), (BigDecimal(parts(4)), BigDecimal(parts(5))))

        parts(0) match {
          case "Mercury" => Mercury = tmp
          case "Venus" => Venus = tmp
          case "Earth" => Earth = tmp
          case "Mars" => Mars = tmp
          case "Jupiter" => Jupiter = tmp
          case "Saturn" => Saturn = tmp
          case "Uranus" => Uranus = tmp
          case "Neptune" => Neptune = tmp
          case "Sun" => Sun = tmp
        }
      } catch {
        case _ => throw new InvalidPlanetInfoException("Asetustiedostoa luettaessa tapahtui virhe.")
      }
    }
  }

  // Käyttäjän syötteen tallentaminen satelliitin muuttujaan ja timeriin.
  def simulate(inputInfo: String) = {

    var satellite: CelestialObject = null
    var time: Timer = null

    try {

      val info = inputInfo.split(';').map(_.trim).map(_.replace(" ", ""))

      if (BigDecimal(info(0)) <= 0) throw new InvalidInputException("Virheellinen syöte. Massan tulee olla suurempi kuin nolla.")

      satellite = new CelestialObject("Satellite", BigDecimal(info(0)), (BigDecimal(info(1)) * 1000, 0), (BigDecimal(info(2))*cos(Pi/4)*1000, BigDecimal(info(2))*sin(Pi/4)*1000))
      time = Timer(info(3).toInt, info(4).toInt)

    } catch {
      case e: ArrayIndexOutOfBoundsException => throw new InvalidInputException("Tarkista, että syötteessä on oikea määrä parametrejä.")
      case e: NumberFormatException => throw new InvalidInputException("Tarkista, että numerot ovat oikeassa formaatissa ja erisuuria kuin nolla. Syöte ei saa sisältää kirjaimia tai muita merkkjä kuin puolipisteen.")
      case InvalidInputException(m) => throw new InvalidInputException(m)
      case _ => throw new InvalidInputException("Virheellinen syöte. Tarkista, että antamasi arvot ovat suurempia kuin nolla.")
    }

    //Ratapisteiden lasku
    val satelliteOrbit = new OrbitCalculator(satellite, Sun, time)
    val mercuryOrbit = new OrbitCalculator(Mercury, Sun, time)
    val venusOrbit = new OrbitCalculator(Venus, Sun, time)
    val earthOrbit = new OrbitCalculator(Earth, Sun, time)
    val marsOrbit = new OrbitCalculator(Mars, Sun, time)
    val jupiterOrbit = new OrbitCalculator(Jupiter, Sun, time)
    val saturnOrbit = new OrbitCalculator(Saturn, Sun, time)
    val uranusOrbit = new OrbitCalculator(Uranus, Sun, time)
    val neptuneOrbit = new OrbitCalculator(Neptune, Sun, time)


    val satellitePoints = satelliteOrbit.calculateAllCoordinates()
    val mercuryPoints = mercuryOrbit.calculateAllCoordinates()
    val venusPoints = venusOrbit.calculateAllCoordinates()
    val earthPoints = earthOrbit.calculateAllCoordinates()
    val marsPoints = marsOrbit.calculateAllCoordinates()
    val jupiterPoints = jupiterOrbit.calculateAllCoordinates()
    val saturnPoints = saturnOrbit.calculateAllCoordinates()
    val uranusPoints = uranusOrbit.calculateAllCoordinates()
    val neptunePoints = neptuneOrbit.calculateAllCoordinates()


    //Tuloste ohjelman tulosteen alkuun.
    println("="*100)
    println("START")
    println("="*100)
    println("Arvot ovat kilometreissä ja ne kuvaavat x- ja y-koordinaattia, kun Aurinko on origossa.\n\n")
    println(s"${UNDERLINED}Satelliitin ratapisteet:${RESET}")
    satellitePoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)


    print(s"\n\n\n${UNDERLINED}Merkuriuksen ratapisteet:${RESET}\n")
    mercuryPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Venuksen ratapisteet:${RESET}\n")
    venusPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Maan ratapisteet:${RESET}\n")
    earthPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Marsin ratapisteet:${RESET}\n")
    marsPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Jupiterin ratapisteet:${RESET}\n")
    jupiterPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Saturnuksen ratapisteet:${RESET}\n")
    saturnPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Uranuksen ratapisteet:${RESET}\n")
    uranusPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

    print(s"\n\n\n${UNDERLINED}Neptunuksen ratapisteet:${RESET}\n")
    neptunePoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(println)

  }
}

case class InvalidInputException(message: String) extends Exception(message)

case class InvalidPlanetInfoException(message: String) extends Exception(message)