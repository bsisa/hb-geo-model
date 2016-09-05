package test.ch.bsisa.hb.geo.model

import org.specs2.mutable._
import ch.bsisa.hb.geo.model.json.HbGeoFormatImplicits._
import play.api.libs.json.{ Json, JsArray }
import ch.bsisa.hb.geo.model._

/**
 * Unit tests for hb.geo.model objects conversion between scala and JSON formats.
 *
 * Tip: from sbt play console run:
 * {{{
 * test-only test.ch.bsisa.hb.geo.model.BoundsRequestFormattersSpec
 * }}}
 * to have only the current test run
 *  @author Patrick Refondini
 */
class BoundsRequestFormattersSpec extends Specification {

  val rasterImgSize = Pixels(60, 40)
  val record1 = PixelsCoordinates(Pixels(20, 20), Coordinates(500000, 200000))
  val record2 = PixelsCoordinates(Pixels(45, 30), Coordinates(500010, 199980))
  val boundsRequest1 = BoundsRequest(rasterImgSize, record1, record2)

  val expectedBoundsRequest1Js = Json.parse("""{
    "rasterImgSize" : {
        "x" : 60,
        "y" : 40
    },
    "record1" : {
      "pixels" : {
         "x" : 20,
   		   "y" : 20
      },
      "coord" : {"xEastingLng":500000,"yNorthingLat":200000,"zAltitude":500}
    },
    "record2" : {
      "pixels" : {
         "x" : 45,
   		   "y" : 30
      },
      "coord" : {"xEastingLng":500010,"yNorthingLat":199980,"zAltitude":500}
    }
 }""")

  "HbGeoFormatImplicits " should {

    s"convert boundsRequest1 ${boundsRequest1} to JSON" in {

      val boundsRequest1Js = Json.toJson(boundsRequest1)

      boundsRequest1Js must be equalTo expectedBoundsRequest1Js
    }

    s"convert expectedBoundsRequest1Js ${expectedBoundsRequest1Js} to boundsRequest1" in {

      val boundsRequestFromExpectedBoundsJs = expectedBoundsRequest1Js.as[BoundsRequest]
      boundsRequest1 must be equalTo boundsRequestFromExpectedBoundsJs

    }

  }

}
