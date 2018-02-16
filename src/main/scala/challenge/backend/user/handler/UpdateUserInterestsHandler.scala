package challenge.backend.user.handler

import challenge.backend.api.aws.ApiGatewayResponse
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

import scala.collection.JavaConverters

class UpdateUserInterestsHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] {
  def  handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ApiGatewayResponse(200, "Go Serverless v1.0! Your function executed successfully!",
      JavaConverters.mapAsJavaMap[String, Object](Map.empty), true)
  }
}
