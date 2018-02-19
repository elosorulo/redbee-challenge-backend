package challenge.backend.handler

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.config.{CreateUserModule, Initializer}
import challenge.backend.service.Service
import challenge.backend.service.status.ExecutionStatusResolver
import challenge.backend.log.Logger
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class CreateUserHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {
  def  handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      Logger.context = Some(context)
      Logger.info("Handling createUser Lambda Function.")
      Logger.info("Initializing CreateUserService Dependencies.")
      val service: Service = initialize(new CreateUserModule)
      Logger.info("Executing CreateUserService.")
      service.execute(request, context)
    })
  }
}
