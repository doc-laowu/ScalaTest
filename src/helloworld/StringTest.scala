package helloworld

object StringTest {
  def main(args: Array[String]): Unit = {

    val str:String = "DjdA-5oKYFQ\tNxTDlnOuybo\tc-8VuICzXtU\tDH56yrIO5nI\tW1Uo5DQTtzc"
    val arr = str.split("\t")

    val arr2 = for (i <- 1 until arr.length) yield arr(i)

    print(arr2.mkString("&"))

    val text:StringBuilder = new StringBuilder()
    for(i <- 0 to 3) {
      text.append(" "+"hahaha ")
    }

    println(text)
  }
}
