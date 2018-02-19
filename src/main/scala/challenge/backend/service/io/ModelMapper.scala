package challenge.backend.service.io

import challenge.backend.api.{UserDto, UserInterestsDto}
import challenge.backend.model.{User, UserInterests}
import org.joda.time.DateTime

object ModelMapper {
  def userDto(user: User): UserDto = {
    UserDto(
      userName = user.userName,
      email = user.email,
      interests = userInterestsDto(user.interests)
    )
  }

  def userInterestsDto(userInterests: UserInterests): UserInterestsDto = {
    UserInterestsDto(profiles = userInterests.profiles, userInterests.hashTags)
  }

  def user(userDto: UserDto): User = {
    User(userName =
      userDto.userName,
      email = userDto.email,
      registrationDate = DateTime.now().getMillis,
      interests = userInterests(userDto.interests))
  }

  def userInterests(userInterestsDto: UserInterestsDto): UserInterests = {
    UserInterests(profiles = userInterestsDto.profiles, hashTags = userInterestsDto.hashTags)
  }
}
