package challenge.backend.handler

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.config.{Initializer, UpdateUserInterestsModule}
import challenge.backend.service.Service
import challenge.backend.service.status.ExecutionStatusResolver
import challenge.backend.log.Logger
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class UpdateUserInterestsHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {
  def  handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      Logger.context = Some(context)
      Logger.info("Handling updateUserInterests Lambda Function.")
      Logger.info("Initializing UpdateUserInterestsService Dependencies.")
      val service: Service = initialize(new UpdateUserInterestsModule)
      Logger.info("Executing UpdateUserInterestsService.")
      service.execute(request, context)
    })
  }
}
