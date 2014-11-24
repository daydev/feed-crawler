package repository

import repository.impl.ArticleRepoSlick

object Repos {

  val articleRepo: ArticleRepo = new ArticleRepoSlick("default")

}
