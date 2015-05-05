package api

import play.api._
import play.api.mvc._
import org.joda.time.DateTime
import org.json4s._
import ext.JodaTimeSerializers
import native.JsonMethods._
import com.github.tototoshi.play2.json4s.native._

trait JsonApi extends Controller with Json4s {

  implicit val formats = DefaultFormats ++ JodaTimeSerializers.all

  def json[A](input: Option[A]): Result = {
    input.map { in =>
      Ok(Extraction.decompose(in))
    }.getOrElse(NotFound)
  }

  def json[A](input: A): Result = {
    Ok(Extraction.decompose(input))
  }

  def json[A](input: List[A]): Result = {
    Ok(Extraction.decompose(input))
  }

  def json(nothing: Unit): Result = {
    NoContent
  }

}
