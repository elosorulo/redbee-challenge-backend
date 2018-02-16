package challenge.backend.user.model

import org.joda.time.DateTime

case class User(userName: String, registrationDate: DateTime, interests: Option[String])

case class UserInterests(profiles: List[String], hashTags: List[String])