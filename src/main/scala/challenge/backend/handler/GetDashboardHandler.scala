package challenge.backend.handler

import challenge.backend.api.aws.ApiGatewayResponse
import challenge.backend.config.{GetDashboardModule, Initializer}
import challenge.backend.log.Logger
import challenge.backend.service.Service
import challenge.backend.service.status.ExecutionStatusResolver
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class GetDashboardHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {
  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      Logger.context = Some(context)
      Logger.info("Handling getDashboard Lambda Function.")
      Logger.info("Initializing GetDashboardService Dependencies.")
      val service: Service = initialize(new GetDashboardModule)
      Logger.info("Executing GetDashboardService.")
      service.execute(request, context)
    })
  }
}
