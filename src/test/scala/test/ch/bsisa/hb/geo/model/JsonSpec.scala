package test.ch.bsisa.hb.geo.model

import org.specs2.mutable._

//import ch.bsisa.hb.geo.service.{ SwissCoordinates }
import ch.bsisa.hb.geo.model.Coordinates
import ch.bsisa.hb.geo.model.json.HbGeoFormatImplicits._
import play.api.libs.json.Json
import play.api.libs.json.JsArray

/**
 * Tip: from sbt play console run:
 * {{{
 * test-only test.ch.bsisa.hb.geo.model.JsonSpec
 * }}}
 * to have only the current test run.
 *
 *  @author Patrick Refondini
 */
class JsonSpec extends Specification {

  val lv03Coord1 = Coordinates(xEastingLng = 556579.9999894347, yNorthingLat = 177849.99994494472, zAltitude = 477)
  val lv03Coord2 = Coordinates(601000.0, 197500.0, 555.0)
  val lv03Coord3 = Coordinates(556580.0, 177850.0, 477.0)
  val expectedCoord1Js = Json.parse("""{"xEastingLng":556579.9999894347,"yNorthingLat":177849.99994494472,"zAltitude":477}""")
  
  val lv03Coords = List(lv03Coord1,lv03Coord2,lv03Coord3)
  
  //val test = JsArray.apply(Json.toJson)
  val expectedLv03CoordsJsArray = Json.parse(
    """[
      {"xEastingLng":556579.9999894347,"yNorthingLat":177849.99994494472,"zAltitude":477},
      {"xEastingLng":601000,"yNorthingLat":197500,"zAltitude":555},
      {"xEastingLng":556580,"yNorthingLat":177850,"zAltitude":477}
      ]""")

  s"Swiss coordinates ${lv03Coord1} to JSON " should {

    s"produce ${expectedCoord1Js}" in {
      Json.toJson(lv03Coord1) mustEqual expectedCoord1Js
    }

  }
  
  s"Swiss coordinates list ${lv03Coords} to JSON " should {

    s"produce ${expectedLv03CoordsJsArray}" in {
      Json.toJson(lv03Coords) mustEqual expectedLv03CoordsJsArray
    }

  }  
  
  


}