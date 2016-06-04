package dao

import models.Doctor

import scala.concurrent.Future

trait DoctorDao {
  def findByEmail(email: String): Future[Option[Doctor]]

  def findById(id: Long): Future[Option[Doctor]]

  def insert(doctor: Doctor): Future[Long]

  def count(): Future[Int]
}