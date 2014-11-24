package repository.impl

import domain.Article
import org.joda.time.DateTime
import repository.ArticleRepo
import repository.SlickConf.Driver.simple._
import repository.SlickConf.JodaSupport._
import repository.SlickConf.db

import scala.language.postfixOps

class ArticleRow(tag: Tag) extends Table[Article](tag, "ARTICLE") {
  def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)

  def title = column[String]("TITLE")

  def link = column[String]("LINK")

  def desription = column[String]("DESCRIPTION")

  def domain = column[String]("DOMAIN")

  def published = column[DateTime]("PUBLISHED")

  override def * = (id.?, title, link, desription, domain, published) <>(Article.apply _ tupled, Article.unapply)
}

class ArticleRepoSlick(datasource: String) extends TableQuery[ArticleRow](new ArticleRow(_)) with ArticleRepo {
  self =>

  override def findLast(domain: String): Option[Article] = db(datasource).withSession {
    implicit session =>
      self.filter(_.domain === domain).sortBy(_.published.desc).firstOption
  }

  override def save(article: Article): Long = db(datasource).withSession {
    implicit session =>
      (self returning self.map(_.id)).insert(article)
  }
}


