package challenge.backend.service.input

import challenge.backend.api.{ExecutionStatus, InterestsOperationResponse, UserDto, UserInterestsDto}
import org.scalatest.{FunSuite, Matchers}
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

class JsonUtilsTest extends FunSuite with Matchers {

  private final val USER_INTERESTS_DTO: UserInterestsDto = UserInterestsDto(
    profiles = List("leomessi"),
    hashTags = List("futbol", "argentina"))

  private final val RESPONSE: InterestsOperationResponse = InterestsOperationResponse(
    executionStatus = ExecutionStatus(200, "SUCCEEDED", None),
    userInterests = Some(USER_INTERESTS_DTO))

  private final val USER_DTO: UserDto = UserDto("jorge", "jorge@gmail.com", USER_INTERESTS_DTO)

  test("UserDto marshalling and Unmarshalling Test.") {
    val userDtoJsonString: String = USER_DTO.asJson.noSpaces
    println(userDtoJsonString)
    val stringToUserDto: UserDto = JsonUtils.handleDecoding(decode[UserDto](userDtoJsonString))

    USER_DTO shouldEqual stringToUserDto
  }
}