package repository

import domain.Article

trait ArticleRepo {

  def findLast(domain: String): Option[Article]

  def save(article: Article): Long

}
