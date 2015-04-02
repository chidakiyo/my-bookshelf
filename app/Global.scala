import controllers.CustomRoutesService
import java.lang.reflect.Constructor
import securesocial.core.RuntimeEnvironment
import scala.collection.immutable.ListMap
import securesocial.core.providers.GoogleProvider
import service.InMemoryUserService
import service.DemoUser

object Global extends play.api.GlobalSettings {

  /**
   * The runtime environment for this sample app.
   */
  object MyRuntimeEnvironment extends RuntimeEnvironment.Default[DemoUser] {
    override implicit val executionContext = play.api.libs.concurrent.Execution.defaultContext
    override lazy val routes = new CustomRoutesService()
    override lazy val userService = new InMemoryUserService()
    override lazy val providers = ListMap( //
      include(new GoogleProvider(routes, cacheService, oauth2ClientFor(GoogleProvider.Google))) // google only
      )
  }

  /**
   * An implementation that checks if the controller expects a RuntimeEnvironment and
   * passes the instance to it if required.
   *
   * This can be replaced by any DI framework to inject it differently.
   *
   * @param controllerClass
   * @tparam A
   * @return
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    val instance = controllerClass.getConstructors.find { c =>
      val params = c.getParameterTypes
      params.length == 1 && params(0) == classOf[RuntimeEnvironment[DemoUser]]
    }.map {
      _.asInstanceOf[Constructor[A]].newInstance(MyRuntimeEnvironment)
    }
    instance.getOrElse(super.getControllerInstance(controllerClass))
  }
}
