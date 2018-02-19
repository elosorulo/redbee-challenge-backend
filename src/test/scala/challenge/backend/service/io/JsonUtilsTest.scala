package challenge.backend.service.io

import challenge.backend.api.{ExecutionStatus, InterestsOperationResponse, UserDto, UserInterestsDto}
import org.scalatest.{FunSuite, Matchers}

class JsonUtilsTest extends FunSuite with Matchers {

  private final val USER_INTERESTS_DTO: UserInterestsDto = UserInterestsDto(
    profiles = List("leomessi"),
    hashTags = List("futbol", "argentina"))

  private final val RESPONSE: InterestsOperationResponse = InterestsOperationResponse(
    executionStatus = ExecutionStatus(200, "SUCCEEDED", None),
    userInterests = Some(USER_INTERESTS_DTO))

  private final val USER_DTO: UserDto = UserDto("jorge", "jorge@gmail.com", USER_INTERESTS_DTO)

  test("InterestsOperationResponse Marshalling and Unmarshalling Test.") {
    val responseJsonString: String = JsonUtils.interestsOperationResponseToJson(RESPONSE)
    println(responseJsonString)
    val stringToResponse: InterestsOperationResponse = JsonUtils.jsonStringToInterestsOperationResponse(responseJsonString)

    RESPONSE shouldEqual stringToResponse
  }

  test("UserDto marshalling and Unmarshalling Test.") {
    val userDtoJsonString: String = JsonUtils.userDtoToJson(USER_DTO)
    println(userDtoJsonString)
    val stringToUserDto: UserDto = JsonUtils.jsonStringToUserDto(userDtoJsonString)

    USER_DTO shouldEqual stringToUserDto
  }
}