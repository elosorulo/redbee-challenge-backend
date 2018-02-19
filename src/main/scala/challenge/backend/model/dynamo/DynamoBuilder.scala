package challenge.backend.model.dynamo

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB

trait DynamoBuilder {
  def build(): AmazonDynamoDB
}
