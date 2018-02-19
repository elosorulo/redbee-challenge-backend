package challenge.backend.utils

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.user.api.ExecutionStatus

import scala.collection.JavaConverters
import scala.collection.JavaConverters._

object IOMapper {

  private final val PATH_PARAMETERS = "pathParameters"
  private final val BODY = "body"

  def getPathParameters(request: java.util.Map[String, Object]): Map[String, String] = {
    request.asScala.get(PATH_PARAMETERS) match {
      case None => Map[String, String]()
      case Some(pp) =>
        pp.asInstanceOf[java.util.LinkedHashMap[String, Object]].asScala
          .mapValues(v => v.toString).toMap[String, String]
    }
  }

  def getBody(request: java.util.Map[String, Object]): Option[String] = {
    request.asScala.get(BODY).map(v => v.toString)
  }

  def getApiGatewayResponse(executionStatus: ExecutionStatus, body: String, headers: Map[String, String]): ApiGatewayResponse = {
    ApiGatewayResponse(statusCode = executionStatus.statusCode, body = body
      , headers = JavaConverters.mapAsJavaMap(headers.mapValues(v => v.asInstanceOf[Object]))
      , base64Encoded = false)
  }
}