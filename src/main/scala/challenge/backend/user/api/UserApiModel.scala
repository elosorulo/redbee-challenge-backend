package challenge.backend.user.api

case class UserDto(userName: String, interests: Option[UserInterestsDto])

case class UserInterestsDto(profiles: List[String], hashTags: List[String])