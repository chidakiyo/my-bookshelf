package controllers

import play.api._
import play.api.mvc._
import models._

object Books extends Controller {

  def show(id: Long) = Action {
    Book.find(id).map { book =>
      println(book) // TODO debug print
      NoContent
    }.getOrElse(NotFound)
  }

  def delete(id: Long) = Action {
    Book.find(id).map { book =>
      book.destroy()
      NoContent
    }.getOrElse(NotFound)
  }

}
