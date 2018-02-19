package challenge.backend.service.io

import challenge.backend.api._
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
    decode[CreateUserOperationResponse](value).toOption match {
      case None => throw new Exception("Improve error message")
      case Some(v) => v
    }
  }

  def jsonStringToInterestsOperationResponse(value: String): InterestsOperationResponse = {
    decode[InterestsOperationResponse](value).toOption match {
      case None => throw new Exception("Improve error message")
      case Some(v) => v
    }
  }

  def jsonStringToErrorResponse(value: String): ErrorResponse = {
    decode[ErrorResponse](value).toOption match {
      case None => throw new Exception("Improve error message")
      case Some(v) => v
    }
  }

  def jsonStringToUserDto(value: String): UserDto = {
    decode[UserDto](value).toOption match {
      case None => throw new Exception("Improve error message")
      case Some(v) => v
    }
  }

  def jsonStringToUserInterestsDto(value: String): UserInterestsDto = {
    decode[UserInterestsDto](value).toOption match {
      case None => throw new Exception("Improve error message")
      case Some(v) => v
    }
  }
}