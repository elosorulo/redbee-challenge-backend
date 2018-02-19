package challenge.backend.user.model

trait UserDao {
  def put(user: User): Unit
  def updateIntersts(userName: String): Unit
  def getIntersts(userName: String): UserInterests
}


