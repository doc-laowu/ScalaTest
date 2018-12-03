package helloworld

object FastLearnScala_Part04 {

  def main(args : Array[String]): Unit ={

    //val score = Map(("alice", 100), ("laowang", 88), ("laoli", 54), ("李云龙", 99), ("赵刚", 88))

    //println(score.getOrElse("李云", 0)) //当存在该key时返回value, 否则返回0

    //遍历
//    for(entry <- score){
//      println(entry._1+"\t"+entry._2)
//    }

//    for((k,v) <- score){
//      println(k+"\t"+v)
//    }

//    //按插入顺序访问所有键
//    val month = scala.collection.mutable.LinkedHashMap(("Jan",1), ("Feb", 2), ("Mar", 3), ("Apr", 4), ("May", 5))
//    month.foreach(args => print(args) )
//
    //与java之间的相互转换
//    import scala.collection.JavaConversions.mapAsScalaMap
//
//    var javaMap:scala.collection.mutable.Map[String, String] =
//      new java.util.TreeMap[String, String]
//
//    javaMap.put("name", "zhangsan")
//    javaMap.put("sex", "male")
//    javaMap.put("age", "23")
//    javaMap += ("ciyt" -> "beijing")
//
//    for (v <- javaMap.keySet) println(v + "====" + javaMap(v))


      //StringOps的方法
//    print("New York".partition(_.isUpper))


    //拉链操作
//    val symbol = Array("<", "-", ">")
//    val counts = Array(1,2,3)
//    symbol.zip(counts).foreach(args => print(args+" "))
//    counts.zip(symbol).foreach(args => print(args+" "))

//    problem05()

//    val a = (0 to 10000).filter(x => x.toString.equals(x.toString.reverse))
//
//    print(a)

//    problem06()


  }

  def problem01(): Unit ={

    var map1 = Map[String,Double](
      "青龙偃月刀"->2.33,"雌雄双股剑"->1.11,"丈八蛇矛"->1.23
    )

    for((key,value)<-map1) print(key+":"+value+" ")
    println()

    val map = for((key, value) <- map1) yield (key, value*0.9)
    println(map)

  }

  def problem02(): Unit ={

    val word = new scala.collection.mutable.HashMap[String,Int]
    val in = new java.util.Scanner(new java.io.File("E:\\IdeaProjects\\ScalaPro\\src\\myfile.txt"))
    while (in.hasNext()){
      val key = in.next()
      word(key) = word.getOrElse(key,0) + 1
    }
    word.foreach(args => println(args))

  }

  def problem03(): Unit ={

    var word2 = Map[String,Int]()
    val in2 = new java.util.Scanner(new java.io.File("E:\\IdeaProjects\\ScalaPro\\src\\myfile.txt"))
    while(in2.hasNext()){
      val key = in2.next()
      word2 += (key -> (word2.getOrElse(key,0)+1))
    }
    word2.foreach(args => println(args))
  }

  def problem04(): Unit ={

    var word3 = scala.collection.immutable.SortedMap[String,Int]()
    val in3 = new java.util.Scanner(new java.io.File("E:\\IdeaProjects\\ScalaPro\\src\\myfile.txt"))
    while(in3.hasNext()){
      val key = in3.next()
      word3 += (key->(word3.getOrElse(key,0) + 1))
    }
    word3.foreach(args => println(args))

  }

  def problem05(): Unit ={

    val calendar = new scala.collection.mutable.LinkedHashMap[String,Int]
    calendar += ("Monday" -> java.util.Calendar.MONDAY)
    calendar += ("Tuesday" -> java.util.Calendar.TUESDAY)
    calendar += ("Wednesday" -> java.util.Calendar.WEDNESDAY)
    calendar += ("Thursday" -> java.util.Calendar.THURSDAY)
    calendar += ("Friday" -> java.util.Calendar.FRIDAY)
    calendar += ("Saturday" -> java.util.Calendar.SATURDAY)
    calendar += ("Sunday" -> java.util.Calendar.SUNDAY)
    println(java.util.Calendar.MONDAY)
    println(calendar)

  }

  def problem06(): Unit ={

    import scala.collection.JavaConversions.propertiesAsScalaMap
    val properties:scala.collection.Map[String,String] = System.getProperties.take(10)
    val keyMaxLength = (for(key <- properties.keySet) yield key.length).max
    properties.foreach{
      case (a,b)=>println(a+" "*(keyMaxLength-a.length)+ "|" + b)
    }

  }

}
