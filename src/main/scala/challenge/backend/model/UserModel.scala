package challenge.backend.model

case class User(userName: String, email: String, registrationDate: Long, interests: UserInterests)

case class UserInterests(profiles: List[String], hashTags: List[String])