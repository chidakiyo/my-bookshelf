package models

import java.util.Date

object DummyRepository extends RepositoryContext[Book] {

  var currentId = 1

  def newBook(name: String, isbn: String): Book = {
    val book = Book(currentId.toString, name, isbn)
    currentId = currentId + 1
    book
  }

  var registry: List[Book] = List( //
    newBook("Book1", "B1234"), //
    newBook("Book2", "B5678") //
    )

  override def find(id: String): Option[Book] = registry.find(_.id == id)

  override def all(): List[Book] = registry

  def create(name: String, isbn: String, created_at: Date = new Date): Book = {
    val maxId = registry.map(_.id.toLong).max
    val book = Book((maxId + 1).toString, name, isbn, created_at)
    registry = book :: registry
    book
  }

  def save(m: Book): Book = {
    registry = registry.filterNot(_.id == m.id)
    registry = m :: registry
    m
  }

  override def destroy(id: String): Unit = {
    registry = registry.filterNot(_.id == id)
  }

}
