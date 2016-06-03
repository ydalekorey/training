package models

/**
  * Created by yuriy on 29.05.16.
  */
case class Account(id: Option[Long] = None, email: String, password: String, name: String)