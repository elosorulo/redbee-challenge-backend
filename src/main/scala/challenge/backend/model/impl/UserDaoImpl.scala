package challenge.backend.model.impl

import javax.inject.Inject

import challenge.backend.model.dynamo.DynamoBuilder
import challenge.backend.model.{User, UserDao, UserInterests}

class UserDaoImpl @Inject() (dynamoBuilder: DynamoBuilder) extends UserDao {
  def put(user: User): User = ???
  def updateIntersts(userName: String, userInterests: UserInterests): UserInterests = ???
  def getIntersts(userName: String): UserInterests = ???
}
