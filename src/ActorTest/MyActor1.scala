package ActorTest

import scala.actors.Actor

/**
  * Scala Actor并发编程
  *
  */

object MyActor1 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 10){
      println("actor-1 " + i)
      Thread.sleep(2000)
    }
  }
}

object MyActor2 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 10){
      println("actor-2 " + i)
      Thread.sleep(2000)
    }
  }
}

object ActorTest extends App{
  //启动Actor
  MyActor1.start()
  MyActor2.start()
}

