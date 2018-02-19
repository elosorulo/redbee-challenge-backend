package challenge.backend.model.dynamo.impl

import challenge.backend.model.dynamo.DynamoBuilder
import com.amazonaws.services.dynamodbv2.{AmazonDynamoDB, AmazonDynamoDBClientBuilder}

class DynamoBuilderImpl extends DynamoBuilder {
  override def build(): AmazonDynamoDB = {
    AmazonDynamoDBClientBuilder
      .standard()
      .build()
  }
}
