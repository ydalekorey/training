package bootstrap

import javax.inject.Inject

import dao.AccountDao
import models.Account
import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

/**
  * Created by yuriy on 03.06.16.
  */
class InitialData @Inject()(accountDao: AccountDao) {

  def insert(): Unit = {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    val insertInitialDataFuture = for {
      count <- accountDao.count() if count == 0
      _ <- accountDao.insert(Account(None, "user@email.com", BCrypt.hashpw("userpassword", BCrypt.gensalt()), "Yuriy"))
    } yield ()

    Try(Await.result(insertInitialDataFuture, Duration.Inf))
  }
  insert()
}
