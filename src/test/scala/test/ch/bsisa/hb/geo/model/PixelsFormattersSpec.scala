package test.ch.bsisa.hb.geo.model

import org.specs2.mutable._
import ch.bsisa.hb.geo.model.json.HbGeoFormatImplicits._
import play.api.libs.json.{Json, JsArray}
import ch.bsisa.hb.geo.model.{ Pixels }

/**
 * Unit tests for hb.geo.model objects conversion between scala and JSON formats.
 *
 * Tip: from sbt play console run:
 * {{{
 * test-only test.ch.bsisa.hb.geo.model.PixelsFormattersSpec
 * }}}
 * to have only the current test run, or
 *  @author Patrick Refondini
 */
class PixelsFormattersSpec extends Specification {

  val x = 460
  val y = 1500

  val pixels1 = Pixels(x,y)
  
  val expectedPixels1Js = Json.parse("""{ "x" : 460, "y" : 1500 }""")


  "HbGeoFormatImplicits " should {

    s"convert pixels1 ${pixels1} to JSON" in {

      val convertedPixels1Js =  Json.toJson(pixels1)

      
      convertedPixels1Js must be equalTo expectedPixels1Js
    }
    
    s"convert expectedPixels1Js ${expectedPixels1Js} to pixels1" in {
    
      val pixels1FromExpectedBoundsJs = expectedPixels1Js.as[Pixels]
      pixels1 must be equalTo pixels1FromExpectedBoundsJs
      
    }
    
  }

}
