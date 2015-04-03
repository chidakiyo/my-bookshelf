package plugin

import securesocial.controllers.ViewTemplates
import securesocial.core._
import play.api.data.Form
import play.api.mvc.RequestHeader
import play.api.i18n.Lang
import play.twirl.api.{ Html, Txt }

class AuthTemplatesPlugin(env: RuntimeEnvironment[_]) extends ViewTemplates.Default(env: RuntimeEnvironment[_]) {

  override def getLoginPage(form: Form[(String, String)], msg: Option[String] = None)(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.auth.login(form, msg)
  }

  override def getNotAuthorizedPage(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.auth.notAuthorized()
  }

}
