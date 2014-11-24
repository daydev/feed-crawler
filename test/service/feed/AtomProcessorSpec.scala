package service.feed

import domain.Article
import org.joda.time.{DateTimeZone, DateTime}
import org.specs2.mutable._

class AtomProcessorSpec extends Specification {

  "AtomProcessor" should {
    "Extract articles" in {
      AtomProcessor.process(SampleFeeds.atom) must be_==(
        Seq(
          Article(
            None,
            "Atom-Powered Robots Run Amok",
            "http://example.org/2003/12/13/atom03",
            "This is the entry content.",
            "example.org",
            new DateTime(1071340202000L))))
    }
  }

}
