package service.feed

import domain.Article
import org.joda.time.{DateTimeZone, DateTime}
import org.specs2.mutable._

class RSS2ProcessorSpec extends Specification{

  "RSS2Processor" should {
    "Extract articles" in {
      RSS2Processor.process(SampleFeeds.rss2) must be_==(
        Seq(
          Article(
            None,
            "Example entry",
            "http://www.example.com/blog/post/1",
            "Here is some text containing an interesting description.",
            "www.example.com",
            new DateTime(1252254000000L).withZone(DateTimeZone.UTC))))
    }
  }

}
