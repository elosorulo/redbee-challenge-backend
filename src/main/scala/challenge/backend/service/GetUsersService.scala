package challenge.backend.service

import javax.inject.Inject

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.api.{GetUsersOperationResponse, UserDto}
import challenge.backend.log.Logger
import challenge.backend.model.{User, UserDao}
import challenge.backend.service.input.{IOMapper, ModelMapper}
import com.amazonaws.services.lambda.runtime.Context
import io.circe.generic.auto._
import io.circe.syntax._

class GetUsersService @Inject() (userDao: UserDao) extends Service {

  def execute(input: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val responseUsers: List[User] = userDao.getAll()
    Logger.info(s"Mapping response users to response userDto list.")
    val responseUsersDto: List[UserDto] = responseUsers.map(u => ModelMapper.userDto(u))
    Logger.info(s"Serializing response to json.")
    val response: GetUsersOperationResponse =
      GetUsersOperationResponse(IOMapper.successfulExecutionStatus(), responseUsersDto)
    val responseBody: String = response.asJson.noSpaces
    Logger.info(s"Building ApiGatewayResponse.")
    IOMapper.apiGatewayResponse(
      executionStatus = IOMapper.successfulExecutionStatus(),
      body = responseBody,
      headers = Map("Access-Control-Allow-Origin" -> "*", "Access-Control-Allow-Credentials" -> "true"))
  }
}
