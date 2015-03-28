package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import org.joda.time.DateTime
import org.json4s._, ext.JodaTimeSerializers, native.JsonMethods._
import com.github.tototoshi.play2.json4s.native._

case class BookForm(name: String, isbn: String)

object Books extends Controller with Json4s {

  implicit val formats = DefaultFormats ++ JodaTimeSerializers.all

  val bookForm = Form(
    mapping( //
      "name" -> text, //
      "isbn" -> text // 
      )(BookForm.apply)(BookForm.unapply))

  def all() = Action {
    Ok(Extraction.decompose(Book.findAll))
  }

  def show(id: Long) = Action {
    Book.find(id).map { book => Ok(Extraction.decompose(book)) }.getOrElse(NotFound)
  }

  def create() = Action { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        Book.create(name = form.name, isbn = form.isbn)
        NoContent
      })
  }

  def update(id: Long) = Action { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        val book = Book(id, form.name, form.isbn, new DateTime())
        book.save
        NoContent
      })
  }

  def delete(id: Long) = Action {
    Book.find(id).map { book =>
      book.destroy()
      NoContent
    }.getOrElse(NotFound)
  }

}
