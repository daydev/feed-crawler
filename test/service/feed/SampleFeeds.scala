package service.feed

object SampleFeeds {

  val rss2 = <rss version="2.0">
    <channel>
      <title>RSS Title</title>
      <description>This is an example of an RSS feed</description>
      <link>http://www.example.com/main.html</link>
      <lastBuildDate>Mon, 06 Sep 2010 00:01:00 +0000</lastBuildDate>
      <pubDate>Sun, 06 Sep 2009 16:20:00 +0000</pubDate>
      <ttl>1800</ttl>

      <item>
        <title>Example entry</title>
        <description>Here is some text containing an interesting description.</description>
        <link>http://www.example.com/blog/post/1</link>
        <guid>7bd204c6-1655-4c27-aeee-53f933c5395f</guid>
        <pubDate>Sun, 06 Sep 2009 16:20:00 +0000</pubDate>
      </item>

    </channel>
  </rss>

  val atom = <feed xmlns="http://www.w3.org/2005/Atom">

    <title>Example Feed</title>
    <subtitle>A subtitle.</subtitle>
    <link href="http://example.org/feed/" rel="self"/>
    <link href="http://example.org/"/>
    <id>urn:uuid:60a76c80-d399-11d9-b91C-0003939e0af6</id>
    <updated>2003-12-13T18:30:02Z</updated>


    <entry>
      <title>Atom-Powered Robots Run Amok</title>
      <link href="http://example.org/2003/12/13/atom03"/>
      <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
      <updated>2003-12-13T18:30:02Z</updated>
      <content type="xhtml">
        <div xmlns="http://www.w3.org/1999/xhtml">
          <p>This is the entry content.</p>
        </div>
      </content>
      <author>
        <name>John Doe</name>
        <email>johndoe@example.com</email>
      </author>
    </entry>

  </feed>

}
