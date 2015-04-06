package models

import org.joda.time.DateTime

case class Book( //
  id: Long, //
  name: String, //
  isbn: String, //
  createdAt: DateTime //
  ) {

  def save(): Book = Book.save(this)
  def destroy(): Unit = Book.destroy(id)
}

object Book {

  var registry: List[Book] = List( //
    Book(1, "Book1", "B1234", new DateTime()), //
    Book(2, "Book2", "B5678", new DateTime()) //
    )

  def find(id: Long): Option[Book] = registry.find(_.id == id)

  def findAll(): List[Book] = registry

  def save(id: Long = registry.map(_.id).max + 1, name: String, isbn: String, createdAt: DateTime = DateTime.now): Book = {
    val book = Book(id, name, isbn, createdAt)
    save(book)
  }

  def save(m: Book): Book = {
    registry = registry.filterNot(_.id == m.id)
    registry = m :: registry
    m
  }

  def destroy(id: Long): Unit = {
    registry = registry.filterNot(_.id == id)
  }

}
