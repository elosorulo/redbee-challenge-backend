package challenge.backend.model

trait UserDao {
  def put(user: User): User
  def updateIntersts(userName: String, userInterests: UserInterests): UserInterests
  def getIntersts(userName: String): UserInterests
  def getAll(): List[User]
}


