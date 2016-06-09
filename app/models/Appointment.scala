package models

import com.github.nscala_time.time.Imports._

case class Appointment(id: Option[Long] = None, doctorId: Option[Long] = None, startTime: DateTime, duration: Duration)
