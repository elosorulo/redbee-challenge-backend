package challenge.backend.service.io

import challenge.backend.api._
import challenge.backend.log.Logger
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

object JsonUtils {

  def createUserOperationResponseToJson(createUserOperationResponse: CreateUserOperationResponse): String = {
    createUserOperationResponse.asJson.noSpaces
  }

  def interestsOperationResponseToJson(interestsOperationResponse: InterestsOperationResponse): String = {
    interestsOperationResponse.asJson.noSpaces
  }

  def errorResponseToJson(errorResponse: ErrorResponse): String = {
    errorResponse.asJson.noSpaces
  }

  def userInterestsDtoToJson(userInterests: UserInterestsDto): String = {
    userInterests.asJson.noSpaces
  }

  def userDtoToJson(userDto: UserDto): String = {
    userDto.asJson.noSpaces
  }

  def jsonStringToCreateUserOperationResponse(value: String): CreateUserOperationResponse = {
    decode[CreateUserOperationResponse](value) match {
      case Left(v) =>
        Logger.error(v.getCause.getMessage)
        throw BackendServiceException(1006, "Error deserializing CreateUserOperationResponse.", v.getCause)
      case Right(v) => v
    }
  }

  def jsonStringToInterestsOperationResponse(value: String): InterestsOperationResponse = {
    decode[InterestsOperationResponse](value) match {
      case Left(v) =>
        Logger.error(v.getCause.getMessage)
        throw BackendServiceException(1006, "Error deserializing InterestsOperationResponse.", v.getCause)
      case Right(v) => v
    }
  }

  def jsonStringToErrorResponse(value: String): ErrorResponse = {
    decode[ErrorResponse](value) match {
      case Left(v) =>
        Logger.error(v.getCause.getMessage)
        throw BackendServiceException(1006, "Error deserializing ErrorResponse.", v.getCause)
      case Right(v) => v
    }
  }

  def jsonStringToUserDto(value: String): UserDto = {
    decode[UserDto](value) match {
      case Left(v) =>
        Logger.error(v.getCause.getMessage)
        throw BackendServiceException(1006, "Error deserializing UserDto.", v.getCause)
      case Right(v) => v
    }
  }

  def jsonStringToUserInterestsDto(value: String): UserInterestsDto = {
    decode[UserInterestsDto](value) match {
      case Left(v) =>
        Logger.error(v.getCause.getMessage)
        throw BackendServiceException(1006, "Error deserializing UserInterestsDto.", v.getCause)
      case Right(v) => v
    }
  }
}