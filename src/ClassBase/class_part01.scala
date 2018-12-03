import scala.util.control.Breaks

object class_part01 {

  def main(args: Array[String]): Unit = {

    //    for (arr <- Array(Array(0), 33,Array(1, 0), Array(0, 1, 0), Array(1, 1, 0), Array(1, 1, 0, 1))) {
    //      val result = arr match {
    //        case Array(0, x @ _*) => 0 +"," + x.mkString("\t")
    //        case Array(0) => "0"
    //        case Array(x, y) => x + " " + y
    //        case Array(x, y, z) => x + " " + y + " " + z
    //        case _ => "something else"
    //      }
    //      println(result)

    //    for (lst <- Array(List(0), List(1, 0), List(0, 1, 2, 1, 346), List(1, 0, 0))) {
    //      val result = lst match {
    //        case 0 :: Nil => "0"
    //        case x :: y :: Nil => x + " " + y
    //        case 0 :: tail => lst.mkString(" ")
    //        case _ => lst.mkString(" ")
    //      }
    //      println(result)


//    val ptr = new class_part01()
//    ptr.price(ptr.sale)

    //      val (x, y) = (1, 2)
    //      println(x+" "+y)
    //
    //      val (q, r) = BigInt(10) /% 3
    //      println(q+" "+r)
    //
    //      val arr = Array(1, 7, 2, 9)
    //
    //      val Array(first, second, _*) = arr
    //      println(first, second)
    //
    //    }
    //
    //    def yy(): Unit = {
    //
    //      println("hello world 2")
    //
    //    }

    whileLoop()

  }

  def whileLoop(): Unit ={
    var n=1
    val loop = new Breaks
    loop.breakable {
      while (n <= 20) {
        n += 1
        if (n == 18) {
          loop.break()
        }
      }
    }
    println(n)
  }

}



class class_part01{

//  def xx(): Unit ={
//
//    println("hello world 1")
//
//    class_part01.yy()
//
//  }

  abstract class Item
  case class Article(description: String, price: Double) extends Item
  case class Bundle(description: String, discount: Double, item: Item*) extends Item

  val sale = Bundle("愚人节大甩卖系列", 10,
    Article("《九阴真经》", 40),
    Bundle("从出门一条狗到装备全发光的修炼之路系列", 20,
      Article("《如何快速捡起地上的装备》", 80),
      Article("《名字起得太长躲在树后容易被地方发现》",30)))

  def getPrice(): Unit ={

//    val result1 = sale match {
//      case Bundle(_, _, Article(descr, _), _*) => descr
//    }
//    println(result1)

//    val result2 = sale match {
//      case Bundle(_, _, art @ Article(_, _), rest @ _*) => (art, rest)
//    }
//    println(result2)

//    val result3 = sale match {
//      case Bundle(_, _, art @ Article(_, _), rest) => (art, rest)
//    }
//    println(result3)

  }

  def price(it: Item): Double = {
    it match {
      case Article(_, p) => return p
      case Bundle(_, disc, its @ _*) => return its.map(price _).sum - disc
    }
  }
  println(price(sale))

}