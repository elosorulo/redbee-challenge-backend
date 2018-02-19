package challenge.backend.service
import java.util
import javax.inject.Inject

import challenge.backend.api._
import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.log.Logger
import challenge.backend.model.{User, UserDao}
import challenge.backend.service.io.{IOMapper, JsonUtils, ModelMapper}
import com.amazonaws.services.lambda.runtime.Context

class CreateUserService @Inject() (userDao: UserDao) extends Service {
  override def execute(input: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val body = IOMapper.body(input)
    body match {
      case None => throw BackendServiceException(1001, "The input body was empty.")
      case Some(b) =>
        Logger.info(s"Deserializing body to request UserDto: $b")
        val requestUserDto: UserDto = JsonUtils.jsonStringToUserDto(b)
        Logger.info(s"Mapping request userDto to request user.")
        val requestUser: User = ModelMapper.user(requestUserDto)
        Logger.info("Executing UserDao put operation.")
        val responseUser: User =  userDao.put(requestUser)
        Logger.info(s"Mapping responseUser into response UserDto.")
        val responseUserDto: UserDto = ModelMapper.userDto(responseUser)
        Logger.info(s"Serializing response to Json.")
        val response: CreateUserOperationResponse =
          CreateUserOperationResponse(IOMapper.successfulExecutionStatus(), Some(responseUserDto))
        val responseBody: String = JsonUtils.createUserOperationResponseToJson(response)
        Logger.info(s"Building ApiGateWayResponse.")
        IOMapper.apiGatewayResponse(
          executionStatus = IOMapper.successfulExecutionStatus(),
          body = responseBody,
          headers = Map.empty)
    }
  }
}