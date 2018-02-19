package challenge.backend.handler

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.config.{GetUserInterestsModule, Initializer}
import challenge.backend.service.Service
import challenge.backend.service.status.ExecutionStatusResolver
import challenge.backend.log.Logger
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class GetUserInterestsHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {
  def  handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      Logger.context = Some(context)
      Logger.info("Handling getUserInterests Lambda Function.")
      Logger.info("Initializing GetUserInterestsService Dependencies.")
      val service: Service = initialize(new GetUserInterestsModule)
      Logger.info("Executing GetUserInterestsService.")
      service.execute(request, context)
    })
  }
}
