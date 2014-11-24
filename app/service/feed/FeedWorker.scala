package service.feed

import akka.actor.Actor
import org.joda.time.DateTime
import play.api.Logger
import play.api.Play.current
import play.api.libs.ws._
import repository.Repos._
import service.feed.FeedWorker.Collect

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.xml.XML

class FeedWorker extends Actor {
  override def receive = {
    case Collect(url) =>
      val feedFut = WS.url(url).get()
      feedFut.onComplete {
        result => result.map {
          response =>
            val xml = XML.loadString(response.body)
            val articles = FeedProcessor(xml).map(_.process(xml)).toList.flatten
            val lastArticleDate = (for {
              article <- articles.headOption
              lastArticle <- articleRepo.findLast(article.domain)
            } yield {
              lastArticle.published
            }).getOrElse(new DateTime(0))
            val newArticles = articles.takeWhile(_.published.isAfter(lastArticleDate))
            newArticles.foreach(articleRepo.save)
        } match {
          case Success(_) => Logger.info(s"Succesfully collected articles from feed [$url].")
          case Failure(ex) =>
            Logger.error(s"An error occured while parsing feed [$url]")
            Logger.error(ex.getMessage, ex)
        }
      }
  }
}

object FeedWorker {

  case class Collect(feedUrl: String)

}
