package controllers

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool
import play.api.Application
import play.api.Play.current
import play.api.mvc._
import service.feed.FeedWorker
import service.feed.FeedWorker.Collect

object Application extends Controller {

  lazy val workerActor = ActorSystem().actorOf(RoundRobinPool(5).props(Props[FeedWorker]), "feedCrawler")

  lazy val feeds =
    for {
      feedsConf <- implicitly[Application].configuration.getStringList("feeds").toList
      feed <- feedsConf.toArray
    } yield {
      feed.toString
    }

  def index = Action {
    feeds.foreach(workerActor ! Collect(_))
    Ok("")
  }

}