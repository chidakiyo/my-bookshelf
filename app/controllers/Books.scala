package controllers

import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import api.JsonApi

case class BookForm(name: String, isbn: String)

object Books extends JsonApi {

  val bookForm = Form(
    mapping( //
      "name" -> text, //
      "isbn" -> text // 
      )(BookForm.apply)(BookForm.unapply))

  def all() = Action {
    json {
      Book.findAll
    }
  }

  def show(id: Long) = Action {
    json {
      Book.find(id)
    }
  }

  def create() = Action { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        json {
          Book.save(name = form.name, isbn = form.isbn)
        }
      })
  }

  def update(id: Long) = Action { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => BadRequest("invalid parameters"),
      form => {
        json {
          Book.save(id = id, name = form.name, isbn = form.isbn)
        }
      })
  }

  def delete(id: Long) = Action {
    json {
      Book.destroy(id)
    }
  }

}
