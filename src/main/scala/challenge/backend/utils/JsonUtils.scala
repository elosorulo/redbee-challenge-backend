package challenge.backend.utils

import challenge.backend.user.api.{CreateUserOperationResponse, InterestsOperationResponse, UserDto, UserInterestsDto}

object JsonUtils {

  def createUserOperationResponseToJson(createUserOperationResponse: CreateUserOperationResponse): String = ???

  def interestsOperationResponseToJson(interestsOperationResponse: InterestsOperationResponse): String = ???

  def userInterestsDtoToJson(userInterests: UserInterestsDto): String = ???

  def userDtoToJson(userDto: UserDto): String = ???

  def jsonStringToCreateUserOperationResponse(value: String): CreateUserOperationResponse = ???

  def jsonStringTointerestsOperationResponse(value: String): InterestsOperationResponse = ???

  def jsonStringToUserDto(value: String): UserDto = ???

  def jsonStringToUserInterestsDto(value: String): UserInterestsDto = ???
}