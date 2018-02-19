package challenge.backend.service.io

import challenge.backend.api.{ExecutionStatus, InterestsOperationResponse, UserInterestsDto}
import org.scalatest.{FunSuite, Matchers}

class JsonUtilsTest extends FunSuite with Matchers {

  private final val RESPONSE: InterestsOperationResponse = InterestsOperationResponse(
    executionStatus = ExecutionStatus(200, "SUCCEEDED", None),
    userInterests = Some(UserInterestsDto(
      profiles = List("leomessi"),
      hashTags = List("futbol", "argentina"))))

  test("Single Item Marshalling and Unmarshalling Test.") {
    val responseJsonString: String = JsonUtils.interestsOperationResponseToJson(RESPONSE)

    val stringToResponse: InterestsOperationResponse = JsonUtils.jsonStringToInterestsOperationResponse(responseJsonString)

    RESPONSE shouldEqual stringToResponse
  }
}