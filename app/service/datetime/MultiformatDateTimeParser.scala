package service.datetime

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

import scala.util.Try

/**
 * Takes multiple possible formats of datetime string and tries them all
 * assuming that at least one should succeed.
 *
 * Parsing is unsafe as is JodaTime's own parsing.
 * If none of the formats succeeded, runtime exception occurs.
 */
class MultiformatDateTimeParser(parsers: DateTimeFormatter*) {

  def parse(dateStr: String): DateTime = {
    parsers.view.map {
      p => Try(p.parseDateTime(dateStr))
    }.find(_.isSuccess).get.get
  }

}
