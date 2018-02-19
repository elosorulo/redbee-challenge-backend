package challenge.backend.config

import challenge.backend.service.{CreateUserService, GetUserInterestsService, Service, UpdateUserInterestsService}
import challenge.backend.model.UserDao
import challenge.backend.model.dynamo.DynamoBuilder
import challenge.backend.model.dynamo.impl.DynamoBuilderImpl
import challenge.backend.model.impl.UserDaoImpl
import net.codingwell.scalaguice.ScalaModule

class CreateUserModule extends ScalaModule {
  override def configure(): Unit = {
    bind[UserDao].to[UserDaoImpl].asEagerSingleton()
    bind[DynamoBuilder].to[DynamoBuilderImpl].asEagerSingleton()
    bind[Service].to[CreateUserService].asEagerSingleton()
  }
}

class GetUserInterestsModule extends ScalaModule {
  override def configure(): Unit = {
    bind[UserDao].to[UserDaoImpl].asEagerSingleton()
    bind[DynamoBuilder].to[DynamoBuilderImpl].asEagerSingleton()
    bind[Service].to[GetUserInterestsService].asEagerSingleton()
  }
}

class UpdateUserInterestsModule extends ScalaModule {
  override def configure(): Unit = {
    bind[UserDao].to[UserDaoImpl].asEagerSingleton()
    bind[DynamoBuilder].to[DynamoBuilderImpl].asEagerSingleton()
    bind[Service].to[UpdateUserInterestsService].asEagerSingleton()
  }
}