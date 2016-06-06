package dao

import com.github.nscala_time.time.Imports._
import models.Appointment

import scala.concurrent.Future

trait AppointmentDao {
  def findByDoctorIdAndDateTimeRange(doctorId: Long, startTime: DateTime, endTime: DateTime): Future[Seq[Appointment]]
}
