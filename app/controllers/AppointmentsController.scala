package controllers

import javax.inject._

import com.github.nscala_time.time.Imports._
import dao.AppointmentDao
import play.api.libs.json.Json
import play.api.mvc._
import security.Security
import play.api.libs.concurrent.Execution.Implicits._
import web.json.implicits.Appointments._

@Singleton
class AppointmentsController @Inject()(appointmentDao: AppointmentDao) extends Controller with Security {
  def appointments(startTime: Option[String], endTime: Option[String]) = isAuthenticatedAsync { userId => implicit request =>

    //val from = startTime.map(DateTime.parse).getOrElse(DateTime.now().withTime(LocalTime.Midnight))
    //val to = startTime.map(DateTime.parse).getOrElse(DateTime.now().plusDays(1).withTime(LocalTime.Midnight))

    val from = DateTime.now().withTime(LocalTime.Midnight)
    val to = DateTime.now().plusDays(1).withTime(LocalTime.Midnight)

    for {
      appointments <- appointmentDao.findByDoctorIdAndDateTimeRange(userId, from, to)
    } yield Ok(Json.toJson(appointments))
  }
}
