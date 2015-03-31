package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import org.joda.time.DateTime

case class BookForm(name: String, isbn: String)

object Books extends Controller {

  val bookForm = Form(
    mapping( //
      "name" -> text, //
      "isbn" -> text // 
      )(BookForm.apply)(BookForm.unapply))

  def all() = Action {
    val books = Book.all()
    Ok(views.html.list(books.sortBy(_.id)))
  }

  def create() = Action {
    Ok(views.html.createForm(bookForm))
  }

  def edit(id: Long) = Action {
    Book.find(id).map { book =>
      Ok(views.html.editForm(id, bookForm.fill(BookForm(book.name, book.isbn))))
    }.getOrElse(NotFound)
  }

  def save() = Action { implicit req =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        Book.create(name = form.name, isbn = form.isbn)
        val books = Book.all()
        Ok(views.html.list(books.sortBy(_.id)))
      })
  }

  def update(id: Long) = Action { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        val book = Book(form.name, form.isbn)
        book.save
        val books = Book.all()
        Ok(views.html.list(books.sortBy(_.id)))
      })
  }

  def delete(id: Long) = Action {
    Book.find(id).map { book =>
      book.destroy()
      NoContent
    }.getOrElse(NotFound)
  }

}
