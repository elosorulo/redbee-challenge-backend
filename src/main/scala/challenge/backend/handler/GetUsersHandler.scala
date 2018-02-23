package challenge.backend.handler

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.config.{GetUsersModule, Initializer}
import challenge.backend.log.Logger
import challenge.backend.service.Service
import challenge.backend.service.status.ExecutionStatusResolver
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class GetUsersHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {

  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      Logger.context = Some(context)
      Logger.info("Handling getUsers Lambda Function.")
      Logger.info("Initializing GetUsersService Dependencies.")
      val service: Service = initialize(new GetUsersModule)
      Logger.info("Executing GetUsersService.")
      service.execute(request, context)
    })
  }
}
