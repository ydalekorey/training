package controllers

import javax.inject._

import models.Credentials
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.AccountService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by yuriy on 21.05.16.
  */
@Singleton
class AuthenticationController @Inject()(accountService: AccountService) extends Controller {

  val credentialsForm = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(Credentials.apply)(Credentials.unapply))

  def loginForm() = Action {
    Ok(views.html.login(credentialsForm))
  }

  def authenticate() = Action.async { implicit request =>
    credentialsForm.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(views.html.login(formWithErrors)))
      },
      credentials => {
        accountService.authenticate(credentials.email, credentials.password) map {
          case Some(account) => Redirect(routes.ApplicationController.application()).withSession("email" -> credentials.email)
          case None => BadRequest(views.html.login(credentialsForm))
        }
      }
    )
  }

  def logout() = Action {
    Redirect(routes.AuthenticationController.loginForm()).withNewSession
  }
}
