package test.ch.bsisa.hb.geo.model

import org.specs2.mutable._
import ch.bsisa.hb.geo.model.json.HbGeoFormatImplicits._
import play.api.libs.json.{Json, JsArray}
import ch.bsisa.hb.geo.model.{ Bounds, Coordinates }

/**
 * Unit tests for hb.geo.model objects conversion between scala and JSON formats.
 *
 * Tip: from sbt play console run:
 * {{{
 * test-only test.ch.bsisa.hb.geo.model.BoundsFormattersSpec
 * }}}
 * to have only the current test run, or
 *  @author Patrick Refondini
 */
class BoundsFormattersSpec extends Specification {

  val lv03Coord1 = Coordinates(xEastingLng = 556579.9999894347, yNorthingLat = 177849.99994494472, zAltitude = 477)
  val lv03Coord2 = Coordinates(601000.0, 197500.0, 555.0)

  val bounds1 = Bounds(lv03Coord1, lv03Coord2)
  
  val expectedBounds1Js = Json.parse("""{ 
    "swCoord" : {"xEastingLng":556579.9999894347,"yNorthingLat":177849.99994494472,"zAltitude":477},
    "neCoord" : {"xEastingLng":601000.0,"yNorthingLat":197500.0,"zAltitude":555.0} }""")


  "HbGeoFormatImplicits " should {

    s"convert bounds1 ${bounds1} to JSON" in {

      val bounds1Js =  Json.toJson(bounds1)

      
      bounds1Js must be equalTo expectedBounds1Js
    }
    
    s"convert expectedBounds1Js ${expectedBounds1Js} to bounds1" in {
    
      val boundsFromExpectedBoundsJs = expectedBounds1Js.as[Bounds]
      bounds1 must be equalTo boundsFromExpectedBoundsJs
      
    }
    
  }

}
