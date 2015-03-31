package models

trait RepositoryContext[E] {

  def all(): List[E]

  def find(id: String): Option[E]

  def destroy(id: String): Unit

}
