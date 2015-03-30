package models

import java.util.Date
import play.api.Play.current
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._

import mongoContext._

case class Books( //
  id: ObjectId = new ObjectId, //
  name: String, //
  isbn: String, //
  created_at: Date = new Date() //
  ) {
  def save(): Books = {
    Books.save(this)
    this
  }
  def destroy(): Unit = {
    Books.removeById(this.id)
  }
}

object Books extends ModelCompanion[Books, ObjectId] {
  def collection = mongoCollection("books")
  val dao = new SalatDAO[Books, ObjectId](collection) {}
  // Queries
  // TODO
}
