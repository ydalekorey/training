package dao.slick

import javax.inject.{Inject, Singleton}

import models.Account
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton
class AccountDao @Inject()(dbConfigProvider: DatabaseConfigProvider) extends dao.AccountDao {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  private val db = dbConfig.db

  import dbConfig.driver.api._

  private class AccountsTable(tag: Tag) extends Table[Account](tag, "account") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def email = column[String]("email")

    def password = column[String]("password")

    def name = column[String]("name")

    def * = (id.?, email, password, name) <>(Account.tupled, Account.unapply)
  }

  private val Accounts = TableQuery[AccountsTable]

  def findByEmail(email: String): Future[Option[Account]] =
    db.run(Accounts.filter(_.email === email).result.headOption)

  def findById(id: Long): Future[Option[Account]] =
    db.run(Accounts.filter(_.id === id).result.headOption)

  def insert(account: Account): Future[Long] = db.run(Accounts returning Accounts.map(_.id) += account)
}
