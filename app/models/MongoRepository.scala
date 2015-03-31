package models

import java.util.Date
import play.api.Play
import play.api.Play.current
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._

object MongoRepository extends ModelCompanion[Book, String] with RepositoryContext[Book] {

  implicit val context = {
    val context = new Context {
      val name = "global"
      override val typeHintStrategy = StringTypeHintStrategy(when = TypeHintFrequency.WhenNecessary, typeHint = "_t")
    }
    context.registerGlobalKeyOverride(remapThis = "id", toThisInstead = "_id")
    context.registerClassLoader(Play.classloader)
    context
  }

  def collection = mongoCollection("book")
  val dao = new SalatDAO[Book, String](collection) {}

  override def all(): List[Book] = this.findAll().toList

  override def find(id: String): Option[Book] = findOneById(id)

  def create(name: String, isbn: String, created_at: Date = new Date): Book = {
    val book = Book(new ObjectId().toString, name = name, isbn = isbn)
    save(book)
    book
  }

  override def destroy(id: String): Unit = {
    this.removeById(id)
  }

}
