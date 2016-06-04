package dao.slick

import javax.inject.{Inject, Singleton}

import models.Doctor
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton
class DoctorDao @Inject()(dbConfigProvider: DatabaseConfigProvider) extends dao.DoctorDao {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  private val db = dbConfig.db

  import dbConfig.driver.api._

  private class DoctorsTable(tag: Tag) extends Table[Doctor](tag, "doctor") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def email = column[String]("email")

    def password = column[String]("password")

    def name = column[String]("name")

    def * = (id.?, email, password, name) <>(Doctor.tupled, Doctor.unapply)
  }

  private val Doctors = TableQuery[DoctorsTable]

  def findByEmail(email: String): Future[Option[Doctor]] = db.run(Doctors.filter(_.email === email).result.headOption)

  def findById(id: Long): Future[Option[Doctor]] = db.run(Doctors.filter(_.id === id).result.headOption)

  def insert(doctor: Doctor): Future[Long] = db.run(Doctors returning Doctors.map(_.id) += doctor)

  override def count(): Future[Int] = db.run(Doctors.map(_.id).length.result)
}
