package models

import scalikejdbc._
import org.joda.time.DateTime

case class Book( //
  id: Long, //
  name: String, //
  isbn: String, //
  createdAt: DateTime, //
  deletedAt: Option[DateTime] = None //
  ) {

  def save()(implicit session: DBSession = Book.autoSession): Book = Book.save(this)(session)
  def destroy()(implicit session: DBSession = Book.autoSession): Unit = Book.destroy(id)(session)

}

object Book extends SQLSyntaxSupport[Book] {

  override val tableName = "book"
  override val columns = Seq("id", "name", "isbn", "created_at", "deleted_at")

  def apply(p: SyntaxProvider[Book])(rs: WrappedResultSet): Book = apply(p.resultName)(rs)
  def apply(p: ResultName[Book])(rs: WrappedResultSet): Book = new Book(
    id = rs.get(p.id),
    name = rs.get(p.name),
    isbn = rs.get(p.isbn),
    createdAt = rs.get(p.createdAt),
    deletedAt = rs.get(p.deletedAt))

  val b = Book.syntax("b")

  def find(id: Long)(implicit session: DBSession = autoSession): Option[Book] = withSQL {
    select.from(Book as b).where.eq(b.id, id)
  }.map(Book(b.resultName)).single.apply()

  def findAll()(implicit session: DBSession = autoSession): List[Book] = withSQL {
    select
      .from(Book as b)
      .orderBy(b.id)
  }.map(Book(b.resultName)).list.apply()

  def create(name: String, isbn: String, createdAt: DateTime = DateTime.now)(implicit session: DBSession = autoSession): Book = {
    val id = withSQL {
      insert.into(Book).namedValues(
        column.name -> name,
        column.isbn -> isbn,
        column.createdAt -> createdAt)
    }.updateAndReturnGeneratedKey.apply()

    Book(
      id = id,
      name = name,
      isbn = isbn,
      createdAt = createdAt)
  }

  def save(m: Book)(implicit session: DBSession = autoSession): Book = {
    withSQL {
      update(Book).set(
        column.name -> m.name,
        column.isbn -> m.isbn).where.eq(column.id, m.id)
    }.update.apply()
    m
  }

  def destroy(id: Long)(implicit session: DBSession = autoSession): Unit = withSQL {
    update(Book).set(column.deletedAt -> DateTime.now).where.eq(column.id, id)
  }.update.apply()

}
