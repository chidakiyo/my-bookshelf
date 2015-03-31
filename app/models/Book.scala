package models

import org.joda.time.DateTime

case class Book( //
  id: Long, //
  name: String, //
  isbn: String, //
  createdAt: DateTime = new DateTime //
  ) {

  def save(): Book = Book.save(this)
  def destroy(): Unit = Book.destroy(id)
}

object Book {

  var currentId = 1

  def apply(name: String, isbn: String): Book = {
    val book = Book(currentId, name, isbn)
    currentId = currentId + 1
    book
  }

  var registry: List[Book] = List( //
    Book("Book1", "B1234"), //
    Book("Book2", "B5678") //
    )

  def find(id: Long): Option[Book] = registry.find(_.id == id)

  def all(): List[Book] = registry

  def create(name: String, isbn: String, createdAt: DateTime = DateTime.now): Book = {
    val maxId = registry.map(_.id).max
    val book = Book(maxId + 1, name, isbn, createdAt)
    registry = book :: registry
    book
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
