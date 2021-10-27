package main.scala
import scala.math._
import java.io.{BufferedReader, FileReader}
import scala.Console.{RESET, UNDERLINED}

object Simulator {

  // Muuttuja asetettu nulliksi, jotta se voidaan täyttää myöhemmin.
  // The variables are set to null so that it can be changed later.
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
  // Retrieving the data of the planetes from configuration file planetInfo.txt using BufferedReader.

  val lineReader = new BufferedReader(new FileReader("resources/planetInfo.txt"))
  var currentline: String = null

  while ({currentline = lineReader.readLine(); currentline != null}) {
    if (currentline.startsWith("#")) {

      try {

        val parts = currentline.drop(1).split(';').map(_.trim)
        val tmp = new CelestialObject(parts(0), BigDecimal(parts(1)), (BigDecimal(parts(2)), BigDecimal(parts(3))), BigDecimal(parts(4)))

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
        case _ => throw new InvalidPlanetInfoException("Error while reading the configuration file.")
      }
    }
  }

  // Käyttäjän syötteen tallentaminen satelliitin muuttujaan ja timeriin.
  // User input is saved into variable var satellite and var time.
  def simulate(inputInfo: String) = {

    var satellite: CelestialObject = null
    var time: Timer = null

    try {

      val info = inputInfo.split(';').map(_.trim).map(_.replace(" ", ""))

      if (BigDecimal(info(0)) <= 0) throw new InvalidInputException("Incorrect input. The mass has to be bigger than zero.")
                                                   //mass (kg)          distance (m)                     speed (m/s)
      satellite = new CelestialObject("Satellite", BigDecimal(info(0)), (BigDecimal(info(1)) * 1000, BigDecimal(info(1)) * 1000), BigDecimal(info(2)))
      time = Timer(info(3).toInt, info(4).toInt)

    } catch {
      case e: ArrayIndexOutOfBoundsException => throw new InvalidInputException("Incorrect input. Check that your input has the right number of parameters.")
      case e: NumberFormatException => throw new InvalidInputException("Incorrect input. Check that your numbers are in right format and that they are not zero. The input must not include letters or any other separators than semicolon (;).")
      case InvalidInputException(m) => throw new InvalidInputException(m)
      case _ => throw new InvalidInputException("Incorrect input. Check that your values are bigger than zero.")
    }

    // Calculating orbitpoints.
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


    //Basic info which appears in the beginning of the output as explanation for the results.
    println("="*100)
    println("START")
    println("="*100)
    println("These values are in kilometers and they represent x and y coordinates when the Sun is in (0,0).\n\n")
    println(s"${UNDERLINED}# Satellites orbit points: #${RESET}")
    satellitePoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))


    print(s"\n\n${UNDERLINED}# Mercury orbit points: #${RESET}\n")
    mercuryPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Venus orbit points: #${RESET}\n")
    venusPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Earth orbit points: # ${RESET}\n")
    earthPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Mars orbit points: #${RESET}\n")
    marsPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Jupiter orbit points: #${RESET}\n")
    jupiterPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Saturn orbit points: #${RESET}\n")
    saturnPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Uranus orbit points: #${RESET}\n")
    uranusPoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

    print(s"\n\n${UNDERLINED}# Neptune orbit points: #${RESET}\n")
    neptunePoints.map(p => (p._1.longValue -> p._2.longValue)).foreach(p => println(s"${p._1} ${p._2}"))

  }
}

case class InvalidInputException(message: String) extends Exception(message)

case class InvalidPlanetInfoException(message: String) extends Exception(message)