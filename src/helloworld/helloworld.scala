package helloworld

object helloworld {
  def main(args: Array[String]) {

//    val a = 11 //不可变常量
//    var b = 12 //可变变量

//    print(repl(3))

//    print("crazy"*3)

//    print(10 max 2)

//    print(BigInt.apply(2).<<(1024))

//    val str:String = "Hello"
//
//    print(str.charAt(0)+"\t"+str.charAt(str.length-1))

//    getUnicode("Hello")

//    delayed(time())

//    printStrings("hello", "world", "!")

//    print(factorial(3))

//    println(getMax(100, 101))

//    formatedString
//
//
//    //创建区间数组
//
//    val arr = Range(0,20,2)
//    println(arr.mkString(","))

//    /**
//      * 隐式定义是指编译器为了修正类型错误而允许插入到程序中的定义。
//      *
//      * @param str
//      * @return
//      */
//
//    implicit def String2Int(str: String) = {
//      str.toInt
//    }
//
//    print("120"/12)

    case class Person(name: String, age: Int) {
      def +(x: Int) = age + x
      def +(p: Person) = age + p.age
    }

    val person = Person("xiaohong", 1)
    println(person + 1)

    implicit def Add1(x: Int) = Person("Empty", x)
    println(1 + person) // 2

    implicit def Add2(x: Person) = x.age
    println(1 + person) // 2

  }


  def repl(num:Int): Int = {
    var x = num*num*num
    x*x - num
  }

  def isNegative(num: Int): Int ={
    if(num > 0){
      1
    }else if(num < 0) {
      -1
    }else{
      0
    }
  }

  def countdown(n:Int){
    for(i<- 0 to n; j = 10 - i){
      print(j+" ")
    }
  }

  def getUnicode(str : String){

//    var result:Long = 1L;
// bv
//    for(i <- 0 until str.length){
//      result *= str.charAt(i).toLong
//    }
//    result

    var result = 1;
    for(ch <- str){
      result *= ch
    }
    print(result)
  }

  //函数传名调用   其实就是传的一个方法
  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }
  def delayed( t: => Long ) = {
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }

  //函数可变参数

  def printStrings(strs : String*): Unit ={

    for(str <- strs) print(str+" ")

  }

  //函数的默认参数值

  def defaultParam(a:Int = 5, b : Long = 10000L): Unit ={

    println(a+" "+b)

  }

  //高阶函数     操作其他函数的函数

  // 函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
  def apply(f: Int => String, v: Int) = f(v)

  def layout[A](x: A) = "[" + x.toString() + "]"

  //函数嵌套

  def factorial(i: Int): Int = {
    def fact(i: Int, accumulator: Int): Int = {
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator)
    }
    fact(i, 1)
  }

  //scala闭包 函数

  var getMax = (a:Int, b:Int) => {
    if(a > b) a else b
  }

  //柯里化
  def strcat(s1: String)(s2: String) = {
    s1 + s2
  }

  def formatedString(): Unit ={

    var floatVar = 12.456
    var intVar = 2000
    var stringVar = "菜鸟教程!"
    var fs = printf("浮点型变量为 " +
      "%f, 整型变量为 %d, 字符串为 " +
      " %s", floatVar, intVar, stringVar)
    println(fs)

  }

}
