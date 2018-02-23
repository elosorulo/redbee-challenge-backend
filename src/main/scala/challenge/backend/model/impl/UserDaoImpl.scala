package challenge.backend.model.impl

import javax.inject.Inject

import challenge.backend.api.BackendServiceException
import challenge.backend.log.Logger
import challenge.backend.model.dynamo.DynamoBuilder
import challenge.backend.model.{User, UserDao, UserInterests}
import com.gu.scanamo.error.DynamoReadError
import com.gu.scanamo.syntax._
import com.gu.scanamo.{Scanamo, Table}
import org.joda.time.DateTime

class UserDaoImpl @Inject() (dynamoBuilder: DynamoBuilder) extends UserDao {

  private final val ENV_TABLE_NAME = "DYNAMODB_USERS_TABLE"

  def put(user: User): User = {
    Logger.info(s"Initializing table.")
    val table: Table[User] = Table[User](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    val timestamp = DateTime.now().getMillis
    Logger.info(s"Executing dynamoDB putOperation.")
    val persistedUser: User = user.copy(registrationDate = timestamp)
    Scanamo.exec(dynamoBuilder.build())(table.put(persistedUser))
    persistedUser
  }

  def updateIntersts(userName: String, userInterests: UserInterests): UserInterests = {
    Logger.info(s"Initializing table.")
    val table: Table[User] = Table[User](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    Logger.info(s"Executing dynamoDB update Operation.")
    readUser(Scanamo.exec(dynamoBuilder.build())
    (table.update('userName -> userName, set('interests -> userInterests)))).interests
  }

  def getIntersts(userName: String): UserInterests = {
    Logger.info(s"Initializing table.")
    val table: Table[User] = Table[User](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    Logger.info(s"Executing dynamoDB get Operation with userName: " + userName)
    Scanamo.exec(dynamoBuilder.build())(table.get('userName -> userName)).map(o => readUser(o)).get.interests
  }

  def readUser(output: Either[DynamoReadError, User]): User = {
    output match {
      case Right (x) => x
      case Left (x) =>
        Logger.error (x.toString)
        throw BackendServiceException(1004, "Unable to retrieve user from dynamodb.")
    }
  }

  override def getAll(): List[User] = {
    Logger.info(s"Initializing table.")
    val table: Table[User] = Table[User](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    Logger.info(s"Executing dynamoDB scan Operation")
    Scanamo.exec(dynamoBuilder.build())(table.scan()).map(o => readUser(o))
  }
}