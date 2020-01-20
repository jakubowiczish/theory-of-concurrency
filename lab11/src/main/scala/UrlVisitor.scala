package main.scala

import java.net.URL

import org.htmlcleaner.{HtmlCleaner, TagNode}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util._

object UrlVisitor {

  def visitUrls(basicUrl: String, aAttributes: Array[TagNode], currentLevel: Int, maxDepth: Int) {
    if (currentLevel > maxDepth) {
      println("Max depth has been reached")
      return
    }

    aAttributes.foreach(element => {
      val link = element.getAttributeByName("href")

      if (link == null || link.isEmpty || link.isBlank) {
        println("No link found for element: " + element)
      } else {
        val url: String = LinkUtil.getLinkUsingAttribute(basicUrl, link)
        println(url)

        getFutureForUrl(url).onComplete {
          case Success(value) => visitUrls(basicUrl, value, currentLevel + 1, maxDepth)
          case Failure(_) => println("Problem while performing on complete");
        }
      }
    })
  }

  def getFutureForUrl(url: String): Future[Array[TagNode]] = {
    Future {
      val html = new HtmlCleaner().clean(new URL(url))
      val aElements = html.getElementsByName("a", true)
      aElements
    }
  }
}
