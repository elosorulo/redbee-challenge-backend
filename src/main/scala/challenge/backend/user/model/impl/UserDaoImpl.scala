package challenge.backend.user.model.impl

import challenge.backend.user.model.{User, UserDao, UserInterests}

class UserDaoImpl extends UserDao {
  def put(user: User): Unit = ???
  def updateIntersts(userName: String): Unit = ???
  def getIntersts(userName: String): UserInterests = ???
}
