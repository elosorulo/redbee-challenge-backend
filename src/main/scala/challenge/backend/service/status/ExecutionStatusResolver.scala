package challenge.backend.service.status

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.api.{BackendServiceException, ErrorResponse, ExecutionStatus, ExecutionTypes}
import challenge.backend.log.Logger
import io.circe.generic.auto._
import io.circe.syntax._

import scala.collection.JavaConverters

object ExecutionStatusResolver extends ExecutionTypes {
  def resolveStatus(body: => ApiGatewayResponse): ApiGatewayResponse = {
    try {
      body
    } catch {
      case e: BackendServiceException => checkStatus(e)
      case e: Exception =>
        Logger.error(e.getMessage)
        ApiGatewayResponse(
          statusCode = 503,
          body = ErrorResponse(
            executionStatus = ExecutionStatus(
              statusCode = 1000,
              message = Some("An unknown error has occured."),
              executionType = UNKNOWN_ERROR)).asJson.noSpaces,
          headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty),
          base64Encoded = false)
    }
  }

  private def checkStatus(exception: BackendServiceException): ApiGatewayResponse = {
    val errorType: String = exception.errorCode match {
      //Invalid body
      case 1006 => INVALID_INPUT_ERROR
      //Serialization error
      case 1001 => INVALID_INPUT_ERROR
      //Invalid path Parameters
      case 1002 => INVALID_INPUT_ERROR
      //DynamoDB Connection error
      case 1003 => INTERNAL_ERROR
      //DynamoDB Element not found
      case 1004 => BUSINESS_ERROR
      //Serialization error
      case 1005 => BUSINESS_ERROR
      case _ => {
        INTERNAL_ERROR
      }
    }
    ApiGatewayResponse(
      statusCode = 503,
      body = ErrorResponse(ExecutionStatus(
          statusCode = exception.errorCode,
          message = Some(exception.message),
          executionType = errorType)).asJson.noSpaces,
      headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty),
      base64Encoded = false)
  }
}