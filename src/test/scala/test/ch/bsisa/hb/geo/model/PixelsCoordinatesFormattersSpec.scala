package test.ch.bsisa.hb.geo.model

import org.specs2.mutable._
import ch.bsisa.hb.geo.model.json.HbGeoFormatImplicits._
import play.api.libs.json.{Json, JsArray}
import ch.bsisa.hb.geo.model.{ Pixels, Coordinates, PixelsCoordinates }

/**
 * Unit tests for hb.geo.model objects conversion between scala and JSON formats.
 *
 * Tip: from sbt play console run:
 * {{{
 * test-only test.ch.bsisa.hb.geo.model.PixelsCoordinatesFormattersSpec
 * }}}
 * to have only the current test run, or
 *  @author Patrick Refondini
 */
class PixelsCoordinatesFormattersSpec extends Specification {

  val x = 460
  val y = 1500

  val pixels1 = Pixels(x,y)
  val lv03Coord1 = Coordinates(xEastingLng = 556579.9999894347, yNorthingLat = 177849.99994494472, zAltitude = 477)
  val pixelsCoord1 = PixelsCoordinates(pixels1, lv03Coord1)
  
  val expectedPixelsCoord1Js = Json.parse("""{ 
    "pixels" : { "x" : 460, "y" : 1500 },
    "coord" : {"xEastingLng":556579.9999894347,"yNorthingLat":177849.99994494472,"zAltitude":477} }""")


  "HbGeoFormatImplicits " should {

    s"convert pixelsCoord1 ${pixelsCoord1} to JSON" in {

      val convertedPixelsCoord1Js =  Json.toJson(pixelsCoord1)

      
      convertedPixelsCoord1Js must be equalTo expectedPixelsCoord1Js
    }
    
    s"convert expectedPixelsCoord1Js ${expectedPixelsCoord1Js} to pixelsCoord1" in {
    
      val pixels1FromExpectedBoundsJs = expectedPixelsCoord1Js.as[PixelsCoordinates]
      pixelsCoord1 must be equalTo pixels1FromExpectedBoundsJs
      
    }
    
  }

}
