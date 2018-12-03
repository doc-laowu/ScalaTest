package helloworld

object FastLearnScala_Part01 {

  def main(args : Array[String]){

//    print(probablePrime(3,Random))

//    println(BigInt(Random.nextInt()).toString(36))

    val str : String = "hello world!"

    //从左边开始获取2个元素，
    //print(str.take(3))
    //从左边删除元素
    //print(str.drop(3))
    //从右边取第几个元素
    //print(str.takeRight(3))
    //takeWhile, 从第一个元素开始判断，满足条件，就留下，直到遇到第一个不满足的条件的元素，就结束循环
    //print(str.takeWhile(_.<=('r')))

    //print(str.filter(_.<=('r')))

    //print(str.filterNot(_.==('r')))

    //print(str.substring(4,7))

    //3.指出Scala何种情况下赋值语句x=y=1是合法的
    //在x的类型为Unit的情况下是合法的

    //4.针对下列java循环编写一个scala版
    //for(int i=10;i>=0;i--)System.out.println(i)

//    for(i <- 0 to 10 reverse){
//      print(i+" ")
//    }

  print(execise8("hello",4))

  }


  //7.同样是解决6的问题，但不能够用循环（提示，StringOps）
  def execise7(input:String):BigInt = {
    var sum:BigInt = 0
    sum = input.map(_.toLong).product //map重新构建了一个集合，product对该集合进行类乘
    sum
  }

  def execise8(input:String,curIndex:Int):BigInt = {
    if(curIndex == 0){
      input(curIndex).toLong
    }else{
      execise8(input, curIndex-1)*input(curIndex).toLong
    }
  }

  //9.把前一个练习的函数改为递归函数
  def execise9(input:String,curIndex:Int):BigInt = {
    if (curIndex == input.length - 1){
      input(curIndex).toLong
    } else{
      execise9(input,curIndex+1)*input(curIndex).toLong
    }
  }

  def computeX(X : Int, N : Int): Unit ={
    if(N == 0){
      1
    }else if(N%2 == 0 && N > 0) {
      Math.pow(X, N/2)
    }else if(N%2 != 0 && N > 0){
      Math.pow(X*X, N-1)
    }else{
      Math.pow(1/X, -N)
    }
  }

}
