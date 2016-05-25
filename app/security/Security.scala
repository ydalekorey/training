package security

import play.api._
import play.api.mvc._
import play.api.mvc.Security._


/**
  * Created by yuriy on 21.05.16.
  */
trait Security {
  def username(request: RequestHeader) = request.session.get("email")

  def onUnauthorized(request: RequestHeader) = Results.Redirect(controllers.routes.AuthenticationController.loginForm())

  def isAuthenticated(f: => String => Request[AnyContent] => Result) = {
    Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }

}
