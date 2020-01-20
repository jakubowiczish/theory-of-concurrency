package main.scala

object LinkUtil {

  def getLinkUsingAttribute(basicUrl: String, link: String): String = {
    if (link.toString.contains("http://") || link.toString.contains("https://")) {
      link.toString
    } else if (link.toString.length > 2 && link.toString.substring(0, 2).equals("//")) {
      "http:" + link.toString
    } else {
      basicUrl + link
    }
  }
}