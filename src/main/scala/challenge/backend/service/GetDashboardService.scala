package challenge.backend.service

import challenge.backend.api.aws.ApiGatewayResponse
import com.amazonaws.services.lambda.runtime.Context

class GetDashboardService extends Service {
  def execute(input: java.util.Map[String, Object], context: Context): ApiGatewayResponse = ???
}
