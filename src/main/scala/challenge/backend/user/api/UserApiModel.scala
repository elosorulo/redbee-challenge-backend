package challenge.backend.user.api

case class UserDto(userName: String, interests: Option[UserInterestsDto])

case class UserInterestsDto(profiles: List[String], hashTags: List[String])

case class ExecutionStatus(statusCode: Int, statusMessage: String)

case class CreateUserOperationResponse(executionStatus: ExecutionStatus, user: Option[UserDto])

case class InterestsOperationResponse(executionStatus: ExecutionStatus, userInterests: Option[UserInterestsDto])