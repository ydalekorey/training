package controllers

import javax.inject._

import play.api.mvc._
import security.Security


@Singleton
class ApplicationController @Inject() extends Controller with Security {

  def application = isAuthenticated { username => implicit request =>
    Ok(views.html.application())
  }

}
