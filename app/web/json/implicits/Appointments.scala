package web.json.implicits

import com.github.nscala_time.time.Imports._
import models.Appointment
import play.api.libs.json.{JsString, JsValue, Json, Writes}

object Appointments {
  implicit val durationWrites = new Writes[Duration] {
    def writes(d: Duration): JsValue = JsString(d.toString)
  }
  implicit val dateTimeWrites = Writes.jodaDateWrites("YYYY-MM-dd'T'HH:mm:ss")
  implicit val appointmentFormat = Json.writes[Appointment]
}
