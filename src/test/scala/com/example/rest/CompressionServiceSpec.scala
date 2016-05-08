package com.example.rest

import scala.concurrent.future
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FreeSpec
import org.scalatest.Matchers
import spray.http.StatusCodes._
import spray.testkit.ScalatestRouteTest
import akka.actor.ActorSystem
import scala.concurrent.duration.`package`.DurationInt
import concurrent.duration._
import akka.testkit._
import spray.http.HttpBody
import spray.http.MediaTypes
import spray.http.HttpEntity

/**
 * @author fidato
 */
class CompressionServiceSpec extends FreeSpec with CompressionApiServiceMain with ScalatestRouteTest with Matchers {
  def actorRefFactory = system

  implicit def default(implicit system: ActorSystem) = RouteTestTimeout(2.second dilated)

  "The Compression Service" - {
    "when calling GET api/0" - {
      "should return '<empty>'" in {
        Get("/api/0") ~> compressionApiRoute ~> check {
          status should equal(OK)
          entity.toString should include("")
        }
      }
    }
  }

  "The Compression Service" - {
    "when calling GET api/list" - {
      "should return '<empty>'" in {
        Get("/api/list") ~> compressionApiRoute ~> check {
          status should equal(OK)
          entity.toString should include("")
        }
      }
    }
  }

  "The Compression Service" - {
    "when calling GET api/add" - {
      "should return '<empty>'" in {
        Post("/api/add",
          HttpEntity(MediaTypes.`text/plain`,
            ("""44
44
55
66""").getBytes)) ~> compressionApiRoute ~> check {
            status should equal(OK)
            entity.toString should include("")
          }
      }
    }
  }

  "The Compression Service" - {
    "when calling GET api/list" - {
      "should return '442'" in {
        Get("/api/list") ~> compressionApiRoute ~> check {
          status should equal(OK)
          entity.toString should include("442")
        }
      }
    }
  }

  "The Compression Service" - {
    "when calling GET api/2" - {
      "should return '55'" in {
        Get("/api/2") ~> compressionApiRoute ~> check {
          status should equal(OK)
          entity.toString should include("55")
        }
      }
    }
  }

}