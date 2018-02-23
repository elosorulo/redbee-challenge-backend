package challenge.backend.service.input

import challenge.backend.api._
import challenge.backend.log.Logger
import io.circe

object JsonUtils {

  def handleDecoding[T](body: => Either[circe.Error, T]): T = {
     body match {
      case Left(v) =>
      Logger.error(v.getCause.getMessage)
      throw BackendServiceException(1006, s"Deserialization error.", v.getCause)
    case Right(v) => v
    }
  }
}