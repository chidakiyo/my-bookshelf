package models

import java.util.Date

case class Book( //
  id: String, //
  name: String, //
  isbn: String, //
  createdAt: Date = new Date //
  ) {
}
