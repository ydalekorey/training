package dao

import models.Account

import scala.concurrent.Future

trait AccountDao {
  def findByEmail(email: String): Future[Option[Account]]

  def findById(id: Long): Future[Option[Account]]

  def insert(account: Account): Future[Long]

  def count(): Future[Int]
}