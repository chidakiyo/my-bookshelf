package controllers

import play.api._
import play.api.mvc._
import securesocial.core._
import service.DemoUser

class Application(override implicit val env: RuntimeEnvironment[DemoUser]) extends SecureSocial[DemoUser] {

  def index = SecuredAction {
    Ok(views.html.index("Your new application is ready."))
  }

}