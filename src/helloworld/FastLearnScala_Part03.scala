package helloworld

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object FastLearnScala_Part03 {

  def main(args : Array[String]): Unit ={

    problem10()

  }

  def problem01(): Unit ={
    val n = 100
    val arr = new Array[Int](n)
    for(i <- 0 until n){
      arr(i) = Random.nextInt(n)
    }
  }

  def problem02(): Unit ={
    val arr = Array(1,2,3,4,5)
    for(i <- Range(0,arr.length-1, 2) ){
      arr(i) ^= arr(i+1)
      arr(i+1) ^= arr(i)
      arr(i) ^= arr(i+1)
    }
    println(arr.mkString(" "))
  }

  def problem03(): Unit ={
    val a = Array(1,2,3,4,5)
    val b = (for(i <- 0 until (a.length,2))
      yield
        if(i < a.length -1 )
          Array(a(i+1),a(i))
        else
          Array(a(i))).flatten.toArray
    println(b.mkString(" "))
  }

  /*
    4.给定一个整数数组，产出一个新的数组，包含元素组中的所有正值，
    以原有顺序排列，之后的元素是所有零或负值，以原有顺序排序。
   */
  def problem04(): Unit ={

    val arr1 = Array[Int](-4, 12, 45, 0, -98, 12,43,7878,-123, 2125, -452, 0)
    var arr2 = ArrayBuffer[Int]()
    var arr3 = ArrayBuffer[Int]()

    arr1.foreach(arg => if(arg>0) arr2+=arg else arr3+=arg)

    arr2++=arr3

    print(arr2.reverse.mkString(" "))

    //print(arr2.mkString(" "))
  }

  def getArrAvg(): Unit ={

    val h = Array(0.1,46.3,24.0,234.2,-4.2)
    val hMean = h.sum/h.length
  }

  def ArrReverse(): Unit ={
    val arr1 = Array[Int](11,1111,2324,346346,11,1111,2324)
    println(arr1.reverse.mkString("<"," ",">"))

    var arrb = ArrayBuffer[Int](-4, 12, 45, 0, -98, 12,43,7878,-123, 2125, -452, 0);

    arrb ++= arr1

    print(arrb.distinct.mkString(",")) //distinct去掉重复项
  }

  def ArrayFilter(): Unit ={

    var arrb = ArrayBuffer[Int](-4, 12, 45, 0, -98, 12,43,7878,-123, 2125, -452, 0)

    val arrnew = for(i <- arrb if i%2==0  ) yield 2 * i
    //本句代码和上一句相同
    //arrnew = arrb.filter(_ % 2 == 0).map(2 * _)

    var sortedArr = arrb.sorted

    print(sortedArr.mkString(" "))
  }

  def sortArray(): Unit ={

    val arr = Array(8,23,3,68,9,1,2,56,832,326,67)

//    scala.util.Sorting.quickSort(arr)

    scala.util.Sorting.stableSort(arr)
    println(arr.mkString(" "))
  }


  def problem09(): Unit ={

    val timeZone = java.util.TimeZone.getAvailableIDs
    val americaTimeZone = timeZone.filter(_.take(8)=="America/")
    val sortedAmericaTimeZone = americaTimeZone.map(_.drop(8)).sorted

    println(timeZone.mkString("\t"))

    println(americaTimeZone.mkString("\t"))

    println(sortedAmericaTimeZone.mkString("\t"))

  }

  def problem10(): Unit ={

    import java.awt.datatransfer._
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    flavors.getNativesForFlavor(DataFlavor.imageFlavor).toArray.toBuffer
    println(flavors)

  }


}
