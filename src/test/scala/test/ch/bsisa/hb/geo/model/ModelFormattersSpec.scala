package test.ch.bsisa.hb.geo.model

import org.specs2.mutable._
import ch.bsisa.hb.geo.model.json.HbGeoFormatImplicits._
import play.api.libs.json.{Json, JsArray}
import ch.bsisa.hb.geo.model.{ Coordinates, CoordinatesWithId }

/**
 * Unit tests for hb.geo.model objects conversion between scala and JSON formats.
 *
 * Tip: from sbt play console run:
 * {{{
 * test-only test.ch.bsisa.hb.geo.service.ModelFormattersSpec
 * }}}
 * to have only the current test run, or
 *  @author Patrick Refondini
 */
class ModelFormattersSpec extends Specification {

  val lv03Coord1 = Coordinates(xEastingLng = 556579.9999894347, yNorthingLat = 177849.99994494472, zAltitude = 477)
  val lv03Coord2 = Coordinates(601000.0, 197500.0, 555.0)
  val lv03Coord3 = Coordinates(556580.0, 177850.0, 477.0)
  val expectedCoord1Js = Json.parse("""{"xEastingLng":556579.9999894347,"yNorthingLat":177849.99994494472,"zAltitude":477}""")
  val expectedCoord2Js = Json.parse("""{"xEastingLng":601000.0,"yNorthingLat":197500.0,"zAltitude":555.0}""")
  val expectedCoord3Js = Json.parse("""{"xEastingLng":556580.0,"yNorthingLat":177850.0,"zAltitude":477.0}""")
  val lv03CoordsExpectedJsArray = JsArray(Seq(expectedCoord1Js, expectedCoord2Js, expectedCoord3Js))

  val lv03Coord1WithId = CoordinatesWithId("G20050614154558416", lv03Coord1 )
  val lv03Coord2WithId = CoordinatesWithId("G20160518150000321", lv03Coord2 )
  val lv03Coord3WithId = CoordinatesWithId("G20160503175908420", lv03Coord3 )
  
  val expectedCoord1WithIdJs = Json.parse("""{ "id" : "G20050614154558416", "coord" : {"xEastingLng":556579.9999894347,"yNorthingLat":177849.99994494472,"zAltitude":477} }""")
  val expectedCoord2WithIdJs = Json.parse("""{ "id" : "G20160518150000321", "coord" : {"xEastingLng":601000.0,"yNorthingLat":197500.0,"zAltitude":555.0} }""")
  val expectedCoord3WithIdJs = Json.parse("""{ "id" : "G20160503175908420", "coord" : {"xEastingLng":556580.0,"yNorthingLat":177850.0,"zAltitude":477.0} }""")  
  val lv03CoordsWithIdExpectedJsArray = JsArray(Seq(expectedCoord1WithIdJs, expectedCoord2WithIdJs, expectedCoord3WithIdJs))  
  
  val lv03CoordsList = List(lv03Coord1, lv03Coord2, lv03Coord3)
  val lv03CoordsWithIdList = List(lv03Coord1WithId, lv03Coord2WithId, lv03Coord3WithId)

  "HbGeoFormatImplicits " should {

    s"convert lv03CoordsList ${lv03CoordsList} to JSON" in {

      val lv03CoordsJsList = for { lv03Coords <- lv03CoordsList } yield { Json.toJson(lv03Coords) }
      val lv03CoordsJsArray = JsArray(lv03CoordsJsList)
      
      lv03CoordsJsArray must be equalTo lv03CoordsExpectedJsArray
    }
    
    s"convert lv03Coord1WithId ${lv03Coord1WithId} to JSON" in {
    
      val lv03Coord1WithIdJs = Json.toJson(lv03Coord1WithId)
      lv03Coord1WithIdJs must be equalTo expectedCoord1WithIdJs
      
    }
    
    s"convert lv03CoordsWithIdList ${lv03CoordsWithIdList} to JSON" in {
    
      val lv03CoordsWithIdJsList = for { lv03CoordsWithId <- lv03CoordsWithIdList } yield { Json.toJson(lv03CoordsWithId) }
      val lv03CoordsWithIdJsArray = JsArray(lv03CoordsWithIdJsList)
      
      lv03CoordsWithIdJsArray must be equalTo lv03CoordsWithIdExpectedJsArray
      
    }
    
    s"convert JSON to lv03Coord1" in {
    
      val lv03Coord1Converted = expectedCoord1Js.as[Coordinates] // .as[Coordinates] 
      lv03Coord1Converted must be equalTo lv03Coord1
      
    }    
    
    s"convert JSON to lv03Coord1WithId " in {
    
      val lv03Coord1WithIdConverted = expectedCoord1WithIdJs.as[CoordinatesWithId]
      lv03Coord1WithIdConverted must be equalTo lv03Coord1WithId
      
    }    
    
    s"convert JSON array  of coordinates with id to a List of coordinates with id" in {
    
      val lv03CoordsWithIdListConverted = for { lv03CoordsWithIdJs <- lv03CoordsWithIdExpectedJsArray.value } yield { lv03CoordsWithIdJs.as[CoordinatesWithId] }
      lv03CoordsWithIdListConverted must be equalTo lv03CoordsWithIdList
      
    }    
    
  }

}
