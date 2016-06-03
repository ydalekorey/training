package services

import javax.inject.{Inject, Singleton}

import dao.AccountDao
import models.Account
import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AccountService @Inject()(private val accountDao: AccountDao) {

  def authenticate(email: String, password: String): Future[Option[Account]] = {
    accountDao.findByEmail(email).map(_.filter(account => BCrypt.checkpw(password, account.password)))
  }

}