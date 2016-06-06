package controllers

import javax.inject._

import models.Credentials
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.AuthenticationService

import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future

/**
  * Created by yuriy on 21.05.16.
  */
@Singleton
class AuthenticationController @Inject()(authenticationService: AuthenticationService) extends Controller {

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
        authenticationService.authenticate(credentials.email, credentials.password) map {
          case Some(doctor) => Redirect(routes.ApplicationController.application())
            .withSession("userId" -> doctor.id.map(_.toString).getOrElse(""))
          case None => BadRequest(views.html.login(credentialsForm))
        }
      }
    )
  }

  def logout() = Action {
    Redirect(routes.AuthenticationController.loginForm()).withNewSession
  }
}
