package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import models.Book

case class BookForm(name: String, isbn: String)

object Books extends Controller {

  val bookForm = Form(
    mapping( //
      "name" -> text, //
      "isbn" -> text // 
      )(BookForm.apply)(BookForm.unapply))

  def all() = Action {
    val books = Book.findAll()
    Ok(views.html.list(books))
  }

  def show(id: Long) = Action {
    Book.find(id).map { book =>
      NoContent
    }.getOrElse(NotFound)
  }

  def create() = Action {
    Ok(views.html.createForm(bookForm))
  }

  def save() = Action { implicit req =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        Book.create(name = form.name, isbn = form.isbn)
        val books = Book.findAll()
        Ok(views.html.list(books))
      })
  }

  def delete(id: Long) = Action {
    Book.find(id).map { book =>
      book.destroy()
      NoContent
    }.getOrElse(NotFound)
  }

}
