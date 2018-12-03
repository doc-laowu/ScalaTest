package ActorTest

import scala.actors.Actor

/**
  *  react 如果要反复执行消息处理，react外层要用loop，不能用while
  */
class YourActor extends Actor {

  override def act(): Unit = {
    loop {
      react {
        case "start" => {
          println("starting ...")
          Thread.sleep(5000)
          println("started")
        }
        case "stop" => {
          println("stopping ...")
          Thread.sleep(8000)
          println("stopped ...")
        }
      }
    }
  }
}


object YourActor {
  def main(args: Array[String]) {
    val actor = new YourActor
    actor.start()
    actor ! "start"
    actor ! "stop"
    println("消息发送完成！")
  }
}


