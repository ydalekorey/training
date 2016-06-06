package dao.slick

import javax.inject.{Inject, Singleton}

import com.github.nscala_time.time.Imports._
import models.Appointment
import org.joda.time.format.{PeriodFormat, PeriodFormatter}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton
class AppointmentDao @Inject()(dbConfigProvider: DatabaseConfigProvider) extends dao.AppointmentDao {

  private val dbDateTimePattern = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

  private val periodFormat = PeriodFormat.getDefault

  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  private val db = dbConfig.db

  import dbConfig.driver.api._

  implicit val dateTimeColumnType = MappedColumnType.base[DateTime, String](
    { dt => dt.toString(dbDateTimePattern) },    // map DateTime to Long
    { l => DateTime.parse(l, dbDateTimePattern) } // map Long to DateTime
  )

  implicit val durationTimeColumnType = MappedColumnType.base[Duration, String](
    { d => d.toPeriod.toString(periodFormat) },    // map Duration to Long
    { l => Period.parse(l, periodFormat).toD } // map Long to Duration
  )

  private class AppointmentsTable(tag: Tag) extends Table[Appointment](tag, "appointment") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def doctorId = column[Long]("doctor_id")

    def startTime = column[DateTime]("start_time")

    def duration = column[Duration]("duration")

    def * = (id.?, doctorId.?, startTime, duration) <> ((Appointment.apply _).tupled, Appointment.unapply)
  }

  private val Appointments = TableQuery[AppointmentsTable]

  def findByDoctorIdAndDateTimeRange(doctorId: Long, startTime: DateTime, endTime: DateTime): Future[Seq[Appointment]] = {
    db.run(Appointments.filter(a => a.doctorId === doctorId && a.startTime >= startTime && a.startTime <= endTime).result)
  }

}
