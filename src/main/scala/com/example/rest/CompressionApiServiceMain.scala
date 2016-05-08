package com.example.rest

import akka.actor.{ Actor, Props }
import akka.event.Logging
import spray.routing._
import spray.http._
import MediaTypes._
import spray.httpx.unmarshalling.Unmarshaller
import com.example.compression.CompressedIns
import com.example.compression.CompressedSeq
import spray.httpx.marshalling.Marshaller
import com.example.compression.CompressedSeq
import com.example.compression.Compressed
import spray.util.LoggingContext

trait CompressionApiServiceMain extends HttpService {
  
  val compressionApiRoute =
    pathPrefix("api") {
      path("add") {
        respondWithMediaType(MediaTypes.`text/plain`) {

          post {
            entity(as[String]) { stringParam =>
              complete {
                CompressedIns(stringParam)
                ""
              }
            }
          }
        }
      } ~
        path(IntNumber) { index =>
          respondWithMediaType(MediaTypes.`text/plain`) {
            get {
              complete {
                if(CompressedIns.currentIns.size > index)
                CompressedIns.currentIns(index)
                else "Index is out of Range."
              }
            }
          }

        } ~
        path("list") {
          respondWithMediaType(MediaTypes.`text/plain`) {

            get {
              complete {
                val seqComp: Seq[Compressed[String]] = CompressedIns.currentIns.underlying

                CompressedIns.formatResponse(seqComp)

              }
            }
          }

        }

    }
}

class CompressionApiServiceActor extends Actor with CompressionApiServiceMain {
  

  def actorRefFactory = context

  def receive = runRoute(compressionApiRoute)
}