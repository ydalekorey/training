package models

import com.github.nscala_time.time.Imports._
import play.api.libs.json.{JsString, JsValue, Json, Writes}

case class Appointment(id: Option[Long] = None, doctorId: Option[Long] = None, startTime: DateTime, duration: Duration)

object Appointment {
  implicit val durationWrites = new Writes[Duration] {
    def writes(d: Duration): JsValue = JsString(d.toString)
  }
  implicit val dateTimeWrites = Writes.jodaDateWrites("YYYY-MM-dd'T'HH:mm:ss")
  implicit val appointmentFormat = Json.writes[Appointment]
}