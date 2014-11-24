package service.feed

import java.net.URL

import domain.Article
import org.joda.time.format.DateTimeFormat
import service.datetime.MultiformatDateTimeParser

import scala.language.postfixOps
import scala.util.Try
import scala.xml.{Elem, Node}

object RSS2Processor extends FeedProcessor {
  private val dateTimeParser = new MultiformatDateTimeParser(
    DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss z"),
    DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z"))

  override def process(xml: Elem): Seq[Article] = {
    def fromXML(xml: Node): Option[Article] = Try {
      val title = xml \ "title" text
      val link = xml \ "link" text
      val desc = xml \ "description" text
      val domain = new URL(link).getHost
      val dateStr = xml \ "pubDate" text

      Article(None, title, link, desc, domain, dateTimeParser.parse(dateStr))
    }.toOption
    xml match {
      case <rss>{items@_*}</rss> =>
        for {
          channel@ <channel>{_*}</channel> <- items.toList
          item@ <item>{_*}</item> <- channel.child
          article <- fromXML(item)
        } yield {
          article
        }
      case _ => Nil
    }
  }
}
