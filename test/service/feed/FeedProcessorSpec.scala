package service.feed

import org.specs2.mutable._


class FeedProcessorSpec extends Specification {

  "FeedProcessor" should {

    "Create processor for Atom feeds" in {
      FeedProcessor(SampleFeeds.atom) must beSome(AtomProcessor)
    }

    "Create processor for RSS 2.0 feeds" in {
      FeedProcessor(SampleFeeds.rss2) must beSome(RSS2Processor)
    }

    "Return None for unknown Feed type" in {
      FeedProcessor(<channel></channel>) must beNone
    }

  }

}
