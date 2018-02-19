package challenge.backend.utils

import challenge.backend.user.api.{ExecutionStatus, InterestsOperationResponse, UserInterestsDto}
import org.scalatest.{FunSuite, Matchers}

class JsonUtilsTest extends FunSuite with Matchers {

  private final val RESPONSE: InterestsOperationResponse = InterestsOperationResponse(
    executionStatus = ExecutionStatus(200, "message"),
    userInterests = Some(UserInterestsDto(
      profiles = List("leomessi"),
      hashTags = List("futbol", "argentina"))))

  test("Single Item Marshalling and Unmarshalling Test.") {
    val responseJsonString: String = JsonUtils.interestsOperationResponseToJson(RESPONSE)

    val stringToResponse: InterestsOperationResponse = JsonUtils.jsonStringTointerestsOperationResponse(responseJsonString)

    RESPONSE shouldEqual stringToResponse
  }
}