package services

import javax.inject.{Inject, Singleton}

import dao.DoctorDao
import models.Doctor
import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AuthenticationService @Inject()(private val doctorDao: DoctorDao) {

  def authenticate(email: String, password: String): Future[Option[Doctor]] = {
    doctorDao.findByEmail(email).map(_.filter(doctor => BCrypt.checkpw(password, doctor.password)))
  }

}