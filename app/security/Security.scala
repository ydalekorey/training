package security

import play.api._
import play.api.mvc._
import play.api.mvc.Security._

import scala.concurrent.Future


/**
  * Created by yuriy on 21.05.16.
  */
trait Security {
  def userId(request: RequestHeader) = request.session.get("userId").map(_.toLong)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(controllers.routes.AuthenticationController.loginForm())

  def isAuthenticated(f: => Long => Request[AnyContent] => Result) = {
    Authenticated(userId, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }

  def isAuthenticatedAsync(f: => Long => Request[AnyContent] => Future[Result]) = {
    Authenticated(userId, onUnauthorized) { user =>
      Action.async(request => f(user)(request))
    }
  }

}
