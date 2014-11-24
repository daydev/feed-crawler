package service.feed

import java.net.URL

import domain.Article
import org.joda.time.DateTime

import scala.language.postfixOps
import scala.util.Try
import scala.xml.{Elem, Node}

object AtomProcessor extends FeedProcessor {

  override def process(xml: Elem): Seq[Article] = {
    def fromXML(xml: Node): Option[Article] = Try {
      val title = xml \ "title" text
      val link = (xml \ "link").head \ "@href" text
      val desc = (xml \ "content" text).trim
      val domain = new URL(link).getHost
      val dateStr = xml \ "updated" text

      Article(None, title, link, desc, domain, new DateTime(dateStr))
    }.toOption
    xml match {
      case <feed>{entries@_*}</feed> =>
        for {
          entry @ <entry>{_*}</entry> <- entries.toList
          article <- fromXML(entry)
        } yield {
          article
        }
      case _ => Nil
    }
  }
}
