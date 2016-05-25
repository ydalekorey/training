package controllers

import javax.inject._

import models.Credentials
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

/**
  * Created by yuriy on 21.05.16.
  */
@Singleton
class AuthenticationController @Inject() extends Controller {

  val credentialsForm = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(Credentials.apply)(Credentials.unapply))

  def loginForm() = Action {
    Ok(views.html.login(credentialsForm))
  }

  def authenticate() = Action { implicit request =>
    credentialsForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login(formWithErrors))
      },
      credentials => {
        Redirect(routes.ApplicationController.application()).withSession("email" -> credentials.email)
      }
    )
  }

  def logout() = Action {
    Redirect(routes.AuthenticationController.loginForm()).withNewSession
  }
}
