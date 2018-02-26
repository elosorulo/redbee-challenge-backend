package challenge.backend.service
import java.util
import javax.inject.Inject

import challenge.backend.api._
import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.log.Logger
import challenge.backend.model.{User, UserDao}
import challenge.backend.service.input.{IOMapper, JsonUtils, ModelMapper}
import com.amazonaws.services.lambda.runtime.Context
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._


class CreateUserService @Inject() (userDao: UserDao) extends Service {
  override def execute(input: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val body = IOMapper.body(input)
    body match {
      case None => throw BackendServiceException(1001, "The input body was empty.")
      case Some(b) =>
        Logger.info(s"Deserializing body to request UserDto: $b")
        val requestUserDto: UserDto = JsonUtils.handleDecoding(decode[UserDto](b))
        Logger.info(s"Mapping request userDto to request user.")
        val requestUser: User = ModelMapper.user(requestUserDto)
        Logger.info("Executing UserDao put operation.")
        val responseUser: User =  userDao.put(requestUser)
        Logger.info(s"Mapping responseUser into response UserDto.")
        val responseUserDto: UserDto = ModelMapper.userDto(responseUser)
        Logger.info(s"Serializing response to Json.")
        val response: CreateUserOperationResponse =
          CreateUserOperationResponse(IOMapper.successfulExecutionStatus(), Some(responseUserDto))
        val responseBody: String = response.asJson.noSpaces
        Logger.info(s"Building ApiGateWayResponse.")
        IOMapper.apiGatewayResponse(
          executionStatus = IOMapper.successfulExecutionStatus(),
          body = responseBody,
          headers = Map("Access-Control-Allow-Origin" -> "*", "Access-Control-Allow-Credentials" -> "true"))
    }
  }
}