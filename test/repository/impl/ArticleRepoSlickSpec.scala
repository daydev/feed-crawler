package repository.impl

import domain.Article
import helpers.WithMemDb
import org.joda.time.DateTime
import org.specs2.mutable._

class ArticleRepoSlickSpec extends Specification {

  val repo = new ArticleRepoSlick("test")

  "ArticleRepoSlick" should {

    "Find latest article" in new WithMemDb {
      repo.findLast("some.com") must beSome.which(_.title == "Second")
    }

    "Return None if there's no articles from the domain" in new WithMemDb {
      repo.findLast("noway.com") must beNone
    }

    "Persist new article" in new WithMemDb {
      val newArticle = Article(None, "Third", "http://another.com", "I am number three", "another.com", new DateTime())
      val newId = repo.save(newArticle)
      newId must_== 3
      repo.findLast("another.com") must beSome.which(_.title == "Third")
    }

  }

}
