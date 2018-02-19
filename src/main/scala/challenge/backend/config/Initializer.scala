package challenge.backend.config

import challenge.backend.service.Service
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.{Guice, Module}

trait Initializer {
  def initialize(module: Module): Service = {
    val injector = Guice.createInjector(module)
    injector.instance[Service]
  }
}
