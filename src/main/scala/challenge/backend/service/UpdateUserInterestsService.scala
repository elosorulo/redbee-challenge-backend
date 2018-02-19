package challenge.backend.service
import java.util
import javax.inject.Inject

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.api.{BackendServiceException, ExecutionTypes, InterestsOperationResponse, UserInterestsDto}
import challenge.backend.log.Logger
import challenge.backend.model.{UserDao, UserInterests}
import challenge.backend.service.io.{IOMapper, JsonUtils, ModelMapper}
import com.amazonaws.services.lambda.runtime.Context

class UpdateUserInterestsService @Inject() (userDao: UserDao) extends Service with ExecutionTypes {
  override def execute(input: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    IOMapper.body(input) match {
      case None => throw BackendServiceException(1001, "The input body was empty.")
      case Some(b) =>
        IOMapper.pathParameters(input).get("userName") match {
          case None => throw BackendServiceException(1002, "Invalid path parameters.")
          case Some(un) =>
            Logger.info(s"Deserializing body to request userInterestsDto: $b.")
            val requestInterestsDto: UserInterestsDto = JsonUtils.jsonStringToUserInterestsDto(b)
            Logger.info(s"Mapping request userInterestsDto to request userInterests.")
            val requestInterests: UserInterests = ModelMapper.userInterests(requestInterestsDto)
            Logger.info(s"Executing updateOperation with request userInterests.")
            val responseUserInterests: UserInterests = userDao.updateIntersts(un, requestInterests)
            Logger.info(s"Mapping response userInterests to response userInterestsDto.")
            val responseUserInterestsDto: UserInterestsDto = ModelMapper.userInterestsDto(responseUserInterests)
            Logger.info(s"Serializing response to json.")
            val response: InterestsOperationResponse =
              InterestsOperationResponse(IOMapper.successfulExecutionStatus(), Some(responseUserInterestsDto))
            val responseBody: String = JsonUtils.interestsOperationResponseToJson(response)
            Logger.info(s"Building ApiGatewayResponse.")
            IOMapper.apiGatewayResponse(
              executionStatus = IOMapper.successfulExecutionStatus(),
              body = responseBody,
              headers = Map.empty)
        }
    }
  }
}
