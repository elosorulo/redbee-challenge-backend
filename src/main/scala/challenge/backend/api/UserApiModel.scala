package challenge.backend.api

case class UserDto(userName: String, email: String, interests: UserInterestsDto)

case class UserInterestsDto(profiles: List[String], hashTags: List[String])

case class CreateUserOperationResponse(executionStatus: ExecutionStatus, user: Option[UserDto])

case class GetUsersOperationResponse(executionStatus: ExecutionStatus, user: List[UserDto])

case class InterestsOperationResponse(executionStatus: ExecutionStatus, userInterests: Option[UserInterestsDto])

case class ErrorResponse(executionStatus: ExecutionStatus)

case class BackendServiceException(errorCode: Int, message: String = "",
                              cause: Throwable = None.orNull) extends Exception(message, cause)

case class ExecutionStatus(statusCode: Int, executionType: String, message: Option[String])

trait ExecutionTypes {
  protected final val SUCCEEDED = "SUCCEEDED"
  protected final val WARNING = "WARNING"
  protected final val UNKNOWN_ERROR = "UNKNOWN_ERROR"
  protected final val BUSINESS_ERROR = "BUSINESS_ERROR"
  protected final val INTERNAL_ERROR = "INTERNAL_ERROR"
  protected final val INVALID_INPUT_ERROR = "INVALID_INPUT_ERROR"
}
