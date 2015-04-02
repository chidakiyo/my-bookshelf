package plugin

import securesocial.controllers.ViewTemplates
import securesocial.controllers.ChangeInfo
import securesocial.core._
import play.api.data.Form
import play.twirl.api.{ Html, Txt }
import play.api.mvc.RequestHeader
import securesocial.controllers.Registration
import securesocial.controllers.RegistrationInfo
import play.api.i18n.Lang

class CustomTemplatesPlugin(env: RuntimeEnvironment[_]) extends ViewTemplates {

  implicit val implicitEnv = env

  override def getLoginPage(form: Form[(String, String)], msg: Option[String] = None)(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.login(form, msg)
  }

  override def getSignUpPage(form: Form[RegistrationInfo], token: String)(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.Registration.signUp(form, token)
  }

  override def getStartSignUpPage(form: Form[String])(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.Registration.startSignUp(form)
  }

  override def getResetPasswordPage(form: Form[(String, String)], token: String)(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.Registration.startResetPassword(form)
  }

  def getStartResetPasswordPage(form: Form[String])(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.Registration.resetPasswordPage(form)
  }

  def getPasswordChangePage(form: Form[ChangeInfo])(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.passwordChange(form)
  }

  def getNotAuthorizedPage(implicit request: RequestHeader, lang: Lang): Html = {
    views.html.custom.notAuthorized()
  }

}
