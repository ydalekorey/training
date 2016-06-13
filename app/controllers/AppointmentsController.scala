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

    val currentMonth = YearMonth.now().toInterval
    val from = startTime.map(LocalDate.parse(_).toDateTime(LocalTime.Midnight)).getOrElse(currentMonth.getStart)
    val to = endTime.map(LocalDate.parse(_).plusDays(1).toDateTime(LocalTime.Midnight)).getOrElse(currentMonth.getEnd)

    for {
      appointments <- appointmentDao.findByDoctorIdAndDateTimeRange(userId, from, to)
    } yield Ok(Json.toJson(appointments))
  }
}
