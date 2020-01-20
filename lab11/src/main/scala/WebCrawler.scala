package main.scala

import java.net.URL

import org.htmlcleaner.HtmlCleaner

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object WebCrawler extends App {

  val basicURL = "https://google.com"
  val startLevel: Int = 1
  val maxDepth: Int = 1

  val elements = new HtmlCleaner().clean(new URL(basicURL))
    .getElementsByName("a", true)

  UrlVisitor.visitUrls(basicURL, elements, startLevel, maxDepth)

  val result = Await.result(Future(0), 100.second)
  Thread.sleep(100000)
}

