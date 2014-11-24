package domain

import org.joda.time.DateTime

case class Article(id: Option[Long],
                   title: String,
                   link: String,
                   desc: String,
                   domain: String,
                   published: DateTime)