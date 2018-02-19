package challenge.backend.utils

import challenge.backend.user.api.{CreateUserOperationResponse, InterestsOperationResponse, UserDto, UserInterestsDto}
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object JsonUtils {

  def createUserOperationResponseToJson(createUserOperationResponse: CreateUserOperationResponse): String = {
    createUserOperationResponse.asJson.noSpaces
  }

  def interestsOperationResponseToJson(interestsOperationResponse: InterestsOperationResponse): String = {
    interestsOperationResponse.asJson.noSpaces
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

  def jsonStringTointerestsOperationResponse(value: String): InterestsOperationResponse = {
    decode[InterestsOperationResponse](value).toOption match {
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