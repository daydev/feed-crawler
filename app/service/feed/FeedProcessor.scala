package service.feed

import domain.Article

import scala.xml.Elem

trait FeedProcessor {

  def process(x: Elem): Seq[Article]

}

object FeedProcessor {

  def apply(xml: Elem): Option[FeedProcessor] = xml.label match {
    case "feed" => Some(AtomProcessor)
    case "rss" => Some(RSS2Processor)
    case _ => None
  }
}


